package com.wizardjava.dao.impl;

import com.wizardjava.dao.AbstractDao;
import com.wizardjava.dao.ImageDao;
import com.wizardjava.entity.Image;
import com.wizardjava.entity.Product;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("imageDao")
public class ImageDaoImpl extends AbstractDao<Long, Image> implements ImageDao{

    @Autowired
    public ImageDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
        getHibernateTemplate().setCheckWriteOperations(false);
    }

    @Override
    public Image getById(Long id) {
        return getByKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveImage(Image image) {
        save(image);
    }

    @Override
    public List<Image> getAllImages() {
        return (List<Image>)getHibernateTemplate().find("select T from Image T");
    }

    @Override
    public List<Image> getImagesByIdProduct(Long id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Image.class)
                .add(Restrictions.eq("product_id", id));
        return  (List<Image>)getHibernateTemplate().findByCriteria( criteria);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteImageById(Long id) {
        deleteById(id);
    }
}
