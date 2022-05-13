/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;
import edu.esprit.GUI.MyQr;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Reservation;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import edu.esprit.services.ReservationService;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import edu.esprit.services.EvenementService;


/**
 * FXML Controller class
 *
 * @author amarz
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField nbp;
    
private int id;
    @FXML
    private ImageView qr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     public Connection getConnection(){
        Connection conn;
        try{
            conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testandhire","root","");
            return conn;
        }catch(SQLException ex){
            System.out.println("Error :"+ ex.getMessage());
            return null;
        }
    
}

   /* @FXML
    private void reserverev(ActionEvent event) {
        
        
        EvenementService pse = new EvenementService();
        Evenement a = pse.Selectevent(id);
       
        System.out.println("ID" + id);
        try {
       
           
             
            hc.setId(a.getId());
            hc.afficher();
            titre.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        
    }
    }*/

    @FXML
    private void res(ActionEvent event) throws WriterException, IOException, SQLException {
        int b = Integer.valueOf(nbp.getText());
    
        ReservationService r = new ReservationService();
        Reservation res = new Reservation ();
        r.ajouterResrvation(b,id);
         r.increment(b,id);
        // The data that the QR code will contain
        String data =nbp.getText();
  
        // The path where the image will get saved
        String path = "qrcode.png";
 
        // Encoding charset
        String charset = "UTF-8";
 
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
            = new HashMap<EncodeHintType,
                          ErrorCorrectionLevel>();
 
        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);
 
        // Create the QR code and save
        // in the specified folder
        // as a jpg file
        
        MyQr.createQR(data, path, charset, hashMap, 300, 300);
       
       
     //  System.out.println( as.ajouterAttachement(a1));
        System.out.println("QR Code Generated!!! ");
         File file2 = new File(path);
                Image image2 = new Image(file2.toURI().toString());
        qr.setImage(image2);
    }
}