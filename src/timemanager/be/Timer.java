package timemanager.be;

import java.time.LocalDateTime;

/**
 *
 * @author andreasvillumsen
 */
public class Timer {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private double spentTime;
    private int projectId;

    /**
     * Timer Constructor
     * @param id
     * @param startTime
     * @param stopTime
     * @param spentTime 
     * @param projectId 
     */
    public Timer(int id, LocalDateTime startTime, LocalDateTime stopTime, double spentTime, int projectId) {
        this.id = id;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.spentTime = spentTime;
        this.projectId = projectId;
    }

    /**
     * Get the time spent
     * @return spentTime
     */
    public double getSpentTime() {
        return spentTime;
    }

    /**
     * Set the time spent
     * @param spentTime 
     */
    public void setSpentTime(double spentTime) {
        this.spentTime = spentTime;
    }

    /**
     * Get the id
     * @return id 
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the timer start time
     * @return startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Set the timer start time
     * @param startTime 
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Get the timer stop time
     * @return stopTime
     */
    public LocalDateTime getStopTime() {
        return stopTime;
    }

    /**
     * Set the timer stop time
     * @param stopTime 
     */
    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }

    /**
     * Get the timer projectId
     * @return projectId
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Set the timer's projectId
     * @param projectId 
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
}
