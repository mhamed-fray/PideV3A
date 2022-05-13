/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import edu.esprit.services.EvenementService;
//import javafxapplication1.FXMLDocumentController;
/**
 * FXML Controller class
 *
 * @author amarz
 */
public class FXMLAffichageController implements Initializable {
private List<Evenement> events;
    /**
     * Initializes the controller class.
     */
    EvenementService ps = new EvenementService();
    
       
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private HBox cardlayout;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        events = new ArrayList<>(ps.afficherEvenement());
    int column = 0;
    int row = 1;
        try {
            for (Evenement p : events) {
              
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("card.fxml"));
               cardlayout = loader.load();
                CardController eventHomeController = loader.getController();
                eventHomeController.SetData1(p);
                if (column == 5) {
                    column = 0;
                    ++row;
                }
                grid.add(cardlayout, column++, row);
                GridPane.setMargin(cardlayout, new Insets(10));
          
                }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        // TODO
    }    
    
}
