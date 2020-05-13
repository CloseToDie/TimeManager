package timemanager.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import timemanager.TimeManagerStart;
import timemanager.gui.model.UserModel;
import timemanager.utils.validation.Validate;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen
 */
public class UserCreateController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    UserModel um;
    
    private Label errorLabel;
    @FXML
    private Button saveUserButton;
    @FXML
    private Button cancelSaveUser;
    @FXML
    private ComboBox<String> selectRole;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;
    @FXML
    private TextField userEmail;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            um = UserModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        selectRole.setItems(um.getRoles());
    }
    @FXML
    private void saveUser(ActionEvent event) {
        String username = userName.getText();
        String email = userEmail.getText();
        String password = userPassword.getText();
        
        if(validateName(username)) {
            um.storeUser(username, email, password);
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
    
    
}
