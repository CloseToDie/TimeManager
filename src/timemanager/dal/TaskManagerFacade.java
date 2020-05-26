/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.dal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import timemanager.be.Project;
import timemanager.be.Task;
import timemanager.be.Timer;
import timemanager.be.User;

/**
 *
 * @author andreasvillumsen
 */
public interface TaskManagerFacade {
    public ArrayList<Task> getTasks(int projectId);
    
    public boolean storeTask(Task task);
    
    public boolean updateTask(Task task);
    
    public boolean deleteTask(Task task);
    
}
