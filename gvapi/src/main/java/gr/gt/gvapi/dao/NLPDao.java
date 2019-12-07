package gr.gt.gvapi.dao;

import org.springframework.stereotype.Repository;
import gr.gt.gvapi.entity.NLP;

@Repository("NLPDao")
public class NLPDao extends AbstractDao<NLP, Long> {

    public void truncateTable() {
        entityManager.createNativeQuery("TRUNCATE TABLE NLP").executeUpdate();
    }

}
