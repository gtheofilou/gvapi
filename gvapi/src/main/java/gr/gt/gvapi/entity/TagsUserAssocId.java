package gr.gt.gvapi.entity;

import java.io.Serializable;

public class TagsUserAssocId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long tagId;
    private Long userId;

    public TagsUserAssocId() {}

    public TagsUserAssocId(Long tagId, Long userId) {
        super();
        this.tagId = tagId;
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
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
        TagsUserAssocId other = (TagsUserAssocId) obj;
        if (tagId == null) {
            if (other.tagId != null)
                return false;
        } else if (!tagId.equals(other.tagId))
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
        return "TagsUserAssocId [tagId=" + tagId + ", userId=" + userId + "]";
    }

}
