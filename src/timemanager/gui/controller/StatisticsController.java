package timemanager.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import timemanager.TimeManagerStart;
import timemanager.be.Client;
import timemanager.be.Project;
import timemanager.be.Task;
import timemanager.be.Timer;
import timemanager.be.User;
import timemanager.gui.model.ClientModel;
import timemanager.gui.model.LoginModel;
import timemanager.gui.model.ProjectModel;
import timemanager.gui.model.TaskModel;
import timemanager.gui.model.TimeLoggerModel;
import timemanager.gui.model.UserModel;
import timemanager.utils.text.Formatter;
import timemanager.utils.time.TimeConverter;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen
 */
public class StatisticsController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    TimeLoggerModel tlm;
    ProjectModel pm;
    ClientModel cm;
    TaskModel tm;
    UserModel um;
    LoginModel lm;
    
    Timeline timeline;

    @FXML
    private JFXComboBox<Project> selectProject;
    @FXML
    private JFXButton timerButton;
    @FXML
    private Label timeSpent;
    @FXML
    private JFXComboBox<Task> selectTask;
    @FXML
    private JFXButton pauseButton;
    @FXML
    private JFXComboBox<Client> selectClient;
    @FXML
    private JFXCheckBox billable;
    @FXML
    private JFXComboBox<Project> statSelectProject;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private CategoryAxis categoryAxis;
    @FXML
    private JFXComboBox<User> selectUser;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private Label totalBillableEarned;
    @FXML
    private Label totalNonBillableEarned;
    @FXML
    private Label totalHoursUsed;
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
            um = UserModel.getInstance();
            lm = LoginModel.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(TimeLoggerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        timerButton.setOnAction(e -> toggleTimer());
        pauseButton.setOnAction(e -> pauseTimer());
        
        selectClient.setItems(cm.getClients());
        
        selectUser.setItems(um.getUsers());
        statSelectProject.setItems(pm.getProjects());
        
        setupTimeline();
        
        initStartTimeline();
        
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now());
        
        barChart.setLegendVisible(false);
        categoryAxis.setAnimated(false);
        
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
    private void openTimeLogger(MouseEvent event) throws Exception {
        timeline.stop();
        tms.set((Stage) (selectProject.getScene().getWindow()), "TimeLogger");
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
    }

    private void disableCombos(boolean b) {
        selectClient.setDisable(b);
        selectProject.setDisable(b);
        selectTask.setDisable(b);
        billable.setDisable(b);
    }
    
    @FXML
    private void refreshData() throws ParseException {
        if(!inputFilled()) return;
        
        totalBillableEarned.setText("");
        totalNonBillableEarned.setText("");
        
        barChart.getData().clear();
        
        XYChart.Series series1 = new XYChart.Series();
        
        ArrayList<Timer> timers = new ArrayList<>();
        
        timers.addAll(tm.getProjectTimers(statSelectProject.getValue(), selectUser.getValue(), startDate.getValue(), endDate.getValue()));
        
        Map<LocalDate, List<Timer>> filteredTimers = filterTimersForChart(timers);
        
        filteredTimers.entrySet().forEach((entry) -> {
            double spentTime = 0;
            spentTime = entry.getValue().stream().map((timer) -> timer.getSpentTime()).reduce(spentTime, (accumulator, _item) -> accumulator + _item);
            
            series1.getData().add(new XYChart.Data(entry.getKey().toString(), TimeConverter.secondsToHours(spentTime)));
        });
        
        barChart.getData().addAll(series1);
        
        calcTotalTimeAndMoneyEarned(filteredTimers);
    }
    
    private boolean inputFilled() {
        return !selectUser.getSelectionModel().isEmpty() && 
                !statSelectProject.getSelectionModel().isEmpty() && 
                startDate.getValue() != null && 
                endDate.getValue() != null;
    }

    private void refreshData(ActionEvent event) throws ParseException {
        refreshData();
    }
    
    private Map<LocalDate, List<Timer>> filterTimersForChart(List<Timer> timers) throws ParseException {
        Map<LocalDate, List<Timer>> timersPerDate = timers.stream()
        .collect(Collectors.groupingBy(Timer::getDate));
        
        return timersPerDate;
    }

    private void calcTotalTimeAndMoneyEarned(Map<LocalDate, List<Timer>> filteredTimers) {
        double totalMoneyEarned = 0;
        double totalNonBillable = 0;
        double totalSpentTime = 0;
        
        for (Map.Entry<LocalDate, List<Timer>> entry : filteredTimers.entrySet()) {
            for (Timer timer : entry.getValue()) {
                totalSpentTime += timer.getSpentTime();
                if(timer.isBillable()) {
                    totalMoneyEarned += statSelectProject.getValue().getSalary() * TimeConverter.secondsToHours(timer.getSpentTime());
                } else {
                    totalNonBillable += statSelectProject.getValue().getSalary() * TimeConverter.secondsToHours(timer.getSpentTime());
                }
            }
        }
        
        totalHoursUsed.setText(Formatter.doubleTo2f(TimeConverter.secondsToHours(totalSpentTime), "Hours."));
        totalBillableEarned.setText(Formatter.doubleTo2f(totalMoneyEarned, "Kr."));
        totalNonBillableEarned.setText(Formatter.doubleTo2f(totalNonBillable, "Kr."));
    }
    
}
