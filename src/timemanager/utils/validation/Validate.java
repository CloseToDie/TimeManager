/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.utils.validation;

/**
 *
 * @author andreasvillumsen
 */
public class Validate {
    public static boolean isNull(String text) {
        return text == null;
    }
    
    public static boolean containsAtLeast(String text, int amount) {
        return text.length() >= amount;
    }
}
