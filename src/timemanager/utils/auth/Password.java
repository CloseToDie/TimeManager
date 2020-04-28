/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.utils.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author andreasvillumsen
 */
public class Password {
    
    public static void main(String[] args) throws NoSuchAlgorithmException 
    {
        String passwordToHash = "password";
        byte[] salt = getSalt();
         
        String securePassword = hash(passwordToHash, salt, "SHA-512");
        System.out.println(securePassword);
        
        Boolean passwordMatches = verifyPassword(passwordToHash, securePassword, salt, "SHA-512");
        System.out.println("Password matches: " + passwordMatches);
    }
    
    /**
     * Hash a given password
     * @param passwordToHash
     * @param salt
     * @param SHA
     * @return Hashed password
     */
    private static String hash(String passwordToHash, byte[] salt, String SHA) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(SHA);
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    /**
     * Verify the given plain text password matches a hashed password
     * @param plainPass
     * @param hashedPass
     * @param salt
     * @param SHA
     * @return boolean
     */
    public static boolean verifyPassword(String plainPass, String hashedPass, byte[] salt, String SHA) {
        return hash(plainPass, salt, SHA).equals(hashedPass);
    }
    
    /**
     * Generate salt to secure password
     * @return salt
     * @throws NoSuchAlgorithmException 
     */
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
