/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.dal;

import java.util.ArrayList;
import timemanager.be.Task;

/**
 *
 * @author andreasvillumsen & Christian
 */
public interface TaskManagerFacade {
    /**
     * Get all tasks for a project
     * @param projectId
     * @return tasks
     */
    public ArrayList<Task> getTasks(int projectId);
    
    /**
     * Store a new task
     * @param task
     * @return boolean
     */
    public boolean storeTask(Task task);
    
    /**
     * Update a task
     * @param task
     * @return boolean
     */
    public boolean updateTask(Task task);
    
    /**
     * Delete a task
     * @param task
     * @return boolean
     */
    public boolean deleteTask(Task task);
    
}
