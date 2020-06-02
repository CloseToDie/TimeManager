/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import timemanager.TimeManagerStart;
import timemanager.be.Project;
import timemanager.be.Task;
import timemanager.gui.model.ProjectModel;
import timemanager.gui.model.TaskModel;
import timemanager.utils.validation.Validate;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen & Christian
 */
public class TaskCreateController implements Initializable {

    TimeManagerStart tms = new TimeManagerStart();
    TaskModel tm;
    ProjectModel pm;
    private Project project;
    private Task task;
    
    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox<Project> projectSelect;
    @FXML
    private TextArea description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tm = TaskModel.getInstance();
            pm = ProjectModel.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(TaskCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    /**
     * Set the task project
     * @param project 
     */
    public void setProject(Project project) {
        this.project = project;
        projectSelect.setItems(pm.getClientProjects(project.getClientId()));
        projectSelect.setValue(project);
    }
    
    /**
     * Set the task variable if we are updating a task
     * @param task 
     */
    public void setTask(Task task) {
        this.task = task;
        if(task != null) setupFields();
    }

    /**
     * Create or Update task
     * @param event 
     */
    @FXML
    private void saveTask(ActionEvent event) {
        String desc = description.getText();
        int projectId = projectSelect.getValue().getId();
        
        if(validateDescription(desc) && validateProject(projectId)) {
            if(task != null) {
                task.setDescription(desc);
                task.setProjectId(projectId);
                tm.updateTask(task);
            } else {
                tm.storeTask(new Task(0, desc, projectId));
            }
            
            Stage window = (Stage) (errorLabel.getScene().getWindow());
            window.close();
        } else {
            errorLabel.setVisible(true);
        }
    }

    /**
     * Close the window
     * @param event 
     */
    @FXML
    private void cancelSaveTask(ActionEvent event) {
        Stage window = (Stage) (errorLabel.getScene().getWindow());
        window.close();
    }
    
    /**
     * Validate the description
     * @param desc
     * @return boolean
     */
    private boolean validateDescription(String desc) {
        return !(Validate.isNull(desc) || !Validate.containsAtLeast(desc, 3));
    }
    
    /**
     * Validate the select project
     * @param projectId
     * @return boolean
     */
    private boolean validateProject(Integer projectId) {
        return projectId != null;
    }

    /**
     * Setup fields if we are updating task
     */
    private void setupFields() {
        description.setText(task.getDescription());
    }
}
