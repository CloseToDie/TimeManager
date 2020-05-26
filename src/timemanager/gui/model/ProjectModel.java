package timemanager.gui.model;

import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.Project;
import timemanager.be.Timer;
import timemanager.be.User;
import timemanager.bll.ProjectManager;

/**
 *
 * @author andreasvillumsen
 */
public class ProjectModel {
    private static ProjectModel single_instance = null;
    private ProjectManager pm;
    private ObservableList<Project> projectsForClient;
    
    public static ProjectModel getInstance() throws IOException {
        if (single_instance == null)
            single_instance = new ProjectModel();
        
        return single_instance;
    }
    
    private ProjectModel() throws IOException {
        pm = new ProjectManager();
        projectsForClient = FXCollections.observableArrayList();
    }
    
    public void refreshData(int clientId) {
        projectsForClient.clear();
        projectsForClient.addAll(pm.getProjects(clientId));
    }
    
    public ObservableList<Project> getProjects() {
        ObservableList<Project> allProjects;
        allProjects = FXCollections.observableArrayList();
        
        allProjects.addAll(pm.getProjects());
        
        return allProjects;
    }
    
    public ObservableList<Project> getClientProjects(int clientId) {
        refreshData(clientId);
        return projectsForClient;
    }
    
    public void storeProject(Project project) {
        pm.storeProject(project);
        refreshData(project.getClientId());
    }
    
    public void updateProject(Project project) {
        pm.updateProject(project);
        refreshData(project.getClientId());
    }

    public void deleteProject(Project project) {
        pm.deleteProject(project);
        refreshData(project.getClientId());
    }
 }
