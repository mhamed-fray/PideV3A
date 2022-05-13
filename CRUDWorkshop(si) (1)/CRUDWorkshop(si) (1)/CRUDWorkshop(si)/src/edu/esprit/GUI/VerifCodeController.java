/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jamil
 */
public class VerifCodeController implements Initializable {

    /**
     * Initializes the controller class.
     */
             @FXML
    private TextField CodeTxt;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    CodeTxt.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            CodeTxt.setText(newValue.replaceAll("[^\\d]", ""));
        }else  if (CodeTxt.getText().length() > 6) {
                String s = CodeTxt.getText().substring(0, 6);
                CodeTxt.setText(s);
            }
    }
});
    }    
   @FXML
private void gotoregister(ActionEvent event) {
            try {
           
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Register.fxml"));  //charger les fichiers fxml
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();stage.setUserData(stage);
                    Scene scene = new Scene(root);
                  stage.setScene(scene);
              stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
 }  
   @FXML
private void sendCode(ActionEvent event) {

    
    if(Integer.parseInt(CodeTxt.getText()) == 485948){
         Node node = (Node) event.getSource();
  Stage stage = (Stage) node.getScene().getWindow();
  // Step 2
  String email= (String) stage.getUserData();
 System.out.println("l emaiiiiiiiiiiiiiiiiiil" + email);
            try {
           
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ChangePassword.fxml"));  //charger les fichiers fxml
           stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setUserData(email);
                    Scene scene = new Scene(root);
                  stage.setScene(scene);
              stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       }  
    else{
                     Alert alert = new Alert(Alert.AlertType.ERROR);

         alert.setContentText("Incorrect code ");
            alert.showAndWait();            
    }
 }  
  
}
