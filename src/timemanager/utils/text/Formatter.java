/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.utils.text;

/**
 *
 * @author andreasvillumsen
 */
public class Formatter {
    public static String doubleTo2f(double d, String suffix) {
        return String.format("%,.2f", d) + " " + suffix;
    }
}
