package gr.gt.gvapi.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

}
