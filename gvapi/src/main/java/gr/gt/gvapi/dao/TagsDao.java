package gr.gt.gvapi.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import gr.gt.gvapi.entity.Tags;
import gr.gt.gvapi.entity.Tags_;

@Repository("TagsDao")
public class TagsDao extends AbstractDao<Tags, Long> {

    public Tags peristIfNotExists(String name) {

        Tags tag = findByName(name);
        if (tag == null) {
            tag = new Tags();
            tag.setName(name);
            persist(tag);
        }

        return tag;
    }

    public Tags findByName(String name) {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tags> q = c.createQuery(Tags.class);
        Root<Tags> r = q.from(Tags.class);

        q.where(c.equal(r.get(Tags_.NAME), name));
        return getSingleOptional(entityManager.createQuery(q));
    }

}
