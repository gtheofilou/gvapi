package gr.gt.gvapi.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import gr.gt.gvapi.entity.FileUserAssoc;
import gr.gt.gvapi.entity.FileUserAssocId;
import gr.gt.gvapi.entity.FileUserAssoc_;

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

    public List<FileUserAssoc> getUserID(Long fileId) {
        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<FileUserAssoc> q = c.createQuery(FileUserAssoc.class);
        Root<FileUserAssoc> r = q.from(FileUserAssoc.class);

        q.where(c.equal(r.get(FileUserAssoc_.FILE_ID), fileId));

        return entityManager.createQuery(q).getResultList();
    }

}
