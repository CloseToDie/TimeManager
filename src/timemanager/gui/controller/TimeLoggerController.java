package timemanager.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import timemanager.TimeManagerStart;
import timemanager.be.Client;
import timemanager.be.Project;
import timemanager.be.Task;
import timemanager.be.Timer;
import timemanager.gui.model.ClientModel;
import timemanager.gui.model.LoginModel;
import timemanager.gui.model.ProjectModel;
import timemanager.gui.model.TaskModel;
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
    ClientModel cm;
    TaskModel tm;
    LoginModel lm;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    Timeline timeline;

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
    @FXML
    private JFXComboBox<Task> selectTask;
    @FXML
    private JFXComboBox<Client> selectClient;
    @FXML
    private JFXCheckBox billable;
    @FXML
    private HBox timeLoggerLink;
    @FXML
    private HBox clientsLink;
    @FXML
    private HBox usersLink;
    @FXML
    private HBox statisticsLink;

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
            cm = ClientModel.getInstance();
            tm = TaskModel.getInstance();
            lm = LoginModel.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(TimeLoggerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setupTable();
        
        timerButton.setOnAction(e -> toggleTimer());
        pauseButton.setOnAction(e -> pauseTimer());
        
        selectClient.setItems(cm.getClients());
        
        setupTimeline();
        
        initStartTimeline();
        
        isAdmin();
    }
    
    private void isAdmin() {
        if(lm.getLoggedInUser().getIsAdmin() == true) {
            timeLoggerLink.setDisable(false);
            timeLoggerLink.setVisible(true);
            clientsLink.setDisable(false);
            clientsLink.setVisible(true);
            usersLink.setDisable(false);
            usersLink.setVisible(true);
            statisticsLink.setDisable(false);
            statisticsLink.setVisible(true);
            
        } else {
            usersLink.setDisable(true);
            usersLink.setVisible(false);
            statisticsLink.setDisable(true);
            statisticsLink.setVisible(false);
        }
    }
    
    @FXML
    private void openClients(MouseEvent event) throws Exception {
        timeline.stop();
        tms.set((Stage) (selectProject.getScene().getWindow()), "Client");
    }

    @FXML
    private void openUsers(MouseEvent event) throws Exception {
        timeline.stop();
        tms.set((Stage) (selectProject.getScene().getWindow()), "User");
    }

    @FXML
    private void openStatistics(MouseEvent event) throws Exception {
        timeline.stop();
        tms.set((Stage) (selectProject.getScene().getWindow()), "Statistics");
    }
    
    private void toggleTimer() {
        if(timerButton.getText().equals("START")) {
            if(!selected(selectClient) || !selected(selectProject) || !selected(selectTask)) return;
            start();
        } else if(timerButton.getText().equals("STOP")) {
            stop();
        }
    }
    
    private void pauseTimer() {
        if(pauseButton.getText().equals("PAUSE")) {
            pause();
        } else if(pauseButton.getText().equals("RESUME")) {
            resume();
        }
    }
    
    private void start() {
        timerButton.setText("STOP");
        pauseButton.setDisable(false);
        tlm.start(selectTask.getValue().getId(), billable.isSelected(), lm.getLoggedInUser().getId());
        disableCombos(true);
        
        //timeline.play();
    }
    
    private void stop() {
        timerButton.setText("START");
        pauseButton.setText("PAUSE");
        pauseButton.setDisable(true);
        tlm.stop();
        disableCombos(false);
        //timeline.stop();
    }
    
    private void pause() {
        pauseButton.setText("RESUME");
        tlm.pause();
        //timeline.pause();
    }
    
    private void resume() {
        pauseButton.setText("PAUSE");
        tlm.unpause();
        //timeline.play();
    }

    private void setupTimeline() {
        timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                timeSpent.setText(tlm.totalSpentTimeString());
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    
    private boolean selected(JFXComboBox combo) {
        return combo.getValue() != null;
    }

    private void initStartTimeline() {
        timeline.play();
        if(tlm.timerRunning() && tlm.lastTimer() != null) {
            timerButton.setText("STOP");
            pauseButton.setText("PAUSE");
            pauseButton.setDisable(false);
            disableCombos(true);
            //timeline.play();
        } else if (!tlm.timerRunning() && tlm.lastTimer() != null) {
            timerButton.setText("STOP");
            pauseButton.setText("RESUME");
            pauseButton.setDisable(false);
            disableCombos(true);
            //timeline.pause();
        }
        
    }

    @FXML
    private void selectClient(ActionEvent event) {
        System.out.println(selectClient.getValue());
        System.out.println(selectClient.getSelectionModel().isEmpty());
        
        if(!selectClient.getSelectionModel().isEmpty()) {
            selectProject.setDisable(false);
            selectProject.setItems(pm.getClientProjects(selectClient.getValue().getId()));
            selectTask.setDisable(true);
        } else {
            selectProject.setDisable(true);
        }
    }

    @FXML
    private void selectProject(ActionEvent event) {
        System.out.println(selectProject.getValue());
        System.out.println(selectProject.getSelectionModel().isEmpty());
        
        if(!selectProject.getSelectionModel().isEmpty()) {
            selectTask.setDisable(false);
            selectTask.setItems(tm.getTasks(selectProject.getValue().getId()));
        } else {
            selectTask.setDisable(true);
        }
    }

    @FXML
    private void selectTask(ActionEvent event) {
        System.out.println(selectTask.getValue());
        System.out.println(selectTask.getSelectionModel().isEmpty());
        
        if(!selectTask.getSelectionModel().isEmpty()) {
            timeTable.setItems(tlm.getTimers(selectTask.getValue().getId()));
        } else {
            timeTable.setItems(null);
        }
    }

    private void disableCombos(boolean b) {
        selectClient.setDisable(b);
        selectProject.setDisable(b);
        selectTask.setDisable(b);
        billable.setDisable(b);
    }
    
    private void setupTable() {
        started.setCellFactory(column -> {
            TableCell<Timer, LocalDateTime> cell = new TableCell<Timer, LocalDateTime>() {

                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(formatter.format(item));

                    }
                }
            };

            return cell;
        });
        started.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        ended.setCellFactory(column -> {
            TableCell<Timer, LocalDateTime> cell = new TableCell<Timer, LocalDateTime>() {

                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(formatter.format(item));

                    }
                }
            };

            return cell;
        });
        ended.setCellValueFactory(new PropertyValueFactory<>("stopTime"));
        timespent.setCellValueFactory(new PropertyValueFactory<>("spentTime"));
        timeTable.setItems(tlm.getTimers());
    }
}
