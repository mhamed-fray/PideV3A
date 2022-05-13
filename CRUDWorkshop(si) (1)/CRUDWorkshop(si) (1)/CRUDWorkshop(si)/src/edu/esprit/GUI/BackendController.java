/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Jamil
 */
public class BackendController implements Initializable {

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
    private void goToDashhboard(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("AbonnementStat.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
    @FXML
    private void goToCrudUser(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("GestionUser.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
    @FXML
    private void goToCrudAbonnement(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("GestionAbonnement.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
    @FXML
    private void goToCrudTypeAbonnement(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("GestionType.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
    @FXML
    private void goToCrudTest(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("Gestionnaire_Test.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
    @FXML
    private void goToCrudQuestion(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("GestionQuestion.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
        @FXML
    private void goToReservation(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("FrontAbonnement.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
        @FXML
    private void goToCandidature(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("tablecand.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
        @FXML
    private void goToOffre(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("tableView.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
        @FXML
    private void goToEvenement(ActionEvent event) {
        Parent root = null ;
       try{
           root= FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       }catch(IOException e){
          // Logger.getLogger(Si)
       }
       mainPane.setCenter(root);
    } 
}
