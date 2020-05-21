/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import timemanager.be.Timer;

/**
 *
 * @author andreasvillumsen
 */
public class TimeManagerTest {
    
    public TimeManagerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getTimers method, of class TimeManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTimers_0args() throws Exception {
        System.out.println("getTimers");
        TimeManager instance = new TimeManager();
        ArrayList<Timer> expResult = null;
        ArrayList<Timer> result = instance.getTimers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class TimeManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        int taskId = 0;
        boolean isBillable = false;
        int user_id = 0;
        TimeManager instance = new TimeManager();
        instance.start(taskId, isBillable, user_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class TimeManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testStop() throws Exception {
        System.out.println("stop");
        TimeManager instance = new TimeManager();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pause method, of class TimeManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testPause() throws Exception {
        System.out.println("pause");
        TimeManager instance = new TimeManager();
        instance.pause();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unpause method, of class TimeManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUnpause() throws Exception {
        System.out.println("unpause");
        TimeManager instance = new TimeManager();
        instance.unpause();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lastTimer method, of class TimeManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testLastTimer() throws Exception {
        System.out.println("lastTimer");
        TimeManager instance = new TimeManager();
        Timer expResult = null;
        Timer result = instance.lastTimer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timerRunning method, of class TimeManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testTimerRunning() throws Exception {
        System.out.println("timerRunning");
        TimeManager instance = new TimeManager();
        boolean expResult = false;
        boolean result = instance.timerRunning();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of spentTime method, of class TimeManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testSpentTime() throws Exception {
        System.out.println("spentTime");
        LocalDateTime startTime = null;
        LocalDateTime stopTime = null;
        TimeManager instance = new TimeManager();
        double expResult = 0.0;
        double result = instance.spentTime(startTime, stopTime);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of totalSpentTime method, of class TimeManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testTotalSpentTime() throws Exception {
        System.out.println("totalSpentTime");
        TimeManager instance = new TimeManager();
        double expResult = 0.0;
        double result = instance.totalSpentTime();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimers method, of class TimeManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTimers_int() throws Exception {
        System.out.println("getTimers");
        int taskId = 0;
        TimeManager instance = new TimeManager();
        ArrayList<Timer> expResult = null;
        ArrayList<Timer> result = instance.getTimers(taskId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
