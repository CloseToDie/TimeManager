/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import java.io.IOException;
import java.util.ArrayList;
import timemanager.be.Task;
import timemanager.dal.TaskManagerFacade;
import timemanager.dal.database.TaskManagerDBDAO;

/**
 *
 * @author andreasvillumsen
 */
public class TaskManager {
    TaskManagerFacade tm;

    public TaskManager() throws IOException {
        tm = new TaskManagerDBDAO();
    }
    
    public ArrayList<Task> getTasks(int projectId) {
        return tm.getTasks(projectId);
    }
}
