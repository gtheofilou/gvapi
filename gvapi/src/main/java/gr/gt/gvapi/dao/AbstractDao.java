package gr.gt.gvapi.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractDao<T, PK extends Serializable> {

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> persistentClass;

    public AbstractDao() {
        this.persistentClass = getGenericTypeClass();
    }

    public T find(PK id) {
        return (T) entityManager.find(persistentClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + persistentClass.getName()).getResultList();
    }

    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public void merge(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @SuppressWarnings("unchecked")
    T getSingleOptional(Query q) {
        List<T> list = q.setMaxResults(2).getResultList();
        if (list.isEmpty())
            return null;
        if (list.size() > 1)
            throw new NonUniqueResultException();
        else
            return list.get(0);
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericTypeClass() {
        try {
            String className = ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0].getTypeName();
            Class<?> clazz = Class.forName(className);
            return (Class<T>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized");
        }
    }

}
