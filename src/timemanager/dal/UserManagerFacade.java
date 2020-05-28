package timemanager.dal;

import java.util.ArrayList;
import timemanager.be.User;

/**
 *
 * @author andreasvillumsen
 */
public interface UserManagerFacade {
    /**
     * Get users
     * @return users
     */
    public ArrayList<User> getUsers();
    
    /**
     * Get user by its id
     * @param id
     * @return User
     */
    public User getUserById(int id);
    
    /**
     * Get user by its username
     * @param username
     * @return User
     */
    public User getUserByName(String username);
    
    /**
     * Store a user to db
     * @param user
     * @return boolean
     */
    public boolean storeUser(User user);
    
    /**
     * Update a given user
     * @param user
     * @return boolean
     */
    public boolean updateUser(User user);
    
    /**
     * Delete a given user from db
     * @param user
     * @return boolean
     */
    public boolean deleteUser(User user);
}
