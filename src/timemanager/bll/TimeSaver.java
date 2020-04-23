/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import java.time.Duration;
import java.time.LocalDateTime;
import timemanager.be.Timer;

/**
 *
 * @author kaspe
 */
public class TimeSaver
{
    LocalDateTime startTime;
    LocalDateTime stopTime;
    int timeSpent;

    public void startTime()
    {
        startTime = LocalDateTime.now();
        
    }

    public void stopTime()
    {
        stopTime = LocalDateTime.now();
        
    }

    public int timeSpent()
    {
        timeSpent(stopTime.compareTo(startTime));
        
    }
}
