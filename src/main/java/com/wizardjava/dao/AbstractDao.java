package com.wizardjava.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public abstract class AbstractDao<PK extends Serializable, T> extends HibernateDaoSupport {

    private final Class<T> persistentClass;

    public AbstractDao(){
        this.persistentClass = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public T getByKey(PK key) {
        return (T)getHibernateTemplate().get(persistentClass, key);
    }

    public void save(T entity) {
        getHibernateTemplate().persist(entity);
        getHibernateTemplate().flush();

    }

    public void update(T entity) {
        getHibernateTemplate().merge(entity);
        getHibernateTemplate().flush();

    }

    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    public void deleteById(long id) {
        getHibernateTemplate().delete(getHibernateTemplate().get(persistentClass, id));
        getHibernateTemplate().flush();
    }

    public List<T> getEntityWithPagination(int offset, int recordsPerPage) {
        DetachedCriteria criteria = DetachedCriteria.forClass(persistentClass);
        return (List<T>)getHibernateTemplate().findByCriteria(criteria, offset, recordsPerPage);
    }
}

