package timemanager.be;

/**
 *
 * @author andreasvillumsen
 */
public class Client {
    private int id;
    private String name;

    /**
     * Client Constructor
     * @param id
     * @param name 
     */
    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the client id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the client id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the client name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the client name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
