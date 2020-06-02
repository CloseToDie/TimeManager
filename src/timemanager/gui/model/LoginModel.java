package timemanager.gui.model;

import java.io.IOException;
import timemanager.be.User;
import timemanager.bll.LoginManager;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class LoginModel {
    private static LoginModel single_instance = null; 
    private LoginManager lm;
    private User user = null;
    
    /**
     * Get LoginModel instance
     * @return LoginModel
     * @throws IOException 
     */
    public static LoginModel getInstance() throws IOException {
        if (single_instance == null) 
            single_instance = new LoginModel(); 
  
        return single_instance; 
    }
    
    /**
     * LoginModel constructor
     * @throws IOException 
     */
    private LoginModel() throws IOException {
        lm = new LoginManager();
    }
    
    /**
     * Take username and password, and log in the user.
     * @param username
     * @param password
     * @return user
     */
    public User login(String username, String password) {
        user = lm.login(username, password);
        return user;
    }
    
    /**
     * Get the logged in user
     * @return user
     */
    public User getLoggedInUser() {
        return user;
    }
}
