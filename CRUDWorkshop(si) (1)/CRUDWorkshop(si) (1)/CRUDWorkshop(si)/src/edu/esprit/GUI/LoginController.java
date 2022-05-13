/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
import java.util.logging.Logger;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Jamil
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TextField Emailtxt;
    @FXML
    private PasswordField Passwordtxt;
       private  final String EMAIL_REGEX = "^[a-zA-Z][a-zA-Z0-9._-]*\\@\\w+(\\.)*\\w+\\.\\w+$";
    private  final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
         String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
       @FXML
    private  void loginfonction(ActionEvent event ) {
        try{
         String  email = Emailtxt.getText();
        String password = Passwordtxt.getText();
        UserService user = new UserService();
       String hashedpassword = user.getPasswordByUsername(email);
        System.out.println(hashedpassword);
             Argon2 argon2 = Argon2Factory.create(
            Argon2Factory.Argon2Types.ARGON2id,
            16,
            32);
            if (argon2.verify(hashedpassword, password) ){
                String role = user.getRole(email);
                 System.out.println("ellllllllllllllllllelelellellelllee" + role);
                 Parent root =FXMLLoader.load(getClass().getResource("../GUI/Backend.fxml"));
                 if ( role.equals("[\"ROLE_ADMIN\"]")){
                       root = FXMLLoader.load(getClass().getResource("../GUI/Backend.fxml"));
                 }
                 if ( role.equals("[\"ROLE_TESTTAKER\"]")){
                    root = FXMLLoader.load(getClass().getResource("../GUI/FrontTaker.fxml"));
                 }
                        if ( role.equals("[\"ROLE_TESTMAKER\"]")){
                       root = FXMLLoader.load(getClass().getResource("../GUI/FrontMaker.fxml"));
                 }
                     if ( role.equals("[\"ROLE_ENTREPRISE\"]")){
                       root = FXMLLoader.load(getClass().getResource("../GUI/FrontEntreprise.fxml"));
                 }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          Scene scene = new Scene(root);
              stage.setScene(scene);
              stage.show();

}else{
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur");
          alert.setContentText("Invalid email or password \n" +
                  "Please try again");
            alert.showAndWait();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
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
     public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
        if(isValidEmail(Emailtxt.getText()) == false){
           System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+isValidEmail(Emailtxt.getText()));
           alert.setContentText("c est n pas un email valid");
            alert.showAndWait();
            return false;
             
        }
//        if(isValidPassword(Passwordtxt.getText(),regex) ==false){
//            alert.setContentText("Must have at least one numeric character\n" +
//                               "Must have at least one lowercase character\n" +
//                                "Must have at least one uppercase character\n" +
//                                  "Must have at least one special symbol among @#$%\n" +
//                                   "Password length should be between 8 and 20");
//            alert.showAndWait();
//            return false;
//        }
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
private void jButton_LOGINActionPerformed(ActionEvent evt) {    
    System.out.println("zzzzzzzz");
        PreparedStatement ps;
        ResultSet rs;
        String email = Emailtxt.getText();
          System.out.println("zzzzz"+email);
        String pass = String.valueOf(Passwordtxt.getText());
        System.out.println("passssss"+pass);
        String query = "select id from user where email=? ";
        
        try {
                Connection cnx;
             cnx =MaConnexion.getInstance().getCnx();
 PreparedStatement ste;
  System.out.println("query"+query);
            ps = cnx.prepareStatement(query);
             System.out.println("ps"+ps); 
            ps.setString(1, email);
            ps.setString(2, pass);
              
            rs = ps.executeQuery();
             System.out.println("executeeeeeeeeeeeeeeeee"); 
             System.out.println(rs.next());
            if(rs.next())
            {
                      System.out.println("zzzzzzzz");
  try {
           
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Backend.fxml"));  //charger les fichiers fxml
                     Stage primaryStage = new Stage();
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("Ajouter User !");
                    primaryStage.setScene(scene);
                    primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            }
            else{
                    JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
                }
            
        } catch (SQLException ex) {
           
        }
        
        
    }                      
}
