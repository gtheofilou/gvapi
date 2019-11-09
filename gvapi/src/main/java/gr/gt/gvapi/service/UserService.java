package gr.gt.gvapi.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gr.gt.gvapi.dao.UserDao;
import gr.gt.gvapi.dto.UserDto;
import gr.gt.gvapi.entity.User;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

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

}
