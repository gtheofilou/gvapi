package gr.gt.gvapi.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import gr.gt.gvapi.dao.AbstractDao;

@Service
@Transactional
public abstract class AbstractService<T, PK extends Serializable> {

    private AbstractDao<T, PK> abstractDao;

    public AbstractService(AbstractDao<T, PK> abstractDao) {
        this.abstractDao = abstractDao;
    }

    public T find(PK id) {
        return (T) abstractDao.find(id);
    }

    public List<T> findAll() {
        return abstractDao.findAll();
    }

    public void persist(T entity) {
        abstractDao.persist(entity);
    }

    public void merge(T entity) {
        abstractDao.merge(entity);
    }

    public void delete(T entity) {
        abstractDao.delete(entity);
    }

}
