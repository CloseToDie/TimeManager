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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import timemanager.TimeManagerStart;
import timemanager.be.Project;
import timemanager.be.User;
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
    private JFXButton addUserButton;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private TableColumn<User, String> email;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            um = UserModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        userTable.setItems(um.getUsers());
        
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

    private void openUsers(MouseEvent event) throws Exception {
        tms.set((Stage) (selectProject.getScene().getWindow()), "User");
    }

    @FXML
    private void openStatistics(MouseEvent event) throws Exception {
        tms.set((Stage) (selectProject.getScene().getWindow()), "Statistics");
    }

    @FXML
    private void openAddUser(ActionEvent event) throws IOException {
        tms.popup("UserCreate");
    }
}
