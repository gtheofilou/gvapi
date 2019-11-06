package gr.gt.gvapi.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import gr.gt.gvapi.entity.File;

@Repository("FileDao")
public class FileDao extends AbstractDao<File, Long> {

	@SuppressWarnings("unused")
	public List<File> getFileList() {

		CriteriaBuilder c = entityManager.getCriteriaBuilder();
		CriteriaQuery<File> q = c.createQuery(File.class);
		Root<File> r = q.from(File.class);

		return entityManager.createQuery(q).getResultList();
	}

}
