package gr.gt.gvapi.dao;

import org.springframework.stereotype.Repository;
import gr.gt.gvapi.entity.TagsUserAssoc;
import gr.gt.gvapi.entity.TagsUserAssocId;

@Repository("TagsUserAssocDao")
public class TagsUserAssocDao extends AbstractDao<TagsUserAssoc, TagsUserAssocId> {

    public TagsUserAssoc createTagsUserAssocIfNotExists(Long tagsId, Long userId) {

        TagsUserAssoc tua = find(new TagsUserAssocId(tagsId, userId));
        if (tua == null) {
            tua = new TagsUserAssoc();
            tua.setTagId(tagsId);
            tua.setUserId(userId);
            persist(tua);
        }
        return tua;
    }

}
