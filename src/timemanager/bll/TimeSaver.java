/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author kaspe
 */
public class TimeSaver
{
    static LocalDateTime startTime;
    static LocalDateTime stopTime;
    double spentTime;
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static void startTime()
    {
        startTime = LocalDateTime.now();
    }

    public static void stopTime()
    {
        stopTime = LocalDateTime.now();
    }

    public static double spentTime()
    {
        return ChronoUnit.SECONDS.between(startTime, stopTime);
    }

    public void validator()
    {
        System.out.println(spentTime);
    }
    
    public static void main(String[] args) throws InterruptedException {
        startTime();
        
        TimeUnit.SECONDS.sleep(5);
        
        stopTime();
        
        System.out.println("Time spent: " + spentTime());
    }

}
