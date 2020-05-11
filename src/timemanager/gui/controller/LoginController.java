/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import timemanager.TimeManagerStart;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen
 */
public class LoginController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(null);
        password.setText(null);
    }    

    @FXML
    private void login(ActionEvent event) throws IOException, Exception {
        if(username.getText().equals("andreas") && password.getText().equals("password")) {
            tms.set((Stage) (username.getScene().getWindow()), "TimeLogger");
        }
    }
    
    private boolean checkUsernameIsValid() {
        return username != null;
    }
    
    private boolean checkPasswordIsValid() {
        return password != null;
    }
    
}
