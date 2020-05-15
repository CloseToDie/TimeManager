package timemanager.bll;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
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
     * Start a new timer if no timer is running.
     * @param projectId
     */
    public void startTime(int projectId) {
        if(noRunningTimer() && sameProjectId(projectId)) {
            timers.add(new Timer(0, LocalDateTime.now(), null, 0, projectId));
            LOG.log(Level.INFO, "Started a new timer for project with id {0}", projectId);
        } else {
            LOG.log(Level.WARNING, "Could not start a new timer for project with id {0}", projectId);
        }
    }

    /**
     * Stop the running timer, 
     * calculate the time spent 
     * and save it to database.
     */
    public void stopTime() {
        if(!noRunningTimer()) {
            Timer timer = getLastTimerInList();
            stopRunningTimer(timer);
            
            double spent = spentTime(timer.getStartTime(), timer.getStopTime());
            
            timer.setSpentTime(spent);
            
            tm.storeTimer(timer);
            LOG.log(Level.INFO, "Timer for project {0} stopped", timer.getTaskId());
        }
    }
    
    /**
     * Stop the given running timer.
     * @param timer 
     */
    void stopRunningTimer(Timer timer) {
        if(noRunningTimer()) return;
        LOG.log(Level.INFO, "Stopping current running timer for project {0}", timer.getTaskId());
        timer.setStopTime(LocalDateTime.now());
    }
    
    /**
     * Check if the newest timer is running or not
     * @return boolean
     */
    boolean noRunningTimer() {
        if(timers.isEmpty()) return true;
        return getLastTimerInList().getStopTime() != null;
    }

    /**
     * Check if the last timers project id matches the new project id.
     * @param projectId
     * @return boolean
     */
    private boolean sameProjectId(int projectId) {
        if(getLastTimerInList() == null) return true;
        int lastProjectId = getLastTimerInList().getTaskId();
        boolean match = (lastProjectId == projectId);
        LOG.log(Level.INFO, "Checking if given project matches the old project, result: {0}", match);
        return match;
    }
    
    /**
     * Get the last timer in the arraylist.
     * @return last timer in arraylist
     */
    public Timer getLastTimerInList() {
        if(timers.isEmpty()) return null;
        return timers.get(timers.size() - 1);
    }
    
    public ArrayList<Timer> getTimers() {
        timers = tm.getTimers();
        return timers;
    }

    public void start(int taskId) {
        if(timerRunning()) return;
        if(!sameTaskId(taskId)) return;
        timers.add(new Timer(0, LocalDateTime.now(), null, 0, taskId));
        System.out.println("timer started");
    }

    public void stop() {
        if(timerRunning()) {
            lastTimer().setStopTime(LocalDateTime.now());
            // Save last timer to db
            //tm.storeTimer(lastTimer());
            // Reset list
            timers.clear();
            System.out.println("timer stopped");
        } else {
            System.out.println("timer is not running");
        }
        
    }

    public void pause() {
        lastTimer().setStopTime(LocalDateTime.now());
        lastTimer().setSpentTime(spentTime(lastTimer().getStartTime(), lastTimer().getStopTime()));
        // Save last timer to db
        //tm.storeTimer(lastTimer());
        System.out.println("timer paused");
    }

    public void unpause() {
        start(lastTimer().getTaskId());
        System.out.println("timer unpaused");
    }
    
    private Timer lastTimer() {
        if(timers.isEmpty()) return null;
        return timers.get(timers.size() - 1);
    }
    
    private boolean sameTaskId(int taskId) {
        if(timers.isEmpty()) return true;
        return lastTimer().getTaskId() == taskId;
    }
    
    private boolean timerRunning() {
        if(!timers.isEmpty()) {
            return lastTimer().getStopTime() == null;
        } else {
            return false;
        }
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

}
