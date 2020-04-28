/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.be;

/**
 *
 * @author andreasvillumsen
 */
public class Project {
    private int id;
    private String name;
    private int clientId;

    /**
     * Project Constructor
     * @param id
     * @param name 
     */
    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the project id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the project id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the project name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the project name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the project clientId
     * @return clientId
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Set the project clientId
     * @param clientId 
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
