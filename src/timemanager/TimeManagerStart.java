package timemanager;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import timemanager.be.Client;
import timemanager.be.Project;
import timemanager.be.Task;
import timemanager.be.User;
import timemanager.gui.controller.ClientCreateController;
import timemanager.gui.controller.ProjectController;
import timemanager.gui.controller.TaskController;
import timemanager.gui.controller.TaskCreateController;
import timemanager.gui.controller.ProjectCreateController;
import timemanager.gui.controller.UserCreateController;

/**
 *
 * @author andreasvillumsen
 */
public class TimeManagerStart extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void set(Stage stage, String view) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/" + view + ".fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void showProjects(Stage stage, Client client) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/view/Project.fxml"));
        
        Region root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        ProjectController projectController = loader.getController();
        projectController.setClient(client);
        
        stage.show();
    }
    
    public void showTasks(Stage stage, Project project) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/view/Task.fxml"));
        
        Region root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        TaskController taskController = loader.getController();
        taskController.setProject(project);
        
        stage.show();
    }
    
    public void showProjectsCreate(Client client) throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/view/ProjectCreate.fxml"));
        
        Region root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        ProjectCreateController projectCreateController = loader.getController();
        projectCreateController.setClient(client);
        
        stage.showAndWait();
    }
    
    public void showTasksCreate(Project project) throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/view/TaskCreate.fxml"));
        
        Region root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        TaskCreateController taskCreateController = loader.getController();
        taskCreateController.setProject(project);
        
        stage.showAndWait();
    }
    
    public void editClient(Client client) throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/view/ClientCreate.fxml"));
        
        Region root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        ClientCreateController clientCreateController = loader.getController();
        clientCreateController.setClient(client);
        
        stage.showAndWait();
    }
    
    public void editProject(Project project, Client client) throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/view/ProjectCreate.fxml"));
        
        Region root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        ProjectCreateController projectCreateController = loader.getController();
        projectCreateController.setClient(client);
        projectCreateController.setProject(project);
        
        stage.showAndWait();
    }
    
    public void editTask(Task task, Project project) throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/view/TaskCreate.fxml"));
        
        Region root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        TaskCreateController taskCreateController = loader.getController();
        taskCreateController.setTask(task);
        taskCreateController.setProject(project);
        
        stage.showAndWait();
    }
    
    public void editUser(User user) throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/view/UserCreate.fxml"));
        
        Region root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        UserCreateController userCreateController = loader.getController();
        userCreateController.setUser(user);
        
        stage.showAndWait();
    }
    
    public void popup(String view) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/" + view + ".fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.showAndWait();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
