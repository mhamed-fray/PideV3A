/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Test;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Moez
 */
public class TestToDeleteController {
    
    
    @FXML
    private AnchorPane mypane;
    
    @FXML
    private Button buttontotest;
    
    public static int id_ider;
    
    
    @FXML
    private void sendData(ActionEvent event) {
        
        
        try {    
              
               TestToDeleteController.id_ider=10;
                    
                    ///////////////////////////////////////////////////
                    System.out.println("here 1");
                    
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    System.out.println("here 2");
                    fxmlLoader.setLocation(getClass().getResource("GestionQuestion.fxml"));
                    System.out.println("here 3");
                    
                    System.out.println("here 4");
                    
                    GestionQuestionController itemController = new GestionQuestionController();
                    System.out.println("here 5");
                    
                    
                    System.out.println("here 6");
                    
                    fxmlLoader.setController(itemController);
                    System.out.println("here 7");
                    
                    
                    Parent page2 = FXMLLoader.load(getClass().getResource("GestionQuestion.fxml"));
                    System.out.println("here 8");
                    
                    Scene scene = new Scene(page2);
                    System.out.println("here 9");
                    
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    System.out.println("here 10");
                    
                    stage.setScene(scene);
                    System.out.println("here 11");
                    

                    stage.show();
                    System.out.println("here 12");
                
                
                
                            
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
                    

            
        
        

    }
    
    
    
    @FXML
    public int get_thething(){
        return this.id_ider;
    }
    
    @FXML
    public void set_thething(int id){
        this.id_ider = id;
    }
    
    
    
    
}
