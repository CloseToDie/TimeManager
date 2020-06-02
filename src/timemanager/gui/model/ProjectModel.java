package timemanager.gui.model;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.Project;
import timemanager.bll.ProjectManager;

/**
 *
 * @author andreasvillumsen & Christian
 */
public class ProjectModel {
    private static ProjectModel single_instance = null;
    private ProjectManager pm;
    private ObservableList<Project> projectsForClient;
    
    /**
     * Get ProjectModel instance
     * @return ProjectModel
     * @throws IOException 
     */
    public static ProjectModel getInstance() throws IOException {
        if (single_instance == null)
            single_instance = new ProjectModel();
        
        return single_instance;
    }
    
    /**
     * ProjectModel constructor
     * @throws IOException 
     */
    private ProjectModel() throws IOException {
        pm = new ProjectManager();
        projectsForClient = FXCollections.observableArrayList();
    }
    
    /**
     * Refresh projects for the client arraylist data
     * @param clientId 
     */
    public void refreshData(int clientId) {
        projectsForClient.clear();
        projectsForClient.addAll(pm.getProjects(clientId));
    }
    
    /**
     * Get ObservableList of Projects
     * @return projects
     */
    public ObservableList<Project> getProjects() {
        ObservableList<Project> allProjects;
        allProjects = FXCollections.observableArrayList();
        
        allProjects.addAll(pm.getProjects());
        
        return allProjects;
    }
    
    /**
     * Get ObservableList of projects for a client
     * @param clientId
     * @return projects for a client
     */
    public ObservableList<Project> getClientProjects(int clientId) {
        refreshData(clientId);
        return projectsForClient;
    }
    
    /**
     * Store a project to database
     * @param project 
     */
    public void storeProject(Project project) {
        pm.storeProject(project);
        refreshData(project.getClientId());
    }
    
    /**
     * Update a project in database
     * @param project 
     */
    public void updateProject(Project project) {
        pm.updateProject(project);
        refreshData(project.getClientId());
    }

    /**
     * Delete a project from database
     * @param project 
     */
    public void deleteProject(Project project) {
        pm.deleteProject(project);
        refreshData(project.getClientId());
    }
 }
