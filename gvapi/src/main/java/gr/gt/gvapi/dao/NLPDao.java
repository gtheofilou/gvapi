package gr.gt.gvapi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.stats.ClassicCounter;
import edu.stanford.nlp.stats.Counter;
import edu.stanford.nlp.util.CoreMap;
import gr.gt.gvapi.entity.GoogleResponse;
import gr.gt.gvapi.entity.NLP;

@Repository("NLPDao")
public class NLPDao extends AbstractDao<NLP, Long> {

    public void truncateTable() {
        entityManager.createNativeQuery("TRUNCATE TABLE NLP").executeUpdate();
    }

    @Transactional(value = TxType.REQUIRES_NEW)
    public void nlp(StanfordCoreNLP pipeline, GoogleResponse g) {

        // per document
        int totalNoOfTerms = 0;
        Counter<String> counter = new ClassicCounter<String>();
        Map<String, String> word2pos = new HashMap<String, String>();

        // Annotation annotation = new Annotation(g.getDescription());
        Annotation annotation = new Annotation(g.getFinalDescription());

        pipeline.annotate(annotation);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                word = word.toLowerCase();

                // https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html
                if (pos.startsWith("NN") || pos.startsWith("JJ") || pos.startsWith("VB")
                        || pos.startsWith("RB")) {
                    pos = pos.substring(0, 2);
                    String h = word2pos.get(word);
                    if (StringUtils.isEmpty(h))
                        word2pos.put(word, pos);
                    counter.incrementCount(word);
                    ++totalNoOfTerms;
                }
            }
        }

        for (Map.Entry<String, Double> es : counter.entrySet()) {
            NLP nlp = new NLP(g.getFileId(), es.getKey(), word2pos.get(es.getKey()),
                    (int) counter.getCount(es.getKey()), totalNoOfTerms,
                    (float) counter.getCount(es.getKey()) / totalNoOfTerms);
            persist(nlp);
        }
    }

}
