package gr.gt.gvapi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import gr.gt.gvapi.entity.CouchDBEntity;

@Repository("CouchDBDao")
public class CouchDBDao {

    @Bean
    private Database db(CloudantClient cloudant) {
        return cloudant.database("influence_tracker", false);
    }

    @Autowired
    private Database db;

    public CouchDBEntity find(String id) {
        return db.find(CouchDBEntity.class, id);
    }

}
