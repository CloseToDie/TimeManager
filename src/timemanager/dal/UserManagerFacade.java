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
