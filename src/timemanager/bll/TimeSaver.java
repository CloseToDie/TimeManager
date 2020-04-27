/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import timemanager.be.Timer;


/**
 *
 * @author kaspe & andreasvillumsen
 */
public class TimeSaver
{
    ArrayList<Timer> timers = new ArrayList<>();
    
    static LocalDateTime startTime;
    static LocalDateTime stopTime;
    double spentTime;

    public void startTime()
    {
        startTime = LocalDateTime.now();
        
        if(timers.isEmpty()) {
            timers.add(new Timer())
        }
    }

    public static void stopTime()
    {
        stopTime = LocalDateTime.now();
    }

    public static double spentTime()
    {
        return ChronoUnit.SECONDS.between(startTime, stopTime);
    }

    public static void main(String[] args) throws InterruptedException {
        startTime();
        
        TimeUnit.SECONDS.sleep(5);
        
        stopTime();
        
        System.out.println("Time spent: " + (int)spentTime());
    }

}
