/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import timemanager.be.Project;
import timemanager.be.Task;
import timemanager.be.Timer;
import timemanager.be.User;
import timemanager.dal.TaskManagerFacade;
import timemanager.dal.TimeManagerFacade;
import timemanager.dal.database.TaskManagerDBDAO;
import timemanager.dal.database.TimeManagerDBDAO;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class TaskManager {
    TaskManagerFacade tm;
    TimeManagerFacade timeManager;

    /**
     * TaskManager constructor
     * @throws Exception 
     */
    public TaskManager() throws Exception {
        tm = new TaskManagerDBDAO();
        timeManager = new TimeManagerDBDAO();
    }
    
    /**
     * Get a list of tasks for a project
     * @param projectId
     * @return tasks
     */
    public ArrayList<Task> getTasks(int projectId) {
        return tm.getTasks(projectId);
    }
    
    /**
     * Get all timers for a project between two dates.
     * @param project
     * @param user
     * @param start
     * @param end
     * @return timers
     */
    public ArrayList<Timer> getProjectTimers(Project project, User user, LocalDate start, LocalDate end) {
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Timer> timers = new ArrayList<>();
        
        tasks.addAll(tm.getTasks(project.getId()));
        
        tasks.forEach((task) -> {
            timers.addAll(timeManager.getStatTimers(task.getId(), user, start, end));
        });
        
        return timers;
    }

    /**
     * Store a new task
     * @param task 
     */
    public void storeTask(Task task) {
        tm.storeTask(task);
    }

    /**
     * Update a task
     * @param task 
     */
    public void updateTask(Task task) {
        tm.updateTask(task);
    }

    /**
     * Delete a task
     * @param task 
     */
    public void deleteTask(Task task) {
        tm.deleteTask(task);
    }
}
