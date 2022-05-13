/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import java.io.IOException;
import models.Offre;
import service.OffreService;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Front_itemtController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label titrefld;
     @FXML
    private Label secteurfld;
    @FXML
    private Label descriptionfld;
    @FXML
    private Label localisationfld;
    @FXML
    private Label salairefld;
    @FXML
    private ImageView image;
OffreService SP = new OffreService();
    private Offre p = SP.get_offre(FrontController.id_static);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          System.out.println("jj");
           titrefld.setText(p.getTitre());
        secteurfld.setText(p.getSecteur());
        descriptionfld.setText(p.getDescription());
        localisationfld.setText(p.getLocalisation());
          salairefld.setText(String.valueOf(p.getSalaire()));
          
     
        
    }    

     @FXML
    private void postuler(MouseEvent event) {
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tableView/postuler.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
}
