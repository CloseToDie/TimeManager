package timemanager.gui.model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import timemanager.be.User;
import timemanager.bll.UserManager;

/**
 *
 * @author andreasvillumsen
 */
public class UserModel {
    private UserManager um;

    public UserModel() throws IOException {
        um = new UserManager();
    }
    
    public void storeUser(String name, String email, String password){
        try {
            um.storeUser(name, email, password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateUser(User user) {
        
    }
}
