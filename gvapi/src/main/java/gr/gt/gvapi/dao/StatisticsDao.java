package gr.gt.gvapi.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import gr.gt.gvapi.sql.Statistics;

@Repository("StatisticsDao")
public class StatisticsDao extends AbstractDao<Long, Long> {

    public List<?> mostRecentPerUser(String user, String dataSource, String type, Integer limit) {
        Query query = null;
        // Labels
        if ("labels".equals(dataSource)) {

            if ("count".equals(type))
                query = entityManager.createNativeQuery(Statistics.LABELS_BY_COUNT_PER_USER);
            else if ("avg".equals(type))
                query = entityManager.createNativeQuery(Statistics.LABELS_BY_AVG_PER_USER);
            else
                return Collections.emptyList();

            query.setParameter("user", user);
            query.setParameter("limit", limit);

            return query.getResultList();

        } // OCR
        else if ("ocr".equals(dataSource)) {

            if ("tf".equals(type))
                query = entityManager.createNativeQuery(Statistics.OCR_TF);
            else if ("tfidf".equals(type))
                query = entityManager.createNativeQuery(Statistics.OCR_TFIDF);
            else
                return Collections.emptyList();

            query.setParameter("user", user);
            query.setParameter("limit", limit);

            return query.getResultList();
        } else
            return Collections.emptyList();

    }
}
