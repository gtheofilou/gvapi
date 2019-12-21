package gr.gt.gvapi.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gr.gt.gvapi.dao.StatisticsDao;

@Service
@Transactional
public class StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;

    public List<?> mostRecentPerUser(String user, String dataSource, String type, Integer limit) {
        return statisticsDao.mostRecentPerUser(user, dataSource, type, limit);
    }
}
