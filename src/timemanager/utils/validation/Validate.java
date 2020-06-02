/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.utils.validation;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class Validate {
    /**
     * Check if string is null
     * @param text
     * @return boolean
     */
    public static boolean isNull(String text) {
        return text == null;
    }
    
    /**
     * Check if string contains at least x amount of characters.
     * @param text
     * @param amount
     * @return boolean
     */
    public static boolean containsAtLeast(String text, int amount) {
        return text.length() >= amount;
    }
}
