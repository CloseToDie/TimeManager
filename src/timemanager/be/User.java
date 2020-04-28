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

    /**
     * User Constructor
     * @param id
     * @param name
     * @param email 
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

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

}
