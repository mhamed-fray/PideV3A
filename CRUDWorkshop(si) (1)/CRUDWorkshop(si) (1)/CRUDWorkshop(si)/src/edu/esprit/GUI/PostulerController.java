/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import helpers.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import models.Offre;
import models.canditab;
import service.OffreService;
import service.canditabservice;

/**
 * FXML Controller class
 *
 * @author nidha
 */
public class PostulerController implements Initializable {

    @FXML
    private JFXTextField nomcol;
    
    @FXML
    private JFXTextField numcol;
    @FXML
    private JFXTextField prenomcol;
    @FXML
    private JFXTextField emailcol;
    @FXML
    private JFXTextArea messagecol;
    @FXML
    private JFXTextField emailFld1;
        Connection connection = null ;
        OffreService SP = new OffreService();
    private Offre p = SP.get_offre(FrontController.id_static);


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    



  
    @FXML
    private void postuler(MouseEvent event) {
        canditabservice es = new canditabservice();

        connection = DbConnect.getConnect();
        String nom = nomcol.getText();
         String prenom = prenomcol.getText();
       
        String email = emailcol.getText();
        String message = messagecol.getText();
        Integer num =Integer.parseInt( numcol.getText());
                // Double salaire = Double.valueOf(salaireflld.getText());


        //int id =p.getId();


        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()|| message.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
             canditab e=new canditab(nom,prenom,email,message,num);
             es.ajoutercandidature(e);
             
             clean();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("candidature enregistré");
            
            alert.showAndWait();
            
        }

    }

    @FXML
    private void clean() {
        nomcol.setText(null);
        prenomcol.setText(null);
        emailcol.setText(null);
        messagecol.setText(null);
        numcol.setText(null);

        
    }
   
/*
    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `Offre`( `titre`, `secteur`, `description`, `localisation`, `salaire`) VALUES (?,?,?,?,?)";

        }else{
            query = "UPDATE `Offre` SET "
                    + "`titre`=?,"
                    + "`secteur`=?,"
                    + "`description`=?,"
                      + "`localisation`=?,"
                    + "`salaire`= ? WHERE id = '"+offreid+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, titrefld.getText());
            preparedStatement.setString(2, secteurfld.getText());
            preparedStatement.setString(3, descriptionfld.getText());
            preparedStatement.setString(4, localisationfld.getText());
            preparedStatement.setString(5, salairefld.getText());

           //preparedStatement.setString(2, String.valueOf(salairefld));

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
*/
   
  

}
