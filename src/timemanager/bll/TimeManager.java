package timemanager.bll;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Logger;
import timemanager.be.Timer;
import timemanager.dal.TimeManagerFacade;
import timemanager.dal.database.TimeManagerDBDAO;

/**
 *
 * @author andreasvillumsen
 */
public class TimeManager {

    private static final Logger LOG = Logger.getLogger(TimeManager.class.getName());
    
    ArrayList<Timer> timers = new ArrayList<>();
    ArrayList<Timer> timersFromDB = new ArrayList<>();
    
    
    TimeManagerFacade tm;

    /**
     * TimeSaver Constructor. 
     * Instantiate a new TimeManagerDBDAO.
     * @throws Exception 
     */
    public TimeManager() throws Exception {
        tm = new TimeManagerDBDAO();
    }
    
    /**
     * Get list of timers
     * @return timers
     */
    public ArrayList<Timer> getTimers() {
        timersFromDB = tm.getTimers();
        return timersFromDB;
    }
    
    

    /**
     * Start a new timer if no timer is running.
     * @param taskId
     * @param isBillable
     */
    public void start(int taskId, boolean isBillable, int user_id) {
        if(timerRunning()) return;
        if(!sameTaskId(taskId)) return;
        timers.add(new Timer(0, LocalDate.now(), LocalDateTime.now(), null, 0, isBillable, taskId, user_id));
        System.out.println("timer started");
    }

    /**
     * Stop the running timer, 
     * and clear the timer list.
     */
    public void stop() {
        if(timerRunning()) {
            lastTimer().setStopTime(LocalDateTime.now());
            // Save last timer to db
            tm.storeTimer(lastTimer());
            System.out.println("timer stopped");
        } else {
            System.out.println("timer is not running");
        }
        // Reset list
        timers.clear();
    }

    /**
     * Pause the timer.
     * Stop the running timer,
     * but don't clear list.
     */
    public void pause() {
        lastTimer().setStopTime(LocalDateTime.now());
        lastTimer().setSpentTime(spentTime(lastTimer().getStartTime(), lastTimer().getStopTime()));
        // Save last timer to db
        tm.storeTimer(lastTimer());
        System.out.println("timer paused");
    }

    /**
     * Start a new timer,
     * with last timer taskId.
     */
    public void unpause() {
        start(lastTimer().getTaskId(), lastTimer().isBillable(), lastTimer().getUserId());
        System.out.println("timer unpaused");
    }
    
    /**
     * Get the newest timer
     * @return timer
     */
    public Timer lastTimer() {
        if(timers.isEmpty()) return null;
        return timers.get(timers.size() - 1);
    }
    
    /**
     * Check if the last timer taskId 
     * is the same as the given taskId
     * @param taskId
     * @return boolean
     */
    private boolean sameTaskId(int taskId) {
        if(timers.isEmpty()) return true;
        return lastTimer().getTaskId() == taskId;
    }
    
    /**
     * Check if the newest timer is running or not
     * @return boolean
     */
    public boolean timerRunning() {
        if(!timers.isEmpty()) return lastTimer().getStopTime() == null;
        return false;
    }
    
    /**
     * Calculate the time between two LocalDateTime.
     * @param startTime
     * @param stopTime
     * @return double (time)
     */
    public double spentTime(LocalDateTime startTime, LocalDateTime stopTime) {
        if(stopTime == null) stopTime = LocalDateTime.now();
        return ChronoUnit.SECONDS.between(startTime, stopTime);
    }
    
    /**
     * Calculate the total spent time 
     * for all timers in list
     * @return spentTime
     */
    public double totalSpentTime() {
        double spentTime = 0;
        System.out.println("Timers running: " + timers.size());
        for (Timer timer : timers) {
            LocalDateTime stopTime;
            if(timer.getStopTime() == null) {
                stopTime = LocalDateTime.now();
            } else {
                stopTime = timer.getStopTime();
            }
            
            spentTime += ChronoUnit.SECONDS.between(timer.getStartTime(), stopTime);
            
            System.out.println("start: " + timer.getStartTime() + " | stop: " + timer.getStopTime());
        }
        return spentTime;
    }

    public ArrayList<Timer> getTimers(int taskId) {
        return tm.getTimers(taskId);
    }
}
