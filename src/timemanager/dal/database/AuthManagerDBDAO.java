package timemanager.dal.database;

import java.io.IOException;
import timemanager.be.User;
import timemanager.dal.AuthManagerFacade;
import timemanager.dal.UserManagerFacade;
import timemanager.utils.auth.Password;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class AuthManagerDBDAO implements AuthManagerFacade{
    User user = null;
    UserManagerFacade um;

    /**
     * AuthManagerDBDAO Constructor
     * @throws IOException 
     */
    public AuthManagerDBDAO() throws IOException {
        um = new UserManagerDBDAO();
    }
    
    /**
     * Login the user if the password is correct
     * @param username
     * @param pass
     * @return user
     */
    @Override
    public User login(String username, String pass) {
        User user = um.getUserByName(username);
        
        if(user == null) return null;
        
        if(passMatches(user, pass)) {
            return user;
        } else {
            return null;
        }
    }
    
    /**
     * Check if the given user's password
     * matches the given plain text password.
     * @param user
     * @param pass
     * @return boolean
     */
    private boolean passMatches(User user, String pass) {
        return Password.verifyPassword(pass, user.getPassword(), 
                user.getSalt(), "SHA-512");
    }
}
