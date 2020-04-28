package timemanager.dal;

import timemanager.be.Timer;

/**
 *
 * @author andreasvillumsen
 */
public interface TimeManagerFacade {
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
