package gr.gt.gvapi.dao;

import java.util.Collection;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.springframework.stereotype.Repository;
import com.google.common.base.Splitter;
import gr.gt.gvapi.entity.GoogleResponse;
import gr.gt.gvapi.entity.GoogleResponse_;

@Repository("GoogleResponseDao")
public class GoogleResponseDao extends AbstractDao<GoogleResponse, Long> {

    public List<GoogleResponse> getGoogleResponse(Long fileId) {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoogleResponse> q = c.createQuery(GoogleResponse.class);
        Root<GoogleResponse> r = q.from(GoogleResponse.class);

        q.where(c.equal(r.get(GoogleResponse_.FILE_ID), fileId));

        return entityManager.createQuery(q).getResultList();
    }

    public List<String> getDistinctLabels() {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> q = c.createQuery(String.class);
        Root<GoogleResponse> r = q.from(GoogleResponse.class);

        q.select(r.get(GoogleResponse_.DESCRIPTION));
        q.where(c.equal(r.get(GoogleResponse_.TYPE), 0)).distinct(true);

        return entityManager.createQuery(q).getResultList();
    }


    public List<GoogleResponse> getOCR() {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoogleResponse> q = c.createQuery(GoogleResponse.class);
        Root<GoogleResponse> r = q.from(GoogleResponse.class);

        q.where(c.equal(r.get(GoogleResponse_.TYPE), 1));

        return entityManager.createQuery(q).getResultList();
    }

    public GoogleResponse getFileOCR(Long fileId) {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoogleResponse> q = c.createQuery(GoogleResponse.class);
        Root<GoogleResponse> r = q.from(GoogleResponse.class);

        q.where(c.and(c.equal(r.get(GoogleResponse_.FILE_ID), fileId),
                c.equal(r.get(GoogleResponse_.TYPE), 1)));

        return getSingleOptional(entityManager.createQuery(q));
    }


    public List<Long> getIDList() {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> q = c.createQuery(Long.class);
        Root<GoogleResponse> r = q.from(GoogleResponse.class);

        q.select(r.get(GoogleResponse_.ID));

        return entityManager.createQuery(q).getResultList();
    }

    @Transactional(value = TxType.REQUIRES_NEW)
    public void saveFinalDescription(Long id) {
        GoogleResponse g = find(id);
        int iend = g.getDescription().indexOf("|");

        if (iend != -1)
            g.setFinalDescription(g.getDescription().substring(0, iend));
        else
            g.setFinalDescription(g.getDescription());

    }

    @Transactional(value = TxType.REQUIRES_NEW)
    public void saveCosineSim(List<GoogleResponse> gList, String ocr, Word2Vec word2Vec) {

        for (GoogleResponse g : gList) {
            double sim = cosineSimForSentence(word2Vec, ocr, g.getDescription());
            g.setCosineSim(sim);
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

}
