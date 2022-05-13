/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

/**
 *
 * @author Moez
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;



public class MakeTestController {

    @FXML
    private Button Makebtn;
    
    @FXML
    private BorderPane mainPane;

    @FXML
    void MakeTest(ActionEvent event) {
        
        
        
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("Test_Make.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);

    }
    
    
    

}
