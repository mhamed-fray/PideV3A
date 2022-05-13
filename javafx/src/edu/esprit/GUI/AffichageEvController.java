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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import edu.esprit.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author amarz
 */
public class AffichageEvController implements Initializable {

    @FXML
    private Label tit;
    @FXML
    private Label desc;
    @FXML
    private Label loca;
    @FXML
    private Label nbp;
    @FXML
    private Label datt;
    
    /**
     * Initializes the controller class.
     *
     */
    private int id;
  EvenementService ps = new EvenementService();

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
int ida;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void afficher() {
       EvenementService as = new EvenementService();
        
        tit.setText(as.Selectevent(this.getId()).getTitre());
        desc.setText(as.Selectevent(this.getId()).getDescription());
        loca.setText(as.Selectevent(this.getId()).getLocalisation());
       // datt.setText(as.Selectevent(this.getId()).getDate().);
        
        
     
        
        
        
        
    }
  
   
 


    @FXML
    private void goToRes(MouseEvent event) {
      ReservationController hc;
        EvenementService pse = new EvenementService();
        Evenement a = pse.Selectevent(id);
       
        System.out.println("ID" + id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reservation.fxml"));
        try {
            
            Parent root = loader.load();
            hc = loader.getController();
             
            hc.setId(a.getId());
            
            tit.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        
    }
    }
    }    
 

