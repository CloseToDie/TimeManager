package timemanager.gui.model;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.Project;
import timemanager.bll.ProjectManager;

/**
 *
 * @author andreasvillumsen
 */
public class ProjectModel {
    private static ProjectModel single_instance = null;
    private ProjectManager pm;
    private ObservableList<Project> projects;
    
    public static ProjectModel getInstance() throws IOException {
        if (single_instance == null)
            single_instance = new ProjectModel();
        
        return single_instance;
    }
    
    private ProjectModel() throws IOException {
        pm = new ProjectManager();
        projects = FXCollections.observableArrayList();
    }
    
    public void refreshData() {
        projects.clear();
        projects.addAll(pm.getProjects());
    }
    
    public ObservableList<Project> getProjects() {
        refreshData();
        return projects;
    }
    
    public void storeProject(Project project) {
        pm.storeProject(project);
        refreshData();
    }
 }
