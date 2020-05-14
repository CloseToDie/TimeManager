package timemanager.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import timemanager.TimeManagerStart;
import timemanager.gui.model.LoginModel;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen
 */
public class LoginController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    LoginModel lm;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lm = LoginModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        username.setText(null);
        password.setText(null);
    }    

    @FXML
    private void login(ActionEvent event) throws IOException, Exception {
        if(lm.login(username.getText(), password.getText()) != null) {
            tms.set((Stage) (username.getScene().getWindow()), "TimeLogger");
        } else {
            // Tell the user, we could not log them in.
            System.out.println("Login failed!");
            errorLabel.setVisible(true);
        }
    }
    
    private boolean checkUsernameIsValid() {
        return username != null;
    }
    
    private boolean checkPasswordIsValid() {
        return password != null;
    }
    
}
