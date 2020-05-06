package timemanager.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author andreasvillumsen
 */
public class UserController implements Initializable {

    @FXML
    private JFXComboBox<?> selectProject;
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
        // TODO
    }    

    @FXML
    private void saveUser(ActionEvent event) {
        
    }

    @FXML
    private void openTimeLogger(MouseEvent event) {
    }

    @FXML
    private void openProjects(MouseEvent event) {
    }

    @FXML
    private void openClients(MouseEvent event) {
    }

    @FXML
    private void openStatistics(MouseEvent event) {
    }

    @FXML
    private void openSettings(MouseEvent event) {
    }
}
