package gr.gt.gvapi.dao;

import org.springframework.stereotype.Repository;
import gr.gt.gvapi.entity.FileUserAssoc;
import gr.gt.gvapi.entity.FileUserAssocId;

@Repository("FileUserAssocDao")
public class FileUserAssocDao extends AbstractDao<FileUserAssoc, FileUserAssocId> {

    public FileUserAssoc createFileUserAssocIfNotExists(Long fileId, Long userId) {

        FileUserAssoc fua = find(new FileUserAssocId(fileId, userId));
        if (fua == null) {
            fua = new FileUserAssoc();
            fua.setFileId(fileId);
            fua.setUserId(userId);
            persist(fua);
        }

        return fua;
    }

}
