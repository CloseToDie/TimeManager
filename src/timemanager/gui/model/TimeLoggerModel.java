package timemanager.gui.model;

import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.Timer;
import timemanager.bll.TimeManager;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class TimeLoggerModel {
    private static TimeLoggerModel single_instance = null;
    private TimeManager tm;
    private ObservableList<Timer> timers;
    private ObservableList<Timer> taskTimers;
    
    /**
     * Get TimeLoggerModel instance
     * @return TimeLoggerModel
     * @throws Exception 
     */
    public static TimeLoggerModel getInstance() throws Exception {
        if (single_instance == null) 
            single_instance = new TimeLoggerModel(); 
  
        return single_instance; 
    }

    /**
     * TimeLoggerModel constructor
     * @throws Exception 
     */
    private TimeLoggerModel() throws Exception {
        tm = new TimeManager();
        timers = FXCollections.observableArrayList();
        taskTimers = FXCollections.observableArrayList();
    }
    
    /**
     * Refresh the timers arraylist data.
     */
    public void refreshData() {
        timers.clear();
        timers.addAll(tm.getTimers());
    }
    
    /**
     * Refresh the taskTimers arraylist data.
     * Get all the timers for a specific task
     * @param taskId 
     */
    public void refreshData(int taskId) {
        taskTimers.clear();
        taskTimers.addAll(tm.getTimers(taskId));
    }
    
    /**
     * Get a ObservableList of timers
     * @return timers
     */
    public ObservableList<Timer> getTimers() {
        refreshData();
        return timers;
    }
    
    /**
     * Get ObservableList of timers for a task
     * @param taskId
     * @return task timers
     */
    public ObservableList<Timer> getTimers(int taskId) {
        refreshData(taskId);
        return taskTimers;
    }

    /**
     * Start a new timer
     * @param taskId
     * @param isBillable
     * @param user_id 
     */
    public void start(int taskId, boolean isBillable, int user_id) {
        tm.start(taskId, isBillable, user_id);
    }
    
    /**
     * Stop a running timer.
     */
    public void stop() {
        refreshData(lastTimer().getTaskId());
        tm.stop();
    }

    /**
     * Pause a running timer
     */
    public void pause() {
        tm.pause();
    }

    /**
     * Resume a paused timer
     */
    public void unpause() {
        tm.unpause();
    }

    /** 
     * Get the total spent time in seconds
     * @return total spent time
     */
    public long totalSpentTime() {
        return tm.totalSpentTime();
    }
    
    /**
     * Get the spent time between two LocalDateTime in seconds
     * @param startTime
     * @param stopTime
     * @return spent time
     */
    public long spentTime(LocalDateTime startTime, LocalDateTime stopTime) {
        return tm.spentTime(startTime, stopTime);
    }
    
    /**
     * Get the total spent time in seconds in string format
     * @return total spent time
     */
    public String totalSpentTimeString() {
        return tm.getTotalSpentTimeString();
    }
    
    /**
     * Get the spent time between two LocalDateTime in seconds in string format
     * @param startTime
     * @param stopTime
     * @return spent time
     */
    public String spentTimeString(LocalDateTime startTime, LocalDateTime stopTime) {
        return tm.getSpentTimeString(startTime, stopTime);
    }
    
    /**
     * Check if the timer is running
     * @return boolean
     */
    public boolean timerRunning() {
        return tm.timerRunning();
    }
    
    /**
     * Get the last running timer
     * @return last running timer
     */
    public Timer lastTimer() {
        return tm.lastTimer();
    }
    
}
