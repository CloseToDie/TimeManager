/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chri9
 */
public class TimeManagerTest
{
    
    public TimeManagerTest()
    {
    }

    /**
     * Test of getTimers method, of class TimeManager.
     */
    @Test
    public void testGetTimers_0args()
    {
    }

    /**
     * Test of start method, of class TimeManager.
     */
    @Test
    public void testStart()
    {
    }

    /**
     * Test of stop method, of class TimeManager.
     */
    @Test
    public void testStop()
    {
    }

    /**
     * Test of pause method, of class TimeManager.
     */
    @Test
    public void testPause()
    {
    }

    /**
     * Test of unpause method, of class TimeManager.
     */
    @Test
    public void testUnpause() throws Exception
    {
        TimeManager instance = new TimeManager();
    }

    /**
     * Test of lastTimer method, of class TimeManager.
     */
    @Test
    public void testLastTimer() throws Exception
    {
        TimeManager instance = new TimeManager();
        instance.start(2, true, 3);
        instance.pause();
        instance.unpause();
        boolean expResult = true;
        boolean result = instance.lastTimer().getStopTime() == null;
        assertEquals(expResult, result);
    }

    /**
     * Test of timerRunning method, of class TimeManager.
     */
    @Test
    public void testTimerRunning() throws Exception
    {
        TimeManager instance = new TimeManager();
        instance.start(1, true, 5);
        boolean expResult = true;
        boolean result = instance.timerRunning();
        assertEquals(expResult, result);
    }

    /**
     * Test of spentTime method, of class TimeManager.
     */
    @Test
    public void testSpentTime() throws Exception
    {
        TimeManager instance = new TimeManager();
        double expResult = 10;
        double result = instance.spentTime(LocalDateTime.now(), LocalDateTime.now().plusSeconds(10));
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of totalSpentTime method, of class TimeManager.
     */
    @Test
    public void testTotalSpentTime()
    {
    }

    /**
     * Test of getSpentTimeString method, of class TimeManager.
     */
    @Test
    public void testGetSpentTimeString() throws Exception
    {
        TimeManager instance = new TimeManager();
        String expResult = "01:05:00";
        String result = instance.getSpentTimeString(LocalDateTime.now(), LocalDateTime.now().plusMinutes(65));
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalSpentTimeString method, of class TimeManager.
     */
    @Test
    public void testGetTotalSpentTimeString() throws Exception
    {
        TimeManager instance = new TimeManager();
        
        
    }

    /**
     * Test of getTimers method, of class TimeManager.
     */
    @Test
    public void testGetTimers_int()
    {
    }
    
}
