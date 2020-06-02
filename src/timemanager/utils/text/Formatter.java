/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.utils.text;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class Formatter {
    /**
     * Format the string to 2f
     * @param d
     * @param suffix
     * @return 2f string
     */
    public static String doubleTo2f(double d, String suffix) {
        return String.format("%,.2f", d) + " " + suffix;
    }
}
