package gr.gt.gvapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ClusterResult")
@IdClass(ClusterResultId.class)
public class ClusterResult {

    @Id
    private Long runID;

    @Id
    private String clusterID;

    @NotNull
    private Integer size;

    public Long getRunID() {
        return runID;
    }

    public void setRunID(Long runID) {
        this.runID = runID;
    }

    public String getClusterID() {
        return clusterID;
    }

    public void setClusterID(String clusterID) {
        this.clusterID = clusterID;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
