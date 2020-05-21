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
import javafx.scene.control.TableRow;
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
public class ProjectController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    TimeLoggerModel tlm;
    ProjectModel pm;
    ClientModel cm;
    TaskModel tm;
    LoginModel lm;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    Timeline timeline;
    
    ContextMenu tcm = new ContextMenu();
    
    Client client;

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
        } catch (Exception ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        timerButton.setOnAction(e -> toggleTimer());
        pauseButton.setOnAction(e -> pauseTimer());
        
        selectClient.setItems(cm.getClients());
        
        setupTimeline();
        
        initStartTimeline();
    }    

    @FXML
    private void openTimeLogger(MouseEvent event) throws Exception {
        tms.set((Stage) (selectProject.getScene().getWindow()), "TimeLogger");
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
    private void openAddProject(MouseEvent event) throws IOException, Exception {
        tms.showProjectsCreate(client);
    }
    
    public void setClient(Client client) {
        this.client = client;
        setupTable();
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
                timeSpent.setText(LocalTime.ofSecondOfDay(tlm.totalSpentTime()).format(formatter));
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
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        projectTable.setRowFactory( tv -> {
            TableRow<Project> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Project rowData = row.getItem();
                    try {
                        tms.showTasks((Stage) (selectProject.getScene().getWindow()), rowData);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row ;
        });
        
        //Show Projects
        MenuItem showItem = new MenuItem("Show Tasks");
        showItem.setOnAction((action) -> {
            try {
                Project project = projectTable.getSelectionModel().getSelectedItem();
                tms.showTasks((Stage) (selectProject.getScene().getWindow()), project);
            } catch (Exception ex) {
                Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tcm.getItems().add(showItem);
        
        // Edit Client
        MenuItem editItem = new MenuItem("Edit");
        editItem.setOnAction((action) -> {
            try {
                Project project = projectTable.getSelectionModel().getSelectedItem();
                tms.editProject(project, client);
            } catch (Exception ex) {
                Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tcm.getItems().add(editItem);
        
        // Delete client
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction((action) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Project");
            alert.setHeaderText("Are you sure you want to delete the project?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try {
                    Project project = projectTable.getSelectionModel().getSelectedItem();
                    pm.deleteProject(project);
                } catch (Exception ex) {
                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tcm.getItems().add(deleteItem);
        
        projectTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    tcm.show(projectTable, t.getScreenX(), t.getScreenY());
                }
            }
        });
        
        if(client != null) {
            projectTable.setItems(pm.getClientProjects(client.getId()));
        }
        
    }
    
}