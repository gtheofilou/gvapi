package gr.gt.gvapi.dao;

import org.springframework.stereotype.Repository;
import gr.gt.gvapi.entity.Cluster;
import gr.gt.gvapi.entity.ClusterId;

@Repository("ClusterDao")
public class ClusterDao extends AbstractDao<Cluster, ClusterId> {


}
