/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mikkel H
 */
public class LoginViewController implements Initializable
{

    @FXML
    private Button loginButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void LoginUser(ActionEvent event) throws Exception
    {
        Parent loader = FXMLLoader.load(getClass().getResource("/timemanager/gui/view/LoggerView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader);
        
        stage.setScene(scene);
        stage.show();
    }

    private void cancelLogin(ActionEvent event) throws Exception
    {
        Stage stage = new Stage();
        Parent loader = FXMLLoader.load(getClass().getResource("gui/view/AdminView.fxml"));
        
        Scene scene = new Scene(loader);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
