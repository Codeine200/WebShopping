package com.wizardjava.dao.impl;

import com.wizardjava.dao.AbstractDao;
import com.wizardjava.dao.ProductDao;
import com.wizardjava.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Long, Product> implements ProductDao {

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
        getHibernateTemplate().setCheckWriteOperations(false);
    }

    @Override
    public Product getById(Long id) {
        return getByKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveProduct(Product product) {
        save(product);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateProduct(Product product) {
        update(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) getHibernateTemplate().find("select T from Product T");
    }

    @Override
    public List<Product> getProductsWithPagination(int offset, int recordPerPage) {
        return getEntityWithPagination(offset, recordPerPage);
    }

    @Override
    public long getCountProducts() {
        return DataAccessUtils.longResult(getHibernateTemplate().find("select count(*) from Product"));
    }

    @Override
    public List<Product> getProductsWithPaginationByCategoryFilter(int offset, int recordPerPage, Long idCategory) {
        enableCategoryFilter(idCategory);
        List<Product> res = getProductsWithPagination(offset, recordPerPage);
        disableCategoryFilter();
        return res;
    }

    @Override
    public long getCountProductsByCategoryFilter(Long idCategory) {
        enableCategoryFilter(idCategory);
        long res = getCountProducts();
        disableCategoryFilter();
        return res;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProductById(Long id) {
        deleteById(id);
    }

    private void enableCategoryFilter(long categoryId) {
        if(categoryId != 0) {
            Session session = getSessionFactory().getCurrentSession();
            session.enableFilter("categoryFilter").setParameter("categoryID", categoryId);
        }
    }

    private void disableCategoryFilter() {
        Session session = getSessionFactory().getCurrentSession();
        session.disableFilter("categoryFilter");
    }
}
