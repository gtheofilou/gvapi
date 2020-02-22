package gr.gt.gvapi.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ClusterConf")
public class ClusterConf {

    @Id
    private Long runID;

    @NotNull
    private Integer clusters;

    @NotNull
    private Integer iterations;

    @NotNull
    private Long workTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    public Long getRunID() {
        return runID;
    }

    public void setRunID(Long runID) {
        this.runID = runID;
    }

    public Integer getClusters() {
        return clusters;
    }

    public void setClusters(Integer clusters) {
        this.clusters = clusters;
    }

    public Integer getIterations() {
        return iterations;
    }

    public void setIterations(Integer iterations) {
        this.iterations = iterations;
    }

    public Long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Long workTime) {
        this.workTime = workTime;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

}
