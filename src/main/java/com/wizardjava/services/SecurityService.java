package com.wizardjava.services;

/**
 * Created by Codeine on 13.11.2016.
 */
public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
