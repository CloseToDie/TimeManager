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
    
    public static TimeLoggerModel getInstance() throws Exception {
        if (single_instance == null) 
            single_instance = new TimeLoggerModel(); 
  
        return single_instance; 
    }

    private TimeLoggerModel() throws Exception {
        tm = new TimeManager();
        timers = FXCollections.observableArrayList();
    }
    
    public void refreshData() {
        timers.clear();
        timers.addAll(tm.getTimers());
    }
    
    public void startTime(int projectId) {
        tm.startTime(projectId);
    }
    
    public void stopTime() {
        tm.stopTime();
    }
    
    public ObservableList<Timer> getTimers() {
        refreshData();
        return timers;
    }
    
}
