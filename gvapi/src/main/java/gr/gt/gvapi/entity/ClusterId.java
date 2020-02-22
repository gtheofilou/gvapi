package gr.gt.gvapi.entity;

import java.io.Serializable;

public class ClusterId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long runID;
    private String label;

    public ClusterId() {}


    public ClusterId(Long runID, String label) {
        super();
        this.runID = runID;
        this.label = label;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
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
        ClusterId other = (ClusterId) obj;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
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
        return "ClusterId [runID=" + runID + ", label=" + label + "]";
    }

}
