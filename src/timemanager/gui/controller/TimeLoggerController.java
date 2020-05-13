package timemanager.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.time.LocalDateTime;
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
import timemanager.be.Timer;
import timemanager.gui.model.ProjectModel;
import timemanager.gui.model.TimeLoggerModel;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen
 */
public class TimeLoggerController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    TimeLoggerModel tlm;
    ProjectModel pm;

    @FXML
    private JFXComboBox<Project> selectProject;
    @FXML
    private JFXButton timerButton;
    @FXML
    private Label timeSpent;
    @FXML
    private TableView<Timer> timeTable;
    @FXML
    private TableColumn<Timer, LocalDateTime> started;
    @FXML
    private TableColumn<Timer, LocalDateTime> ended;
    @FXML
    private TableColumn<Timer, Double> timespent;
    @FXML
    private TableColumn<Project, String> project;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tlm = TimeLoggerModel.getInstance();
            pm = ProjectModel.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(TimeLoggerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        started.setCellValueFactory(new PropertyValueFactory<>("started"));
        ended.setCellValueFactory(new PropertyValueFactory<>("ended"));
        timespent.setCellValueFactory(new PropertyValueFactory<>("timespent"));
        
        timeTable.setItems(tlm.getTimers());
        
        selectProject.setItems(pm.getProjects());
    }    

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

    
}
