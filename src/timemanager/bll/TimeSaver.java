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
 * @author kaspe & andreasvillumsen
 */
public class TimeSaver {

    private static final Logger LOG = Logger.getLogger(TimeSaver.class.getName());
    
    ArrayList<Timer> timers = new ArrayList<>();
    
    TimeManagerFacade tm;

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
     * @param projectId
     */
    public void startTime(int projectId) {
        if(timers.isEmpty()) {
            if(noRunningTimer() && sameProjectId(projectId)) {
                timers.add(new Timer(0, LocalDateTime.now(), null, 0, projectId));
            }
        }
        LOG.log(Level.INFO, "Started a new timer for project with id {0}", projectId);
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
        return getLastTimerInList().getStopTime() != null;
    }

    /**
     * Check if the last timers project id matches the new project id.
     * @param projectId
     * @return boolean
     */
    private boolean sameProjectId(int projectId) {
        int lastProjectId = getLastTimerInList().getProjectId();
        return lastProjectId == projectId;
    }
    
    /**
     * Get the last timer in the arraylist.
     * @return last timer in arraylist
     */
    private Timer getLastTimerInList() {
        return timers.get(timers.size() - 1);
    }

}
