package gr.gt.gvapi.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import gr.gt.gvapi.sql.Statistics;

@Repository("StatisticsDao")
public class StatisticsDao extends AbstractDao<Long, Long> {

    public List<?> mostRecentPerUser(String user, String type, Integer limit) {
        Query query = null;
        if ("count".equals(type))
            query = entityManager.createNativeQuery(Statistics.LABELS_BY_COUNT_PER_USER);
        else if ("avg".equals(type))
            query = entityManager.createNativeQuery(Statistics.LABELS_BY_AVG_PER_USER);
        else
            return Collections.emptyList();

        query.setParameter("user", user);
        query.setParameter("limit", limit);

        return query.getResultList();
    }
}
