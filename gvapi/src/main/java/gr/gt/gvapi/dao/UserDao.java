package gr.gt.gvapi.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import gr.gt.gvapi.entity.User;
import gr.gt.gvapi.entity.User_;

@Repository("UserDao")
public class UserDao extends AbstractDao<User, Long> {

    public User findUserByName(String name) {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> q = c.createQuery(User.class);
        Root<User> r = q.from(User.class);

        q.where(c.equal(r.get(User_.NAME), name));
        return getSingleOptional(entityManager.createQuery(q));
    }

    @SuppressWarnings("unused")
    public List<User> getUsers() {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> q = c.createQuery(User.class);
        Root<User> r = q.from(User.class);

        return entityManager.createQuery(q).getResultList();
    }

}
