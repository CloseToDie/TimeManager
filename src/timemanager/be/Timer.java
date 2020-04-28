/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    /**
     * Timer Constructor
     * @param id
     * @param startTime
     * @param stopTime
     * @param spentTime 
     */
    public Timer(int id, LocalDateTime startTime, LocalDateTime stopTime, double spentTime) {
        this.id = id;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.spentTime = spentTime;
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
    
}
