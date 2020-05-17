package timemanager.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.Timer;
import timemanager.bll.TimeManager;

/**
 *
 * @author andreasvillumsen
 */
public class TimeLoggerModel {
    private static TimeLoggerModel single_instance = null;
    private TimeManager tm;
    private ObservableList<Timer> timers;
    private ObservableList<Timer> taskTimers;
    
    public static TimeLoggerModel getInstance() throws Exception {
        if (single_instance == null) 
            single_instance = new TimeLoggerModel(); 
  
        return single_instance; 
    }

    private TimeLoggerModel() throws Exception {
        tm = new TimeManager();
        timers = FXCollections.observableArrayList();
        taskTimers = FXCollections.observableArrayList();
    }
    
    public void refreshData() {
        timers.clear();
        timers.addAll(tm.getTimers());
    }
    
    public void refreshData(int taskId) {
        taskTimers.clear();
        taskTimers.addAll(tm.getTimers(taskId));
    }
    
    public ObservableList<Timer> getTimers() {
        refreshData();
        return timers;
    }
    
    public ObservableList<Timer> getTimers(int taskId) {
        refreshData(taskId);
        return taskTimers;
    }

    public void start(int taskId, boolean isBillable, int user_id) {
        tm.start(taskId, isBillable, user_id);
    }
    
    public void stop() {
        refreshData(lastTimer().getTaskId());
        tm.stop();
    }

    public void pause() {
        tm.pause();
    }

    public void unpause() {
        tm.unpause();
    }

    public long totalSpentTime() {
        return (long) tm.totalSpentTime();
    }
    
    public boolean timerRunning() {
        return tm.timerRunning();
    }
    
    public Timer lastTimer() {
        return tm.lastTimer();
    }
    
}
