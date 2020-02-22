package gr.gt.gvapi.entity;

import java.io.Serializable;

public class ClusterResultId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long runID;
    private String clusterID;

    public ClusterResultId() {}

    public ClusterResultId(Long runID, String clusterID) {
        super();
        this.runID = runID;
        this.clusterID = clusterID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clusterID == null) ? 0 : clusterID.hashCode());
        result = prime * result + ((runID == null) ? 0 : runID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClusterResultId other = (ClusterResultId) obj;
        if (clusterID == null) {
            if (other.clusterID != null)
                return false;
        } else if (!clusterID.equals(other.clusterID))
            return false;
        if (runID == null) {
            if (other.runID != null)
                return false;
        } else if (!runID.equals(other.runID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ClusterResultId [runID=" + runID + ", clusterID=" + clusterID + "]";
    }

}
