/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.gui.model;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.Project;
import timemanager.be.Task;
import timemanager.be.Timer;
import timemanager.be.User;
import timemanager.bll.TaskManager;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class TaskModel {
    private static TaskModel single_instance = null; 
    private TaskManager tm;
    private ObservableList<Task> tasks;
    
    /**
     * Get TaskModel instance
     * @return TaskModel
     * @throws Exception 
     */
    public static TaskModel getInstance() throws Exception {
        if (single_instance == null) 
            single_instance = new TaskModel(); 
  
        return single_instance; 
    }

    /**
     * TaskModel constructor
     * @throws Exception 
     */
    private TaskModel() throws Exception {
        tm = new TaskManager();
        tasks = FXCollections.observableArrayList();
    }
    
    /**
     * Refresh tasks arraylist data
     * @param projectId 
     */
    public void refreshData(int projectId) {
        tasks.clear();
        tasks.addAll(tm.getTasks(projectId));
    }
    
    /**
     * Get ObservableList of tasks for a project
     * @param projectId
     * @return 
     */
    public ObservableList<Task> getTasks(int projectId) {
        refreshData(projectId);
        return tasks;
    }

    /**
     * Store a new task
     * @param task 
     */
    public void storeTask(Task task) {
        tm.storeTask(task);
        refreshData(task.getProjectId());
    }

    /**
     * Update a task
     * @param task 
     */
    public void updateTask(Task task) {
        tm.updateTask(task);
        refreshData(task.getProjectId());
    }
    
    /**
     * Delete a task
     * @param task 
     */
    public void deleteTask(Task task) {
        tm.deleteTask(task);
        refreshData(task.getProjectId());
    }
    
    /**
     * Get all timers for a project
     * @param project
     * @param user
     * @param start
     * @param end
     * @return project timers
     */
    public ObservableList<Timer> getProjectTimers(Project project, User user, LocalDate start, LocalDate end) {
        ObservableList<Timer> timers;
        timers = FXCollections.observableArrayList();
        
        timers.clear();
        timers.addAll(tm.getProjectTimers(project, user, start, end));
        
        return timers;
    }
    
}
