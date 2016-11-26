package com.wizardjava.dao.impl;

import com.wizardjava.dao.AbstractDao;
import com.wizardjava.dao.RoleDao;
import com.wizardjava.entity.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl  extends AbstractDao<Long, Role> implements RoleDao {

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
        getHibernateTemplate().setCheckWriteOperations(false);
    }

    @Override
    public Role getById(Long id) {
        return getByKey(id);
    }


}
