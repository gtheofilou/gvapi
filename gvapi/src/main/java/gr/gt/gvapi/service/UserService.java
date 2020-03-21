package gr.gt.gvapi.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gr.gt.gvapi.dao.TagsDao;
import gr.gt.gvapi.dao.TagsUserAssocDao;
import gr.gt.gvapi.dao.UserDao;
import gr.gt.gvapi.dto.UserDto;
import gr.gt.gvapi.entity.Tags;
import gr.gt.gvapi.entity.User;
import gr.gt.gvapi.utils.ThematicCategories;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TagsDao tagsDao;
    @Autowired
    private TagsUserAssocDao tagsUserAssocDao;

    public User add(UserDto userDto) {
        User user = userDao.findUserByName(userDto.getName());
        if (user == null) {
            user = new User();
            user.setName(userDto.getName());
            userDao.persist(user);
        }
        return user;
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public void setParty() {
        List<User> usersList = getUsers();

        for (User u : usersList) {
            List<String> tags = ThematicCategories.getCategoriesMap().get(u.getName());

            if (tags == null || !tags.contains("Politics"))
                continue;

            u.setCategory("Politics");

            String party = null;
            for (String tag : tags) {
                if (User.parties.contains(tag)) {
                    party = User.party(tag);
                    break;
                }
            }
            u.setParty(party);
        }

    }

    public void setTags() {
        List<User> usersList = getUsers();

        for (User u : usersList) {
            List<String> tags = ThematicCategories.getCategoriesMap().get(u.getName());

            // run only for the required
            if (tags == null || !tags.contains("Politics"))
                continue;

            for (String tag : tags) {
                Tags t = tagsDao.peristIfNotExists(tag);
                tagsUserAssocDao.createTagsUserAssocIfNotExists(t.getId(), u.getId());
            }
        }

    }

}
