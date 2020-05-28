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
 * @author andreasvillumsen
 */
public interface TaskManagerFacade {
    public ArrayList<Task> getTasks(int projectId);
    
    public boolean storeTask(Task task);
    
    public boolean updateTask(Task task);
    
    public boolean deleteTask(Task task);
    
}
