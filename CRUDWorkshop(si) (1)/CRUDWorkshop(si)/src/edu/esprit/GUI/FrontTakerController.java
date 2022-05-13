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
public class FrontTakerController implements Initializable {

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
    private void goToAbonnement(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("FrontAbonnement.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
                @FXML
    private void goToOffres(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("front.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
                @FXML
    private void goToEvenements(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("GestionQuestion.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
                @FXML
    private void goToQuiz(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("quiz.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
    
    
}
