package timemanager.gui.model;

import timemanager.bll.TimeManager;

/**
 *
 * @author andreasvillumsen
 */
public class TimeLoggerModel {
    private TimeManager tm;

    public TimeLoggerModel() throws Exception {
        tm = new TimeManager();
    }
    
    public void startTime(int projectId) {
        tm.startTime(projectId);
    }
    
    public void stopTime() {
        tm.stopTime();
    }
}
