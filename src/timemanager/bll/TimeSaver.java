package timemanager.bll;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    /**
     * TimeSaver Constructor. 
     * Instantiate a new TimeManagerDBDAO.
     * @throws Exception 
     */
    public TimeSaver() throws Exception {
        tm = new TimeManagerDBDAO();
    }

    /**
     * Start a new timer if no timer is running.
     */
    public void startTime() {
        if(timers.isEmpty() || noRunningTimer()) {
            timers.add(new Timer(0, LocalDateTime.now(), null, 0));
        }
    }

    /**
     * Stop the running timer, 
     * calculate the time spent 
     * and save it to database.
     */
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

    /**
     * Calculate the time between two LocalDateTime.
     * @param startTime
     * @param stopTime
     * @return double (time)
     */
    public double spentTime(LocalDateTime startTime, LocalDateTime stopTime) {
        return ChronoUnit.SECONDS.between(startTime, stopTime);
    }
    
    /**
     * Stop the given running timer.
     * @param timer 
     */
    void stopRunningTimer(Timer timer) {
        timer.setStopTime(LocalDateTime.now());
    }
    
    /**
     * Check if the newest timer is running or not
     * @return boolean
     */
    boolean noRunningTimer() {
        return timers.get(timers.size() - 1).getStopTime() != null;
    }

}
