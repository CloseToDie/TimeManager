package timemanager.bll;

import java.io.IOException;
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

    /**
     * ProjectManager Constructor
     * @throws IOException 
     */
    public ProjectManager() throws IOException {
        pm = new ProjectManagerDBDAO();
    }
    
    /**
     * Get all the projects from DAL
     * @return projects
     */
    public ArrayList<Project> getProjects() {
        return projects;
    }
    
    /**
     * Get a project.
     * @param project
     * @return project
     */
    public Project getProject(Project project) {
        return pm.getProject(project);
    }
}
