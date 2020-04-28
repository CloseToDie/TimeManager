/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.bll;

import java.util.ArrayList;
import timemanager.be.Project;
import timemanager.dal.ProjectManagerFacade;
import timemanager.dal.database.ProjectManagerDBDAO;

/**
 *
 * @author andreasvillumsen
 */
public class ProjectManager {
    public ArrayList<Project> projects = new ArrayList<>();
    
    ProjectManagerFacade pm;

    public ProjectManager() {
        pm = new ProjectManagerDBDAO();
    }
    
    
    
    public ArrayList<Project> getProjects() {
        return projects;
    }
    
    public Project getProject(Project project) {
        return pm.getProject(project);
    }
}
