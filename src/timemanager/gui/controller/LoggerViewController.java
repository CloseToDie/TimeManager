/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.gui.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen
 */
public class LoggerViewController implements Initializable {

    @FXML
    private JFXTextField projectNameField;
    @FXML
    private TextField MPHField;
    @FXML
    private TableColumn<?, ?> startedColumn;
    @FXML
    private TableColumn<?, ?> stoppedColumn;
    @FXML
    private TableColumn<?, ?> dateColumn;
    @FXML
    private TableColumn<?, ?> usedColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logOutBtn(MouseEvent event)
    {
    }

    @FXML
    private void startButton(MouseEvent event)
    {
    }

    @FXML
    private void stopButton(MouseEvent event)
    {
    }
    
}
