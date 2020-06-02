package timemanager.gui.model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.User;
import timemanager.bll.UserManager;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class UserModel {
    private static UserModel single_instance = null; 
    private UserManager um;
    private ObservableList<User> users;

    /**
     * Get the UserModel instance
     * @return UserModel
     * @throws IOException 
     */
    public static UserModel getInstance() throws IOException {
        if (single_instance == null) 
            single_instance = new UserModel(); 
  
        return single_instance; 
    }
    
    /**
     * UserModel constructor
     * @throws IOException 
     */
    private UserModel() throws IOException {
        um = new UserManager();
        users = FXCollections.observableArrayList();
    }
    
    /**
     * Refresh the users arraylist data.
     */
    public void refreshData() {
        users.clear();
        users.addAll(um.getUsers());
    }
    
    /**
     * Get all users.
     * @return ObservableList of User
     */
    public ObservableList<User> getUsers() {
        refreshData();
        return users;
    }
    
    /**
     * Store a user to database
     * @param name
     * @param email
     * @param password
     * @param isAdmin 
     */
    public void storeUser(String name, String email, String password, Boolean isAdmin){
        try {
            um.storeUser(name, email, password, isAdmin);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshData();
    }
    
    /**
     * Update a user to database
     * @param user 
     */
    public void updateUser(User user) {
        um.updateUser(user);
        refreshData();
    }

    /**
     * Delete a user form database.
     * @param user 
     */
    public void deleteUser(User user) {
        um.deleteUser(user);
        refreshData();
    }
}
