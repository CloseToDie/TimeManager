package timemanager.gui.controller;

import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import timemanager.TimeManagerStart;
import timemanager.be.User;
import timemanager.gui.model.UserModel;
import timemanager.utils.validation.Validate;
import timemanager.utils.auth.Password;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen
 */
public class UserCreateController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    UserModel um;
    User user = null;
    
    @FXML
    private Label errorLabel;
    @FXML
    private Button saveUserButton;
    @FXML
    private Button cancelSaveUser;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;
    @FXML
    private TextField userEmail;
    @FXML
    private JFXCheckBox isAdmin;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            um = UserModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setUser(User user) {
        this.user = user;
        if(user != null) setupFields();
    }
    
    @FXML
    private void saveUser(ActionEvent event) throws NoSuchAlgorithmException {
        String username = userName.getText();
        String email = userEmail.getText();
        String password = userPassword.getText();
        Boolean admin = isAdmin.isSelected();
        
        if(validateName(username)) {
            if(user != null) {
                byte[] salt = Password.getSalt();
                user.setName(username);
                user.setEmail(email);
                user.setIsAdmin(admin);
                if(!(password == null || "".equals(password))) {
                    user.setSalt(salt);
                    user.setPassword(Password.hash(password, salt, "SHA-512"));
                }
                um.updateUser(user);
            } else {
                um.storeUser(username, email, password, admin);
            }
            
            Stage window = (Stage) (cancelSaveUser.getScene().getWindow());
            window.close();
        } else {
            errorLabel.setVisible(true);
        }
    }
    @FXML
    private void cancelSaveUser(ActionEvent event) {
        Stage window = (Stage) (cancelSaveUser.getScene().getWindow());
        window.close();
    }
    
    private boolean validateName(String name) {
        return !(Validate.isNull(name) || !Validate.containsAtLeast(name, 3));
    }

    private void setupFields() {
        userName.setText(user.getName());
        userEmail.setText(user.getEmail());
        isAdmin.setSelected(user.getIsAdmin());
    }
    
    
}
