package com.wizardjava.dao.impl;

import com.wizardjava.dao.AbstractDao;
import com.wizardjava.dao.CategoryDao;
import com.wizardjava.entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("categoryDao")
public class CategoryDaoImpl extends AbstractDao<Long, Category> implements CategoryDao {

    @Autowired
    public CategoryDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
        getHibernateTemplate().setCheckWriteOperations(false);

    }

    @Override
    public Category getById(Long id) {
        return getByKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCategory(Category category) {
        save(category);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCategory(Category category) {
        update(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) getHibernateTemplate().find("select T from Category T");
    }

    @Override
    public List<Category> getCategoriesWithPagination(int offset, int recordPerPage) {
        return getEntityWithPagination(offset, recordPerPage);
    }

    @Override
    public long getCountCategories() {
        return DataAccessUtils.longResult(getHibernateTemplate().find("select count(*) from Category"));
    }

    @Override
    public void deleteCategoryById(long id) {
        deleteById(id);
    }
}
