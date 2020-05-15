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
 * @author andreasvillumsen
 */
public class ProjectCreateController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    ProjectModel pm;
    ClientModel cm;
    
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
    
    @FXML
    private void saveProject(ActionEvent event) {
        String name = projectName.getText();
        double salary = Double.parseDouble(projectSalary.getText());
        int clientId = clientSelect.getValue().getId();
        
        if(validateName(name)) {
            pm.storeProject(new Project(0, name, salary, clientId));
            Stage window = (Stage) (cancelSaveProject.getScene().getWindow());
            window.close();
        } else {
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void cancelSaveProject(ActionEvent event) {
        Stage window = (Stage) (cancelSaveProject.getScene().getWindow());
        window.close();
    }
    
    private boolean validateName(String name) {
        return !(Validate.isNull(name) || !Validate.containsAtLeast(name, 3));
    }
}
