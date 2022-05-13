/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.User;
import edu.esprit.services.UserService;
import edu.esprit.tools.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.scene.Node;
/**
 * FXML Controller class
 *
 * @author Jamil
 */
public class RegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
          @FXML
    private TextField Emailtxt;
    @FXML
    private TextField Passwordtxt;
       private  final String EMAIL_REGEX = "^[a-zA-Z][a-zA-Z0-9._-]*\\@\\w+(\\.)*\\w+\\.\\w+$";
    private  final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
         String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 
          @FXML
    private void register(ActionEvent event) {
        if ( controlSaisie()){
         
         UserService ts = new UserService();
             ts.registeruser(new User(Emailtxt.getText(),Passwordtxt.getText()));
           try {
           
             Parent root = FXMLLoader.load(getClass().getResource("../GUI/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          Scene scene = new Scene(root);
              stage.setScene(scene);
              stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }     
       
    }}
        @FXML
   private void gotologin(ActionEvent event) {
            try {
           
             Parent root = FXMLLoader.load(getClass().getResource("../GUI/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          Scene scene = new Scene(root);
              stage.setScene(scene);
              stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
 }  
     public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("Input error");
        if(isValidEmail(Emailtxt.getText()) == false){
           System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+isValidEmail(Emailtxt.getText()));
           alert.setContentText("The email " + Emailtxt.getText()+ "is not a valid email");
            alert.showAndWait();
            return false;
             
        }
        if(isValidPassword(Passwordtxt.getText(),regex) ==false){
            alert.setContentText("Must have at least one numeric character\n" +
                               "Must have at least one lowercase character\n" +
                                "Must have at least one uppercase character\n" +
                                  "Must have at least one special symbol among @#$%\n" +
                                   "Password length should be between 8 and 20");
            alert.showAndWait();
            return false;
        }
        UserService us = new UserService();
        if(us.FindUserbyEmail(Emailtxt.getText()) == true){
           alert.setContentText("The email " + Emailtxt.getText()+ "is already used\n" + "Please try another");
            alert.showAndWait();
            return false;            
        }
        return true;
    }
      public  boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
       public static boolean isValidPassword(String password,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
   @FXML
   private void gotoResetPass(ActionEvent event) {
            try {
           
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ForgetPassword.fxml"));  //charger les fichiers fxml
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                  stage.setScene(scene);
              stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
 }      
}
