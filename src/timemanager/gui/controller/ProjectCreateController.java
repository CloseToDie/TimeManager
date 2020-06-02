package timemanager.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import timemanager.TimeManagerStart;
import timemanager.be.Client;
import timemanager.be.Project;
import timemanager.gui.model.ClientModel;
import timemanager.gui.model.ProjectModel;
import timemanager.utils.validation.Validate;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen & Christian
 */
public class ProjectCreateController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    ProjectModel pm;
    ClientModel cm;
    
    Client client;
    Project project;
    
    @FXML
    private Label errorLabel;
    @FXML
    private TextField projectName;
    @FXML
    private Button saveProjectButton;
    @FXML
    private Button cancelSaveProject;
    @FXML
    private TextField projectSalary;
    @FXML
    private ComboBox<Client> clientSelect;
    
    /**
     * Class initialization
     * @param arg0
     * @param arg1 
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            pm = ProjectModel.getInstance();
            cm = ClientModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        clientSelect.setItems(cm.getClients());
    }
    
    /**
     * Set the project client
     * @param client 
     */
    public void setClient(Client client) {
        this.client = client;
        clientSelect.setValue(client);
    }
    
    /**
     * Set the project, if we are updating instead of creating
     * @param project 
     */
    public void setProject(Project project) {
        this.project = project;
        if(project != null) setupFields();
    }
    
    /**
     * Create / Update the project
     * @param event 
     */
    @FXML
    private void saveProject(ActionEvent event) {
        String name = projectName.getText();
        int clientId = clientSelect.getValue().getId();
        if(projectSalary.getText() != null && !"".equals(projectSalary.getText())) {
            Double salary = Double.parseDouble(projectSalary.getText());
            
            if(validateName(name) && validateClient(clientId)) {
                if(project != null) {
                    project.setClientId(clientId);
                    project.setName(name);
                    project.setSalary(salary);
                    pm.updateProject(project);
                } else {
                    pm.storeProject(new Project(0, name, salary, clientId));
                }

                Stage window = (Stage) (cancelSaveProject.getScene().getWindow());
                window.close();
            } else {
                errorLabel.setVisible(true);
            }
        } else {
            errorLabel.setVisible(true);
        }
    }

    /**
     * Close the window
     * @param event 
     */
    @FXML
    private void cancelSaveProject(ActionEvent event) {
        Stage window = (Stage) (cancelSaveProject.getScene().getWindow());
        window.close();
    }
    
    /**
     * Validate the name input
     * @param name
     * @return 
     */
    private boolean validateName(String name) {
        return !(Validate.isNull(name) || !Validate.containsAtLeast(name, 3));
    }
    
    /**
     * Validate the client input
     * @param clientId
     * @return 
     */
    private boolean validateClient(Integer clientId) {
        return !(clientId == null);
    }

    /**
     * Setup all the fields.
     * Is being run when we want to update client.
     */
    private void setupFields() {
        projectName.setText(project.getName());
        projectSalary.setText(String.valueOf(project.getSalary()));
    }
}
