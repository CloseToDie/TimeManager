package timemanager.dal.database;

import java.io.IOException;
import timemanager.be.User;
import timemanager.dal.UserManagerFacade;
import timemanager.utils.auth.Password;

/**
 *
 * @author andreasvillumsen
 */
public class AuthManagerDBDAO {
    UserManagerFacade um;

    public AuthManagerDBDAO() throws IOException {
        um = new UserManagerDBDAO();
    }
    
    public User login(String username, String pass) {
        // Find user from database.
        User user = um.getUserByName(username);
        // Verify password
        if(passMatches(user, pass)) {
            return user;
        } else {
            return null;
        }
    }
    
    public boolean passMatches(User user, String pass) {
        return Password.verifyPassword(pass, user.getPassword(), 
                user.getSalt(), "SHA-512");
    }
}
