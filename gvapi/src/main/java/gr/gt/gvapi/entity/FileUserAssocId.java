package gr.gt.gvapi.entity;

import java.io.Serializable;

public class FileUserAssocId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long fileId;
    private Long userId;

    public FileUserAssocId() {}

    public FileUserAssocId(Long fileId, Long userId) {
        super();
        this.fileId = fileId;
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        FileUserAssocId other = (FileUserAssocId) obj;
        if (fileId == null) {
            if (other.fileId != null)
                return false;
        } else if (!fileId.equals(other.fileId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FileUserAssocId [fileId=" + fileId + ", userId=" + userId + "]";
    }

}
