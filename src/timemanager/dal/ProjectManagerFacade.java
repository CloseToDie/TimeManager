package timemanager.dal;

import java.util.ArrayList;
import timemanager.be.Project;

/**
 *
 * @author andreasvillumsen
 */
public interface ProjectManagerFacade {
    
    /**
     * Get projects
     * @return projects
     */
    public ArrayList<Project> getProjects();
    
    /**
     * Get all projects for a client.
     * @param clientId
     * @return 
     */
    public ArrayList<Project> getProjects(int clientId);
    
    /**
     * Get a project
     * @param project
     * @return project
     */
    public Project getProject(Project project);
    
    /**
     * Store a new project
     * @param project
     * @return boolean
     */
    public boolean storeProject(Project project);
    
    /**
     * Update a project in database
     * @param project
     * @return boolean
     */
    public boolean updateProject(Project project);
    
    /**
     * Delete a project from database
     * @param project
     * @return boolean
     */
    public boolean deleteProject(Project project);
}
