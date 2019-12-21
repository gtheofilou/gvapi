package gr.gt.gvapi.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gr.gt.gvapi.dao.CouchDBDao;
import gr.gt.gvapi.entity.CouchDBEntity;

@Service
@Transactional
public class CouchDBService {

    @Autowired
    private CouchDBDao couchDBDao;

    public CouchDBEntity find(String id) {
        return couchDBDao.find(id);
    }

}
