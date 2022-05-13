/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import static edu.esprit.GUI.GestionUserController.isValidPassword;
import edu.esprit.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jamil
 */
public class ChangePasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
                @FXML
    private PasswordField newPasstxt;
    String email = "";          
     String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void changepass(ActionEvent event){
        if(controlSaisie()){
     Node node = (Node) event.getSource();
  Stage stage = (Stage) node.getScene().getWindow();
   email= (String) stage.getUserData();
   UserService ts = new UserService();

      if(ts.FindUserbyEmail(email) == true ) {
       String  password= newPasstxt.getText();
          String encryptedpassword = null;  
         ts.saveNewPassword(email, password);
          try {
           
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Login.fxml"));  //charger les fichiers fxml
           stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setUserData(stage);
                    Scene scene = new Scene(root);
                  stage.setScene(scene);
              stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      }
        }
    }
      public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
        if(isValidPassword(newPasstxt.getText(),regex) ==false){
            alert.setContentText("Must have at least one numeric character\n" +
                               "Must have at least one lowercase character\n" +
                                "Must have at least one uppercase character\n" +
                                  "Must have at least one special symbol among @#$%\n" +
                                   "Password length should be between 8 and 20");
            alert.showAndWait();
            return false;
        }

        return true;
    }
       @FXML
   private void gotoregister(ActionEvent event) {
            try {
           
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Register.fxml"));  //charger les fichiers fxml
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                  stage.setScene(scene);
              stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
 }    
    public static boolean isValidPassword(String password,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
         
}
