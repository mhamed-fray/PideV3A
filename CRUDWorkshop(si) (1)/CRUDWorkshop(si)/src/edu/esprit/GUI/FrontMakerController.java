/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Jamil
 */
public class FrontMakerController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private BorderPane mainPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        @FXML
    private void goToDoTest(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("TestMakerQuestion.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
}
