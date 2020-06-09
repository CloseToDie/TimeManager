/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import timemanager.be.User;
import timemanager.dal.UserManagerFacade;
import timemanager.dal.database.UserManagerDBDAO;
import timemanager.utils.auth.Password;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class UserManager {
    public ArrayList<User> users = new ArrayList<>();
    UserManagerFacade um;
    
    /**
     * UserManager constructor
     * @throws IOException 
     */
    public UserManager() throws IOException {
        um = new UserManagerDBDAO();
    }
    
    /**
     * Get a list of users
     * @return users
     */
    public ArrayList<User> getUsers() {
        users = um.getUsers();
        return users; // return um.getUsers();
    }
    
    /**
     * Get a user by its id
     * @param id
     * @return user
     */
    public User getUserById(int id) {
        return um.getUserById(id);
    }
    
    /**
     * Get a user by its username
     * @param name
     * @return user
     */
    public User getUserByName(String name) {
        return um.getUserByName(name);
    }
    
    /**
     * Store a new user to database
     * @param name
     * @param email
     * @param password
     * @param isAdmin
     * @return boolean
     * @throws NoSuchAlgorithmException 
     */
    public boolean storeUser(String name, String email, String password, Boolean isAdmin) throws NoSuchAlgorithmException {
        byte[] salt = Password.getSalt();
        return um.storeUser(new User(0, name, email, Password.hash(password, salt, "SHA-512"), salt, isAdmin));
    }
    
    /**
     * Update a user in database
     * @param user
     * @return boolean
     */
    public boolean updateUser(User user) {
        return um.updateUser(user);
    }
    
    /**
     * Deleting a user from database
     * @param user
     * @return boolean
     */
    public boolean deleteUser(User user) {
        return um.deleteUser(user);
    }
}
