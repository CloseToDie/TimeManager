package timemanager.be;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Timer BE Class
 * @author andreasvillumsen & Christian
 */
public class Timer {
    private int id;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private double spentTime;
    private boolean billable;
    private int taskId;
    private int userId;

    /**
     * Timer Constructor
     * @param id
     * @param date
     * @param startTime
     * @param stopTime
     * @param spentTime 
     * @param billable 
     * @param taskId 
     * @param userId 
     */
    public Timer(int id, LocalDate date, LocalDateTime startTime, LocalDateTime stopTime, double spentTime, boolean billable, int taskId, int userId) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.spentTime = spentTime;
        this.billable = billable;
        this.taskId = taskId;
        this.userId = userId;
    }
    /**
     * Get the date the timer was started.
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set the date the timer was started.
     * @param date 
     */
    public void setDate(LocalDate date) {
        this.date = date;
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
     * Get is billable
     * @return billable
     */
    public boolean isBillable() {
        return billable;
    }

    /**
     * Set is billable
     * @param billable 
     */
    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    /**
     * Get the timer taskId
     * @return taskId
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Set the timer's taskId
     * @param taskId 
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Get the task users id
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set the task users id
     * @param userId 
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
