package gr.gt.gvapi.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import gr.gt.gvapi.dao.AbstractDao;
import gr.gt.gvapi.dao.TweetDao;
import gr.gt.gvapi.dao.UserDao;
import gr.gt.gvapi.entity.Tweet;
import gr.gt.gvapi.entity.User;

@Service
@Transactional
public class TweetService extends AbstractService<Tweet, Long> {

    private TweetDao tweetDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public TweetService(@Qualifier("TweetDao") AbstractDao<Tweet, Long> abstractDao) {
        super(abstractDao);
        this.tweetDao = (TweetDao) abstractDao;
    }

    public void saveTweet(List<String> textList, String userName) {
        if (textList == null || textList.isEmpty())
            return;
        User user = userDao.findUserByName(userName);
        for (String text : textList) {
            Tweet t = new Tweet();
            t.setUserId(user.getId());
            t.setText(text);

            tweetDao.persist(t);

        }
    }

}
