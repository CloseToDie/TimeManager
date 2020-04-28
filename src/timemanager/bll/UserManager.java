/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import java.io.IOException;
import java.util.ArrayList;
import timemanager.be.User;
import timemanager.dal.UserManagerFacade;
import timemanager.dal.database.UserManagerDBDAO;

/**
 *
 * @author andreasvillumsen
 */
public class UserManager {
    public ArrayList<User> users = new ArrayList<>();
    
    UserManagerFacade um;
    
    public UserManager() throws IOException {
        um = new UserManagerDBDAO();
    }
    
    public ArrayList<User> getUsers() {
        users = um.getUsers();
        return users;
    }
    
    public User getUserById(int id) {
        return um.getUserById(id);
    }
    
    public User getUserByName(String name) {
        return um.getUserByName(name);
    }
    
    public boolean storeUser(User user) {
        return um.storeUser(user);
    }
    
    public boolean updateUser(User user) {
        return um.updateUser(user);
    }
    
    public boolean deleteUser(User user) {
        return um.deleteUser(user);
    }
}
