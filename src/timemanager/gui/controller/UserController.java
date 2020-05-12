package timemanager.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import timemanager.TimeManagerStart;
import timemanager.be.Project;
import timemanager.gui.model.UserModel;

/**
 *
 * @author andreasvillumsen
 */
public class UserController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    UserModel um;

    @FXML
    private JFXComboBox<Project> selectProject;
    @FXML
    private JFXButton timerButton;
    @FXML
    private Label timeSpent;
    @FXML
    private Button saveUserButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            um = new UserModel();
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @FXML
    private void openTimeLogger(MouseEvent event) throws Exception {
        tms.set((Stage) (selectProject.getScene().getWindow()), "TimeLogger");
    }

    @FXML
    private void openProjects(MouseEvent event) throws Exception {
        tms.set((Stage) (selectProject.getScene().getWindow()), "Project");
    }
    
    @FXML
    private void openClients(MouseEvent event) throws Exception {
        tms.set((Stage) (selectProject.getScene().getWindow()), "Client");
    }

    @FXML
    private void openUsers(MouseEvent event) throws Exception {
        tms.set((Stage) (selectProject.getScene().getWindow()), "User");
    }

    @FXML
    private void openStatistics(MouseEvent event) throws Exception {
        tms.set((Stage) (selectProject.getScene().getWindow()), "Statistics");
    }
    
    @FXML
    private void saveUser(ActionEvent event) {
        um.storeUser("Andreas", "andreas@lortemail.dk", "password");
    }
}
