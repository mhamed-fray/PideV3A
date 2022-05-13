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
import java.util.Properties;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Jamil
 */
public class ForgetPasswordController implements Initializable {
          @FXML
    private TextField Emailtxt;
    private  final String EMAIL_REGEX = "^[a-zA-Z][a-zA-Z0-9._-]*\\@\\w+(\\.)*\\w+\\.\\w+$";
    private String email;
    private  final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
         String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 @FXML
public void sendmail(ActionEvent event){
       email =Emailtxt.getText();
    if (controlSaisie(email)){
      
             Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
     
          UserService ts = new UserService();
if(ts.FindUserbyEmail(email) == true ) {

            String host="tesrhire@gmail.com";  //← my email address
        final String user="tesrhire@gmail.com";//← my email address
        final String password="191ABC1153";//change accordingly   //← my email password

        String to=email;//→ the EMAIL i want to send TO →

        // session object
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //My message :
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(" Reset Password !!! ");
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
            message.setContent(
                    "to reset your password please enter this code 485948",
                    "text/html");
//String a = "[\"TEST_MAKER\"]" ;
            //send the message
            Transport.send(message);
          alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Success");
     //    alert.setHeaderText("Erreur de saisie");
   email =Emailtxt.getText();
   
           alert.setContentText("message sent successfully ");
            alert.showAndWait();             
               try {
           
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/VerifCode.fxml"));  //charger les fichiers fxml
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          stage.setUserData(email);
                    Scene scene = new Scene(root);
                  stage.setScene(scene);
              stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {e.printStackTrace();}}
else{
           alert.setContentText("The email address " + email+" could not be found.");
       alert.showAndWait(); 
}
  }
}  

//else{
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//         alert.setTitle("Erreur");
//           alert.setContentText("The email address " + email+" could not be found.  ");
//            alert.showAndWait();
// 
//      }
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
 public boolean controlSaisie(String email){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
        if(isValidEmail(email) == false){

           alert.setContentText("The email " + email+ "is not a valid email, please try another");
            alert.showAndWait();
            return false;
             
        }
    
        return true;
    }
     public  boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
}
