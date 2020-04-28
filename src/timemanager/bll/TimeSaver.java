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
import timemanager.dal.TimeManagerFacade;
import timemanager.dal.database.TimeManagerDBDAO;

/**
 *
 * @author kaspe & andreasvillumsen
 */
public class TimeSaver
{
    ArrayList<Timer> timers = new ArrayList<>();
    
    TimeManagerFacade tm;
    
    boolean paused = false;

    public TimeSaver() throws Exception {
        tm = new TimeManagerDBDAO();
    }

    public void startTime() {
        if(timers.isEmpty() || noRunningTimer()) {
            timers.add(new Timer(0, LocalDateTime.now(), null, 0));
        }
    }

    public void stopTime() {
        if(!noRunningTimer()) {
            Timer timer = timers.get(timers.size() - 1);
            stopRunningTimer(timer);
            
            double spent = spentTime(timer.getStartTime(), timer.getStopTime());
            
            timer.setSpentTime(spent);
            
            tm.storeTimer(timer);
        }
    }
    
    /**
     * Not working yet, need attention.
     */
    public void pauseTime() {
        paused = !paused;
        
        if(paused) {
            stopTime();
        } else {
            startTime();
        }
    }

    public double spentTime(LocalDateTime startTime, LocalDateTime stopTime) {
        return ChronoUnit.SECONDS.between(startTime, stopTime);
    }
    
    void stopRunningTimer(Timer timer) {
        timer.setStopTime(LocalDateTime.now());
    }
    
    boolean noRunningTimer() {
        return timers.get(timers.size() - 1).getStopTime() != null;
    }

}
