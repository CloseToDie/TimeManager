/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.utils.time;

/**
 *
 * @author andreasvillumsen
 */
public class TimeConverter {
    public static double secondsToMinutes(double d) {
        return d / 60;
    }
    
    public static double secondsToHours(double d) {
        return d / 60 / 60;
    }
}
