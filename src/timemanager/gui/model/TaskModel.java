/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.gui.model;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.Task;
import timemanager.bll.TaskManager;

/**
 *
 * @author andreasvillumsen
 */
public class TaskModel {
    private static TaskModel single_instance = null; 
    private TaskManager tm;
    private ObservableList<Task> tasks;
    
    public static TaskModel getInstance() throws IOException {
        if (single_instance == null) 
            single_instance = new TaskModel(); 
  
        return single_instance; 
    }

    private TaskModel() throws IOException {
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
    
}
