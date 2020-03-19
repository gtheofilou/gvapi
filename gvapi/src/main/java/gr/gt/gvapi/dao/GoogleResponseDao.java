package gr.gt.gvapi.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import org.springframework.stereotype.Repository;
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

}
