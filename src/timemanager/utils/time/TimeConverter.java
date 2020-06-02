/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.utils.time;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class TimeConverter {
    /**
     * Convert seconds to minutes
     * @param d
     * @return double (minutes)
     */
    public static double secondsToMinutes(double d) {
        return d / 60;
    }
    
    /**
     * Convert seconds to hours
     * @param d
     * @return double (hours)
     */
    public static double secondsToHours(double d) {
        return d / 60 / 60;
    }
}
