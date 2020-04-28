/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.dal;

import java.util.ArrayList;
import timemanager.be.User;

/**
 *
 * @author andreasvillumsen
 */
public interface UserManagerFacade {
    public ArrayList<User> getUsers();
    public User getUserById(int id);
    public User getUserByName(String username);
    public boolean storeUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
}
