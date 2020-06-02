/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.be;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class Task {
    private int id;
    private String description;
    private int projectId;

    /**
     * Task Constructor
     * @param id
     * @param description
     * @param projectId 
     */
    public Task(int id, String description, int projectId) {
        this.id = id;
        this.description = description;
        this.projectId = projectId;
    }

    /** 
     * Get the task id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the task id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the task description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the task description
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the project id
     * @return projectId
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Set the project id
     * @param projectId 
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    /**
     * Class toString method
     * @return description
     */
    @Override
    public String toString() {
        return this.getDescription();
    }
    
}
