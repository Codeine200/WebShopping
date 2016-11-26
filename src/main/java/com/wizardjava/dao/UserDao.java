package com.wizardjava.dao;

import com.wizardjava.entity.User;

import java.util.List;

/**
 * Created by Codeine on 13.11.2016.
 */
public interface UserDao {
    User findByUsername(String username);
    User getById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    List<User> getAllUsers();
    List<User> getUsersWithPagination(int offset, int recordPerPage);
    List<User> getUsersWithPaginationByRoleFilter(int offset, int recordPerPage, Long idRole);
    long getCountUsers();
    long getCountUsersByRoleFilter(Long idRole);
    void deleteUserById(Long id);
}
