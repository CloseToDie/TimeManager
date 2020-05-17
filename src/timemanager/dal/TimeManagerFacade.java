package timemanager.dal;

import java.util.ArrayList;
import timemanager.be.Timer;

/**
 *
 * @author andreasvillumsen
 */
public interface TimeManagerFacade {
    
    /**
     * Get timers
     * @return timers
     */
    public ArrayList<Timer> getTimers();
    
    public ArrayList<Timer> getTimers(int projectId);
    
    /**
     * Get a timer
     * @param timer
     * @return timer
     */
    public Timer getTimer(Timer timer);
    
    /**
     * Store a new timer in the database
     * @param timer
     * @return boolean
     */
    public boolean storeTimer(Timer timer);
    
    /**
     * Update a timer in the database
     * @param timer
     * @return boolean
     */
    public boolean updateTimer(Timer timer);
    
    /**
     * Delete a timer in the database
     * @param timer
     * @return boolean
     */
    public boolean deleteTimer(Timer timer);
}
