/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Abonnement;
import edu.esprit.services.AbonnementService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterAbonnementController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtCout;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addAbonnement(ActionEvent event) {
          String nom =txtNom.getText();
        String description=txtDescription.getText();
        String cout=txtCout.getText();
        int coutint=Integer.parseInt(String.valueOf(cout).toString());
                
        Abonnement a=new Abonnement(nom, description,coutint);
        AbonnementService as=new AbonnementService();
        as.ajouterAbonnement(a);
        
        FXMLLoader loader =new FXMLLoader(getClass().getResource("AfficherAbonnement.fxml")) ;
        try {
            Parent root = loader.load(); //charger les acteurs (button,textfield...)
            AfficherAbonnementController ac =loader.getController();// kima jibna l fichier fxml njibou lcontrolleur mta3ha
          
            
            btn.getScene().setRoot(root); //chna5dhou l interface l9dima w nzideha ejdida 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
