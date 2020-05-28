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
 * @author andreasvillumsen
 */
public class TaskModel {
    private static TaskModel single_instance = null; 
    private TaskManager tm;
    private ObservableList<Task> tasks;
    
    public static TaskModel getInstance() throws Exception {
        if (single_instance == null) 
            single_instance = new TaskModel(); 
  
        return single_instance; 
    }

    private TaskModel() throws Exception {
        tm = new TaskManager();
        tasks = FXCollections.observableArrayList();
    }
    
    public void refreshData(int projectId) {
        tasks.clear();
        tasks.addAll(tm.getTasks(projectId));
    }
    
    public ObservableList<Task> getTasks(int projectId) {
        refreshData(projectId);
        return tasks;
    }

    public void storeTask(Task task) {
        tm.storeTask(task);
        refreshData(task.getProjectId());
    }

    public void updateTask(Task task) {
        tm.updateTask(task);
        refreshData(task.getProjectId());
    }
    
    public void deleteTask(Task task) {
        tm.deleteTask(task);
        refreshData(task.getProjectId());
    }
    
    public ObservableList<Timer> getProjectTimers(Project project, User user, LocalDate start, LocalDate end) {
        ObservableList<Timer> timers;
        timers = FXCollections.observableArrayList();
        
        timers.clear();
        timers.addAll(tm.getProjectTimers(project, user, start, end));
        
        return timers;
    }
    
}
