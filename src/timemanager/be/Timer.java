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

    public Timer(int id, LocalDateTime startTime, LocalDateTime stopTime, double spentTime) {
        this.id = id;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.spentTime = spentTime;
    }

    public double getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(double spentTime) {
        this.spentTime = spentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }
    
    
    
    
}
