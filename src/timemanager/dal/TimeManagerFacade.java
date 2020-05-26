package timemanager.dal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import timemanager.be.Timer;
import timemanager.be.User;

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
    
    public ArrayList<Timer> getTimers(int taskId);
    
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

    /**
     * Get timers for statistics
     * @param taskId
     * @param user
     * @param start
     * @param end
     * @return timers
     */
    public ArrayList<Timer> getStatTimers(int taskId, User user, LocalDate start, LocalDate end);
}
