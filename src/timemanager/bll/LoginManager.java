/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import java.io.IOException;
import timemanager.be.User;
import timemanager.dal.AuthManagerFacade;
import timemanager.dal.database.AuthManagerDBDAO;

/**
 *
 * @author andreasvillumsen
 */
public class LoginManager {
    AuthManagerFacade am;
    
    public LoginManager() throws IOException {
        am = new AuthManagerDBDAO();
    }
    
    public User login(String username, String password) {
        return am.login(username, password);
    }
}
