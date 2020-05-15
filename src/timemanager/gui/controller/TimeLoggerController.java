package timemanager.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    Timeline timeline;
    
    int i = 0;

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
    @FXML
    private JFXButton pauseButton;

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
        
        setupTable();
        
        timerButton.setOnAction(e -> toggleTimer());
        pauseButton.setOnAction(e -> pauseTimer());
        
        selectProject.setItems(pm.getProjects());
        
        setupTimeline();
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
    
    private void toggleTimer() {
        if(timerButton.getText().equals("START")) {
            if(!selected(selectProject)) return;
            timerButton.setText("STOP");
            pauseButton.setDisable(false);
            tlm.start(16);
            timeline.play();
        } else if(timerButton.getText().equals("STOP")) {
            timerButton.setText("START");
            pauseButton.setText("PAUSE");
            pauseButton.setDisable(true);
            tlm.stop();
            timeline.stop();
        }
    }
    
    private void pauseTimer() {
        if(pauseButton.getText().equals("PAUSE")) {
            pauseButton.setText("PAUSED");
            tlm.pause();
            timeline.pause();
        } else if(pauseButton.getText().equals("PAUSED")) {
            pauseButton.setText("PAUSE");
            tlm.unpause();
            timeline.play();
        }
    }
    
    private void setupTable() {
        started.setCellValueFactory(new PropertyValueFactory<>("started"));
        ended.setCellValueFactory(new PropertyValueFactory<>("ended"));
        timespent.setCellValueFactory(new PropertyValueFactory<>("timespent"));
        
        timeTable.setItems(tlm.getTimers());
    }

    private void setupTimeline() {
        timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                i++;
                timeSpent.setText(LocalTime.ofSecondOfDay(tlm.totalSpentTime()).format(formatter));
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    
    private boolean selected(JFXComboBox combo) {
        return combo.getValue() != null;
    }
    
}
