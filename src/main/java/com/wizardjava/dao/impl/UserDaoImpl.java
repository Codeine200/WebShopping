package com.wizardjava.dao.impl;

import com.wizardjava.dao.AbstractDao;
import com.wizardjava.dao.UserDao;
import com.wizardjava.entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Codeine on 13.11.2016.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
        getHibernateTemplate().setCheckWriteOperations(false);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        String query = "select T from User T where username = ?";
        Object[] params  = {username};
        List<User> res = (List<User>)getHibernateTemplate().find(query, params);
        return (res.size() != 0) ? res.get(0) : null;
    }

    @Override
    public User getById(Long id) {
        return getByKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user) {
        save(user);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) getHibernateTemplate().find("select T from User T");
    }

    @Override
    public List<User> getUsersWithPagination(int offset, int recordPerPage) {
        return getEntityWithPagination(offset, recordPerPage);
    }

    @Override
    public long getCountUsers() {
        return DataAccessUtils.longResult(getHibernateTemplate().find("select count(*) from User"));
    }

    @Override
    public List<User> getUsersWithPaginationByRoleFilter(int offset, int recordPerPage, Long idRole) {
        List<User> res = getUsersWithPagination(offset, recordPerPage);
        return res;
    }

    @Override
    public long getCountUsersByRoleFilter(Long idCategory) {
        long res = getCountUsers();
        return res;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserById(Long id) {
        deleteById(id);
    }
}
