package gr.gt.gvapi.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.springframework.util.StringUtils;
import com.google.common.base.Splitter;
import gr.gt.gvapi.dao.ClusterConfDao;
import gr.gt.gvapi.dao.ClusterDao;
import gr.gt.gvapi.dao.ClusterResultDao;
import gr.gt.gvapi.dao.FileDao;
import gr.gt.gvapi.dao.FileUserAssocDao;
import gr.gt.gvapi.dao.GoogleResponseDao;
import gr.gt.gvapi.dao.TweetDao;
import gr.gt.gvapi.entity.ClusterConf;
import gr.gt.gvapi.entity.ClusterId;
import gr.gt.gvapi.entity.ClusterResult;
import gr.gt.gvapi.entity.FileUserAssoc;
import gr.gt.gvapi.entity.GoogleResponse;
import gr.gt.gvapi.entity.GoogleResponse.Type;
import gr.gt.gvapi.entity.Tweet;

@Service
@Transactional
public class W2VService {

    @Autowired
    private GoogleResponseDao googleResponseDao;
    @Autowired
    private ClusterDao clusterDao;
    @Autowired
    private ClusterConfDao clusterConfDao;
    @Autowired
    private ClusterResultDao clusterResultDao;
    @Autowired
    private FileDao fileDao;
    @Autowired
    private FileUserAssocDao fileUserAssocDao;
    @Autowired
    private TweetDao tweetDao;

    private static Word2Vec model = null;

    private Word2Vec getWord2VecModel() {
        if (model == null) {
            File file = new File("/home/gio/Diploma/GoogleNews-vectors-negative300.bin.gz");
            // File model = new File("/home/gio/Diploma/freebase-vectors-skipgram1000.bin.gz");
            model = WordVectorSerializer.readWord2VecModel(file);
        }
        return model;
    }


    public void run(Integer clusters, Integer iterations) {

        Long runID = System.currentTimeMillis();
        System.out.println("runID: " + runID);

        // List<GoogleResponse> gList = googleResponseDao.getOCR();

        StopWatch sw = new StopWatch();

        sw.start("loading model -> ");
        Word2Vec word2Vec = getWord2VecModel();
        sw.stop();
        System.out.println(sw.getLastTaskName() + sw.getLastTaskTimeMillis());

        // Collection<String> lst3 = word2Vec.wordsNearest("man", 10);
        // System.out.println(lst3);
        //
        // double cosSim = word2Vec.similarity("day", "night");
        // double cosSim2 = word2Vec.similarity("first", "second");
        // System.out.println("-->" + cosSim);
        // System.out.println("-->" + cosSim2);
        //
        // String s1 = "first string";
        // String s2 = "second string";
        // cosineSimForSentence(word2Vec, s1, s2);

        /**
         * Clustering
         */
        KMeansClustering kmeans =
                KMeansClustering.setup(clusters, iterations, Distance.COSINE_SIMILARITY, false);

        // Fetch labels
        List<String> labelsList = googleResponseDao.getDistinctLabels();
        System.out.println("labels list size: " + labelsList.size());

        // Convert to vectors
        List<INDArray> vectors = new ArrayList<>();
        Map<String, INDArray> vectorsMap = new HashMap<>();
        for (String word : labelsList) {
            INDArray indArray = word2Vec.getWordVectorMatrix(word);
            vectors.add(indArray);
            vectorsMap.put(word, indArray);
        }

        // Convert to points
        List<Point> pointsLst = Point.toPoints(vectors);

        // Do cluster
        Date start = new Date();
        sw.start("clustering -> ");
        ClusterSet cs = kmeans.applyTo(pointsLst);
        sw.stop();
        System.out.println(sw.getLastTaskName() + sw.getLastTaskTimeMillis());

        ClusterConf clusterConf = new ClusterConf();
        clusterConf.setRunID(runID);
        clusterConf.setClusters(clusters);
        clusterConf.setIterations(iterations);
        clusterConf.setWorkTime(sw.getLastTaskTimeMillis());
        clusterConf.setStart(start);
        clusterConfDao.persist(clusterConf);

        List<Cluster> clsterLst = cs.getClusters();
        System.out.println("------Clusters------");

        int cnt = 0;
        for (Cluster c : clsterLst) {
            System.out.println("-----------" + ++cnt + "------------");
            System.out.println("Cluster size: " + c.getPoints().size());
            Point center = c.getCenter();
            System.out.println("Cluster center: " + center.getId());
            System.out.println("Cluster label: " + center.getLabel());
            System.out.println("-------------------------------------");

            ClusterResult clusterResult = new ClusterResult();
            clusterResult.setRunID(runID);
            clusterResult.setClusterID(center.getId());
            clusterResult.setSize(c.getPoints().size());

            clusterResultDao.persist(clusterResult);
        }

        for (String word : labelsList) {
            // INDArray indArray = word2Vec.getWordVectorMatrix(word);
            // vectors.add(indArray);
            // vectorsMap.put(word, indArray);

            Point p = new Point(word, word, vectorsMap.get(word));
            PointClassification pc = cs.classifyPoint(p);
            gr.gt.gvapi.entity.Cluster c = new gr.gt.gvapi.entity.Cluster();
            c.setRunID(runID);
            c.setLabel(word);
            c.setClusterID(pc.getCluster().getCenter().getId());

            gr.gt.gvapi.entity.Cluster c2 = clusterDao.find(new ClusterId(runID, word));
            if (c2 != null)
                System.out.println("Skipping: " + word);
            else
                clusterDao.persist(c);
        }

        // double[] nesVec = word2Vec.getWordVector("Actor");
        // Point newpoint = new Point("myid", "mylabel", word2Vec.getWordVectorMatrix("Actor"));
        // PointClassification pc = cs.classifyPoint(newpoint);
        // System.out.println(pc.getCluster().getCenter().getId());
        //
        // Point newpoint2 = new Point("myid2", "mylabel2", word2Vec.getWordVectorMatrix("actor"));
        // PointClassification pc2 = cs.classifyPoint(newpoint2);
        // System.out.println(pc2.getCluster().getCenter().getId());
        //
        // Point newpoint3 = new Point("myid3", "mylabel3",
        // word2Vec.getWordVectorMatrix("Aircraft"));
        // PointClassification pc3 = cs.classifyPoint(newpoint3);
        // System.out.println(pc3.getCluster().getCenter().getId());
        //
        // Point newpoint4 = new Point("myid4", "mylabel4",
        // word2Vec.getWordVectorMatrix("Giorgos"));
        // PointClassification pc4 = cs.classifyPoint(newpoint4);
        // System.out.println(pc4.getCluster().getCenter().getId());


        System.out.println("---------------SUMMARY------------------------");
        System.out.println(sw.prettyPrint());
    }

    public void w2vOCRLabelsSimilarity() {

        Word2Vec word2Vec = getWord2VecModel();

        List<Long> fileIDList = fileDao.getFileIDs();
        int size = fileIDList.size();
        System.out.println("fileIDList: " + size);
        int cnt = 0;
        for (Long fileID : fileIDList) {
            cnt++;
            System.out.println("Iteration: " + cnt + "/" + size);
            List<GoogleResponse> gList = googleResponseDao.getGoogleResponse(fileID);

            GoogleResponse ocr = null;
            for (GoogleResponse g : gList) {
                if (g.getType() == Type.OCR) {
                    ocr = g;
                    break;
                }
            }

            if (ocr == null || StringUtils.isEmpty(ocr.getDescription()))
                continue;

            gList.remove(ocr);

            googleResponseDao.saveCosineSim(gList, ocr.getFinalDescription(), word2Vec);
            // for (GoogleResponse g : gList) {
            // double sim =
            // cosineSimForSentence(word2Vec, ocr.getDescription(), g.getDescription());
            // g.setCosineSim(sim);
            // }
        }
    }

    private static double cosineSimForSentence(Word2Vec word2Vec, String s1, String s2) {
        Collection<String> label1 = Splitter.on(' ').splitToList(s1);
        Collection<String> label2 = Splitter.on(' ').splitToList(s2);
        try {

            double r = Transforms.cosineSim(word2Vec.getWordVectorsMean(label1),
                    word2Vec.getWordVectorsMean(label2));
            return r;
        } catch (Exception e) {
            return -2;
        }
    }

    public void w2vOCRTweetsSimilarity() {
        Word2Vec word2Vec = getWord2VecModel();

        Set<Long> usersProcessed = new HashSet<Long>();

        List<Long> fileIDList = fileDao.getFileIDs();
        int size = fileIDList.size();
        System.out.println("fileIDList: " + size);
        int cnt = 0;
        for (Long fileID : fileIDList) {
            cnt++;

            GoogleResponse ocr = googleResponseDao.getFileOCR(fileID);
            if (ocr == null || StringUtils.isEmpty(ocr.getDescription()))
                continue;

            List<FileUserAssoc> fuaList = fileUserAssocDao.getUserID(fileID);
            if (fuaList == null || fuaList.isEmpty())
                continue;
            System.out.println("Iteration: " + cnt + "/" + size + " fileid: " + fileID + " users: "
                    + fuaList.size());

            for (FileUserAssoc fua : fuaList) {

                if (usersProcessed.contains(fua.getUserId()))
                    continue;
                else
                    usersProcessed.add(fua.getUserId());

                List<Tweet> tweetList = tweetDao.getUserTweets(fua.getUserId());
                if (tweetList == null || tweetList.isEmpty())
                    continue;

                for (Tweet t : tweetList) {
                    double sim = cosineSimForSentence(word2Vec, ocr.getDescription(), t.getText());
                    t.setCosineSim(sim);
                }
            }

        }
    }

}
