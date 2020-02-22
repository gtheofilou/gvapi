package gr.gt.gvapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "Cluster")
@IdClass(ClusterId.class)
public class Cluster {

    @Id
    private Long runID;

    @Id
    private String label;

    private String clusterID;

    public Long getRunID() {
        return runID;
    }

    public void setRunID(Long runID) {
        this.runID = runID;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getClusterID() {
        return clusterID;
    }

    public void setClusterID(String clusterID) {
        this.clusterID = clusterID;
    }

}
