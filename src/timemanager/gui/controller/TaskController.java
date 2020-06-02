/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import timemanager.TimeManagerStart;
import timemanager.be.Client;
import timemanager.be.Project;
import timemanager.be.Task;
import timemanager.gui.model.ClientModel;
import timemanager.gui.model.LoginModel;
import timemanager.gui.model.ProjectModel;
import timemanager.gui.model.TaskModel;
import timemanager.gui.model.TimeLoggerModel;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen & Christian
 */
public class TaskController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    TimeLoggerModel tlm;
    ProjectModel pm;
    ClientModel cm;
    TaskModel tm;
    LoginModel lm;
    
    Timeline timeline;
    
    ContextMenu tcm = new ContextMenu();
    
    private Project project;

    @FXML
    private JFXComboBox<Task> selectTask;
    @FXML
    private JFXButton timerButton;
    @FXML
    private Label timeSpent;
    @FXML
    private JFXButton pauseButton;
    @FXML
    private JFXComboBox<Project> selectProject;
    @FXML
    private JFXComboBox<Client> selectClient;
    @FXML
    private JFXCheckBox billable;
    @FXML
    private TableColumn<Task, String> description;
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private HBox timeLoggerLink;
    @FXML
    private HBox clientsLink;
    @FXML
    private HBox usersLink;
    @FXML
    private HBox statisticsLink;
    @FXML
    private Label projectName;

    /**
     * Initializes the controller class.
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
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        timerButton.setOnAction(e -> toggleTimer());
        pauseButton.setOnAction(e -> pauseTimer());
        
        selectClient.setItems(cm.getClients());
        
        setupTimeline();
        
        initStartTimeline();
        
        isAdmin();
        
        setupTableMenus();
    }  
    
    /**
     * Setup sidebar links according to admin status
     */
    private void isAdmin() {
        if(lm.getLoggedInUser().getIsAdmin()) {
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
    
    /**
     * Set the task projects, and setup table with tasks under this project
     * @param project 
     */
    public void setProject(Project project) {
        this.project = project;
        projectName.setText(project.getName());
        setupTable();
    }

    /**
     * TimeLogger view link
     * @param event
     * @throws Exception 
     */
    @FXML
    private void openTimeLogger(MouseEvent event) throws Exception {
        timeline.stop();
        tms.set((Stage) (selectProject.getScene().getWindow()), "TimeLogger");
    }
    
    /**
     * Client view link
     * @param event
     * @throws Exception 
     */
    @FXML
    private void openClients(MouseEvent event) throws Exception {
        timeline.stop();
        tms.set((Stage) (selectProject.getScene().getWindow()), "Client");
    }

    /** 
     * User view link
     * @param event
     * @throws Exception 
     */
    @FXML
    private void openUsers(MouseEvent event) throws Exception {
        timeline.stop();
        tms.set((Stage) (selectProject.getScene().getWindow()), "User");
    }

    /**
     * Statistics view link
     * @param event
     * @throws Exception 
     */
    @FXML
    private void openStatistics(MouseEvent event) throws Exception {
        timeline.stop();
        tms.set((Stage) (selectProject.getScene().getWindow()), "Statistics");
    }

    /**
     * Create a new task popup
     * @param event
     * @throws Exception 
     */
    @FXML
    private void addTask(ActionEvent event) throws Exception {
        tms.showTasksCreate(project);
    }
    
    /**
     * Toggle the timer, start / stop timer
     */
    private void toggleTimer() {
        if(timerButton.getText().equals("START")) {
            if(!selected(selectClient) || !selected(selectProject) || !selected(selectTask)) return;
            start();
        } else if(timerButton.getText().equals("STOP")) {
            stop();
        }
    }
    
    /**
     * Pause the timer
     */
    private void pauseTimer() {
        if(pauseButton.getText().equals("PAUSE")) {
            pause();
        } else if(pauseButton.getText().equals("RESUME")) {
            resume();
        }
    }
    
    /**
     * Start the timer
     */
    private void start() {
        timerButton.setText("STOP");
        pauseButton.setDisable(false);
        tlm.start(selectTask.getValue().getId(), billable.isSelected(), lm.getLoggedInUser().getId());
        disableCombos(true);
    }
    
    /**
     * Stop the timer
     */
    private void stop() {
        timerButton.setText("START");
        pauseButton.setText("PAUSE");
        pauseButton.setDisable(true);
        tlm.stop();
        disableCombos(false);
    }
    
    /**
     * Pause the timer
     */
    private void pause() {
        pauseButton.setText("RESUME");
        tlm.pause();
    }
    
    /**
     * Resume running timer
     */
    private void resume() {
        pauseButton.setText("PAUSE");
        tlm.unpause();
    }

    /**
     * Setup the timeline, to update time spent.
     */
    private void setupTimeline() {
        timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                timeSpent.setText(tlm.totalSpentTimeString());
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    
    /**
     * Check JFXComboBox selected status
     * @param combo
     * @return boolean
     */
    private boolean selected(JFXComboBox combo) {
        return combo.getValue() != null;
    }

    /**
     * Initialize timeline according to if a timer is running or paused.
     */
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

    /**
     * Select a client
     * @param event 
     */
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

    /**
     * Select a project
     * @param event 
     */
    @FXML
    private void selectProject(ActionEvent event) {
        System.out.println(selectProject.getValue());
        System.out.println(selectProject.getSelectionModel().isEmpty());
        
        if(!selectProject.getSelectionModel().isEmpty()) {
            selectTask.setDisable(false);
            selectTask.setItems(tm.getTasks(selectProject.getValue().getId()));
            setProject(selectProject.getValue());
        } else {
            selectTask.setDisable(true);
        }
    }

    /**
     * Select a task
     * @param event 
     */
    @FXML
    private void selectTask(ActionEvent event) {
        System.out.println(selectTask.getValue());
        System.out.println(selectTask.getSelectionModel().isEmpty());
    }

    /**
     * Mass setDisable for ComboBoxes
     * Set combobox disable status to given boolean.
     * @param b 
     */
    private void disableCombos(boolean b) {
        selectClient.setDisable(b);
        selectProject.setDisable(b);
        selectTask.setDisable(b);
        billable.setDisable(b);
    }

    /**
     * Setup table
     */
    private void setupTable() {
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        if(project != null) {
            taskTable.setItems(tm.getTasks(project.getId()));
        }
    }
    
    /**
     * Setup table menus
     */
    private void setupTableMenus() {
        // Edit Client
        MenuItem editItem = new MenuItem("Edit");
        editItem.setOnAction((action) -> {
            try {
                Task task = taskTable.getSelectionModel().getSelectedItem();
                tms.editTask(task, project);
            } catch (Exception ex) {
                Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tcm.getItems().add(editItem);
        
        // Delete client
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction((action) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Task");
            alert.setHeaderText("Are you sure you want to delete the task?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try {
                    Task task = taskTable.getSelectionModel().getSelectedItem();
                    tm.deleteTask(task);
                } catch (Exception ex) {
                    Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        if(lm.getLoggedInUser().getIsAdmin()){
            tcm.getItems().add(deleteItem);
        }
        
        taskTable.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
            if(t.getButton() == MouseButton.SECONDARY) {
                tcm.show(taskTable, t.getScreenX(), t.getScreenY());
            }
        });
    }
    
}
