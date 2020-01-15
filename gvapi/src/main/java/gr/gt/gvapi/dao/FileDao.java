package gr.gt.gvapi.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.entity.File_;

@Repository("FileDao")
public class FileDao extends AbstractDao<File, Long> {

    public List<File> getFileList() {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<File> q = c.createQuery(File.class);
        Root<File> r = q.from(File.class);

        return entityManager.createQuery(q).setMaxResults(500).getResultList();
    }

    public File findFileNByName(String name) {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<File> q = c.createQuery(File.class);
        Root<File> r = q.from(File.class);

        q.where(c.equal(r.get(File_.NAME), name));
        return getSingleOptional(entityManager.createQuery(q));
    }

    public List<File> getFileListNotSent() {

        CriteriaBuilder c = entityManager.getCriteriaBuilder();
        CriteriaQuery<File> q = c.createQuery(File.class);
        Root<File> r = q.from(File.class);

        q.where(c.isNull(r.get(File_.SENT)));

        // return entityManager.createQuery(q).setMaxResults(100).getResultList();
        return entityManager.createQuery(q).getResultList();
    }



}
