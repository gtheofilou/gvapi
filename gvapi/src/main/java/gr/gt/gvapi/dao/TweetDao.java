package gr.gt.gvapi.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import gr.gt.gvapi.entity.Tweet;
import gr.gt.gvapi.entity.Tweet_;

@Repository("TweetDao")
public class TweetDao extends AbstractDao<Tweet, Long> {


    public List<Tweet> getUserTweets(Long userID) {
        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tweet> q = c.createQuery(Tweet.class);
        Root<Tweet> r = q.from(Tweet.class);

        q.where(c.equal(r.get(Tweet_.USER_ID), userID));

        return entityManager.createQuery(q).getResultList();
    }

}
