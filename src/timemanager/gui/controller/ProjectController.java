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
import timemanager.gui.model.ProjectModel;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen
 */
public class ProjectController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    ProjectModel pm;

    @FXML
    private JFXComboBox<Project> selectProject;
    @FXML
    private JFXButton timerButton;
    @FXML
    private Label timeSpent;
    @FXML
    private JFXButton timerButton1;
    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<Project, String> name;
    @FXML
    private TableColumn<Project, Double> salary;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            pm = ProjectModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        projectTable.setItems(pm.getProjects());
    }    

    @FXML
    private void openTimeLogger(MouseEvent event) throws Exception {
        tms.set((Stage) (selectProject.getScene().getWindow()), "TimeLogger");
    }

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
    private void toggleTimer(ActionEvent event) {
    }

    @FXML
    private void openAddProject(MouseEvent event) throws IOException {
        tms.popup("ProjectCreate");
    }
    
}
