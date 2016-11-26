package com.wizardjava.services;

import com.wizardjava.entity.User;

/**
 * Created by Codeine on 13.11.2016.
 */
public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
