/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import edu.esprit.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author amarz
 */
public class CardController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label desc;
    @FXML
    private Label loc;
     private int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      void SetData1(Evenement p) {
      
         
        
        //System.out.println((Session.getUser().getAvatar()));
//        File file = new File(a.getPath());
      //  Image image = new Image(file.toURI().toString());
      //  Attachement.setImage(image);
        id = p.getId();
        titre.setText(p.getTitre());
        // int cardid = (p.getId());
        //  ps.Selectplace(p.getId());
        //  System.out.println(cardid);
       loc.setText(p.getLocalisation());
        desc.setText(p.getDescription());
     
    }

    @FXML
    private void selectone(MouseEvent event) {
        AffichageEvController hc;
        EvenementService pse = new EvenementService();
        Evenement a = pse.Selectevent(id);
       
        System.out.println("ID" + id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEv.fxml"));
        try {
            a = pse.Selectevent(id);
            Parent root = loader.load();
            hc = loader.getController();
             a = pse.Selectevent(id);
            hc.setId(a.getId());
            hc.afficher();
            titre.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        
    }
    }}
