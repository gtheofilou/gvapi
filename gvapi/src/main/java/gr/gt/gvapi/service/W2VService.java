package gr.gt.gvapi.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.deeplearning4j.clustering.algorithm.Distance;
import org.deeplearning4j.clustering.cluster.Cluster;
import org.deeplearning4j.clustering.cluster.ClusterSet;
import org.deeplearning4j.clustering.cluster.Point;
import org.deeplearning4j.clustering.cluster.PointClassification;
import org.deeplearning4j.clustering.kmeans.KMeansClustering;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import com.google.common.base.Splitter;
import gr.gt.gvapi.dao.GoogleResponseDao;
import gr.gt.gvapi.entity.GoogleResponse;

@Service
@Transactional
public class W2VService {

    @Autowired
    private GoogleResponseDao googleResponseDao;


    public void run() {

        // List<GoogleResponse> gList = googleResponseDao.getOCR();

        StopWatch sw = new StopWatch();

        sw.start("loading model");
        File model = new File("/home/gio/Diploma/GoogleNews-vectors-negative300.bin.gz");
        // File model = new File("/home/gio/Diploma/freebase-vectors-skipgram1000.bin.gz");
        Word2Vec word2Vec = WordVectorSerializer.readWord2VecModel(model);
        sw.stop();

        Collection<String> lst3 = word2Vec.wordsNearest("man", 10);
        System.out.println(lst3);

        double cosSim = word2Vec.similarity("day", "night");
        double cosSim2 = word2Vec.similarity("first", "second");
        System.out.println("-->" + cosSim);
        System.out.println("-->" + cosSim2);

        String s1 = "first string";
        String s2 = "second string";
        cosineSimForSentence(word2Vec, s1, s2);


        // ------------------
        int CLUSTERS = 5;
        int MAX_ITERATIONS = 300;
        KMeansClustering kmeans =
                KMeansClustering.setup(CLUSTERS, MAX_ITERATIONS, Distance.COSINE_SIMILARITY);

        // 2. iterate over rows in the paragraphvector and create a List of paragraph vectors
        List<INDArray> vectors = new ArrayList<INDArray>();
        List<String> labelsList = googleResponseDao.getDistinctLabels();
        for (String word : labelsList) {
            vectors.add(word2Vec.getWordVectorMatrix(word));
        }
        System.out.println("labels list size: " + labelsList.size());
        List<Point> pointsLst = Point.toPoints(vectors);

        sw.start("clustering");
        ClusterSet cs = kmeans.applyTo(pointsLst);
        sw.stop();

        List<Cluster> clsterLst = cs.getClusters();

        System.out.println("\nCluster Centers:");
        for (Cluster c : clsterLst) {
            System.out.println(c.getPoints().size());
            Point center = c.getCenter();
            System.out.println(center.getId());
            System.out.println(center.getLabel());
        }

        // double[] nesVec = word2Vec.getWordVector("Actor");
        Point newpoint = new Point("myid", "mylabel", word2Vec.getWordVectorMatrix("Actor"));
        PointClassification pc = cs.classifyPoint(newpoint);
        System.out.println(pc.getCluster().getCenter().getId());

        // double[] nesVec2 = word2Vec.getWordVector("actor");
        Point newpoint2 = new Point("myid2", "mylabel2", word2Vec.getWordVectorMatrix("actor"));
        PointClassification pc2 = cs.classifyPoint(newpoint2);
        System.out.println(pc2.getCluster().getCenter().getId());

        // double[] nesVec3 = word2Vec.getWordVector("Aircraft");
        Point newpoint3 = new Point("myid3", "mylabel3", word2Vec.getWordVectorMatrix("Aircraft"));
        PointClassification pc3 = cs.classifyPoint(newpoint3);
        System.out.println(pc3.getCluster().getCenter().getId());

        // double[] nesVec4 = word2Vec.getWordVector("Giorgos");
        Point newpoint4 = new Point("myid4", "mylabel4", word2Vec.getWordVectorMatrix("Giorgos"));
        PointClassification pc4 = cs.classifyPoint(newpoint4);
        System.out.println(pc4.getCluster().getCenter().getId());


        // INDArray wordVectorMatrix = word2Vec.getWordVectorMatrix("myword");
        // double[] wordVector = word2Vec.getWordVector("myword");

        // System.out.println(wordVectorMatrix);
        // System.out.println(wordVector);

    }

    private static double cosineSimForSentence(Word2Vec word2Vec, String s1, String s2) {
        Collection<String> label1 = Splitter.on(' ').splitToList(s1);
        Collection<String> label2 = Splitter.on(' ').splitToList(s2);

        double r = Transforms.cosineSim(word2Vec.getWordVectorsMean(label1),
                word2Vec.getWordVectorsMean(label2));

        System.out.println("Similarity Score between: " + s1 + " --vs-- " + s2 + ":==>" + r);

        return r;

    }

}
