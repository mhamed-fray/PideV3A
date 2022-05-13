/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import edu.esprit.test.FxMain;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PaiementController implements Initializable {

     @FXML
    private AnchorPane anac;

    @FXML
    private Label prix;

    @FXML
    private TextField nom;

    @FXML
    private Label nn;

    @FXML
    private TextField mail;

    @FXML
    private Label mm;

    @FXML
    private TextField carte;

    @FXML
    private Label cc;

    @FXML
    private TextField sec;

    @FXML
    private Label cv;

    @FXML
    private Button payerBtn;
        @FXML
    private ComboBox<String> mois;

    @FXML
    private ComboBox<String> annee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> list  = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
        mois.setItems(list);
          ObservableList <String> list2  = FXCollections.observableArrayList("2021","2022","2023","2024","2025","2026","2027");
        annee.setItems(list2);
         try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../GUI/FrontAbonnement.fxml"));
            
            AnchorPane anchorPane = fxmlLoader.load();
            FrontAbonnementController itemController = fxmlLoader.getController();
            prix.setText("66");
            
            
        } catch (IOException ex) {
            Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
       /* try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/GUI/FrontAbonnement.fxml"));
            
            AnchorPane anchorPane = fxmlLoader.load();
            ItemController itemController = fxmlLoader.getController();
            prix.setText(itemController.getData());
            
            
        } catch (IOException ex) {
            Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        // TODO
                    
       /* try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/GUI/FrontAbonnement.fxml"));
            
            AnchorPane anchorPane = fxmlLoader.load();
            ItemController itemController = fxmlLoader.getController();
            prix.setText(itemController.getData());
            
            
        } catch (IOException ex) {
            Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        // TODO
        
    }    
      @FXML
    private void payer(ActionEvent event) throws SQLException, StripeException, InterruptedException, IOException {
        int k=0 ;
        cc.setText("");
        cv.setText("");
         mm.setText("");
        nn.setText("");
       
        
          
            
        String c=carte.getText();
        String s=sec.getText();
        String m=mail.getText();
        String n=nom.getText();
        if(m.length()==0)
        {
        k=1;
         mm.setText("Champs vide ");
        }
        if(n.length()==0)
        {
        k=1;
         nn.setText("Champs vide ");
        }
          if(c.length()==0)
        {
        k=1;
         cc.setText("Champs vide ");
        }
        if(s.length()==0)
        {
        k=1;
         cv.setText("Champs vide ");
        }
        
        if(!c.matches("[+-]?\\d*(\\.\\d+)?"))
        {
        k=1;
        carte.setStyle("-fx-text-fill: red; ");
        cc.setText("Chiffres Uniquement");
        }
        else
        
        if(!s.matches("[+-]?\\d*(\\.\\d+)?"))
        {
        k=1;
        sec.setStyle("-fx-text-fill: red; ");
        cv.setText("Chiffres Uniquement");
        }
        else
        {
        if(s.length()!=3)
        {  
            k=1;
            sec.setStyle("-fx-text-fill: red; ");
        cv.setText("3 Chiffres "); } 
        
        }
        if(k==0)
        {
            try {
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  https://www.bulksms.com/developer/eapi/submission/character-encoding/
             */
            data += "username=" + URLEncoder.encode("ons21", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("Onsons123", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode("votre achat de "+prix.getText()+"tnd a ete effectue", "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=21693785026";
            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
              Stripe.apiKey ="sk_test_51KYFffJhbqELi4FTGEfUDtM3CMIOpLovdOV738NQrcU6LhBrZO8FnlKardhECxipf5x3UL9ukE0DOfbosA0rOlvt00nWbGeTDF"; // add your api key

                Customer a=Customer.retrieve("cus_LaVGZEl1FB1hzc");

                 
                  
                  
                
		
                
	Map<String, Object> params = new HashMap<String,Object>();
		params.put("amount", "66");
		params.put("currency", "usd");
                params.put("description", "achat d'un abonnement");
                params.put("customer",  a.getId());
		
                
                Charge.create(params);
        

//             Properties prop = System.getProperties();
//prop.put("mail.smtp.port", "587");
//    prop.put("mail.smtp.auth", true);
//prop.put("mail.smtp.starttls.enable", "true");
//        Session newSession = Session.getDefaultInstance(prop, null);
//
// String emailsubject="Notification paiement";
//  String emailbody="Recu de Transaction ,Cher Khalil , Merci de votre achat chez ILEARN ";
//Message message = new MimeMessage(newSession);
//message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getText()));
//
//message.setSubject(emailsubject);
//
//
//
//
//MimeBodyPart mimeBodyPart = new MimeBodyPart();
//mimeBodyPart.setContent(emailbody, "text/html");
//
//Multipart multipart = new MimeMultipart();
//multipart.addBodyPart(mimeBodyPart);
//
//
//message.setContent(multipart);
//
//String fromuser ="khalil.m.supra@gmail.com";
//String pass ="I LOVE MY MOTHER";
//String emailhost="smtp.gmail.com";
//Transport transport =newSession.getTransport("smtp");
//transport.connect(emailhost,fromuser,pass);
//transport.sendMessage( message, message.getAllRecipients());
//transport.close();
//System.out.println("yessssss");
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/FrontAbonnement.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();}
        
    }
    
}
