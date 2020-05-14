package timemanager.gui.model;

import java.io.IOException;
import timemanager.be.User;
import timemanager.bll.LoginManager;

/**
 *
 * @author andreasvillumsen
 */
public class LoginModel {
    private static LoginModel single_instance = null; 
    private LoginManager lm;
    private User user = null;
    
    public static LoginModel getInstance() throws IOException {
        if (single_instance == null) 
            single_instance = new LoginModel(); 
  
        return single_instance; 
    }
    
    private LoginModel() throws IOException {
        lm = new LoginManager();
    }
    
    public User login(String username, String password) {
        user = lm.login(username, password);
        return user;
    }
    
    public User getLoggedInUser() {
        return user;
    }
}
