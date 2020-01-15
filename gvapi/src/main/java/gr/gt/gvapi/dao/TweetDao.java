package gr.gt.gvapi.dao;

import org.springframework.stereotype.Repository;
import gr.gt.gvapi.entity.Tweet;

@Repository("TweetDao")
public class TweetDao extends AbstractDao<Tweet, Long> {


}
