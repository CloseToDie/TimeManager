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
    int id;
    LocalDateTime startTime;
    LocalDateTime stopTime;
    double spentTime;

    public Timer(int id, LocalDateTime startTime, LocalDateTime stopTime, double spentTime) {
        this.id = id;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.spentTime = spentTime;
    }
    
    
}
