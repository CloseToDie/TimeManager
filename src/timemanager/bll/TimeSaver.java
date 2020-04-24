/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author kaspe
 */
public class TimeSaver
{
    LocalDateTime startTime;
    LocalDateTime stopTime;
    int spentTime;
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public void startTime()
    {
        startTime = LocalDateTime.now();
        
    }

    public void stopTime()
    {
        stopTime = LocalDateTime.now();
        
    }

    public int spentTime(int compareTo)
    {
        spentTime(stopTime.compareTo(startTime));
        return spentTime;
        
    }

    public void validator()
    {
        System.out.println(spentTime);
    }

}
