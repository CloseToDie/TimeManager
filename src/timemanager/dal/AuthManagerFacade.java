/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.dal;

import timemanager.be.User;

/**
 *
 * @author andreasvillumsen
 */
public interface AuthManagerFacade {
    /**
     * Login the user with username and password
     * @param username
     * @param pass
     * @return User
     */
    public User login(String username, String pass);
}
