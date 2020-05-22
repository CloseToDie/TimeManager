package timemanager.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import timemanager.TimeManagerStart;
import timemanager.be.Client;
import timemanager.be.Project;
import timemanager.be.Task;
import timemanager.be.User;
import timemanager.gui.model.ClientModel;
import timemanager.gui.model.LoginModel;
import timemanager.gui.model.ProjectModel;
import timemanager.gui.model.TaskModel;
import timemanager.gui.model.TimeLoggerModel;
import timemanager.gui.model.UserModel;

/**
 *
 * @author andreasvillumsen
 */
public class UserController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    TimeLoggerModel tlm;
    ProjectModel pm;
    ClientModel cm;
    TaskModel tm;
    LoginModel lm;
    UserModel um;
    
    Timeline timeline;
    
    ContextMenu tcm = new ContextMenu();

    @FXML
    private JFXComboBox<Project> selectProject;
    @FXML
    private JFXButton timerButton;
    @FXML
    private Label timeSpent;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, Boolean> role;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private JFXComboBox<Task> selectTask;
    @FXML
    private JFXButton pauseButton;
    @FXML
    private JFXComboBox<Client> selectClient;
    @FXML
    private JFXCheckBox billable;
    
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
            um = UserModel.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(TimeLoggerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        timerButton.setOnAction(e -> toggleTimer());
        pauseButton.setOnAction(e -> pauseTimer());
        
        selectClient.setItems(cm.getClients());
        
        setupTable();
        
        setupTimeline();
        
        initStartTimeline();
        
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
    }

    private void disableCombos(boolean b) {
        selectClient.setDisable(b);
        selectProject.setDisable(b);
        selectTask.setDisable(b);
        billable.setDisable(b);
    }

    private void setupTable() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        role.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        // Edit user
        MenuItem editItem = new MenuItem("Edit");
        editItem.setOnAction((action) -> {
            try {
                User user = userTable.getSelectionModel().getSelectedItem();
                tms.editUser(user);
            } catch (Exception ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tcm.getItems().add(editItem);
        
        // Delete user
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction((action) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete User");
            alert.setHeaderText("Are you sure you want to delete the user?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try {
                    User user = userTable.getSelectionModel().getSelectedItem();
                    um.deleteUser(user);
                } catch (Exception ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tcm.getItems().add(deleteItem);
        
        userTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    tcm.show(userTable, t.getScreenX(), t.getScreenY());
                }
            }
        });
        
        userTable.setItems(um.getUsers());
    }
}
