package timemanager.be;

/**
 *
 * @author kaspe & andreasvillumsen
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private byte[] salt;
    private boolean isAdmin;

    /**
     * User Constructor
     * @param id
     * @param name
     * @param email 
     * @param password 
     * @param salt 
     * @param isAdmin 
     */
    public User(int id, String name, String email, String password, byte[] salt, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.isAdmin = isAdmin;
    }

    /**
     * Get the user's id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the user's id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the user's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the user's name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the user's email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user's email
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the user's password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the salt
     * @return salt
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     * Set the salt
     * @param salt 
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    /**
     * Get user admin status
     * @return isAdmin
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Set user admin status
     * @param isAdmin 
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    /**
     * Class toString method
     * @return name
     */
    @Override
    public String toString() {
        return this.getName();
    }

}
