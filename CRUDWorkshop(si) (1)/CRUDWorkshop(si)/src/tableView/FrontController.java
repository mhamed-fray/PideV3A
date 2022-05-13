/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableView;

import models.Offre;
import service.OffreService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FrontController implements Initializable {

    @FXML
    private Pane pnl_salle;
    @FXML
    private ScrollPane scrollpane_Salle;
    @FXML
    private VBox vbox_salle;
    static int id_static=0,id;
    
    private int taille_produit=0;
    private OffreService SP = new OffreService();
    private int indiceProduit=0;
    @FXML
    private TextField searchfld;
    @FXML
    private AnchorPane anchor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             List<Offre> ls = SP.afficherOffre();
         taille_produit=ls.size();
    Node[] nodes_offre = new Node[taille_produit];
        scrollpane_Salle.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
         try {
                
        for (indiceProduit = 0; indiceProduit < taille_produit; indiceProduit++) {
            

                System.out.println("ss");
id_static=ls.get(indiceProduit).getId();
                nodes_offre[indiceProduit] = FXMLLoader.load(getClass().getResource("/tableView/front_item.fxml"));
  vbox_salle.getChildren().add(nodes_offre[indiceProduit]);
               
          
        }
          } catch (IOException e) {

            }
         
              searchfld.textProperty().addListener((observable, oldValue, newValue) -> {
           //System.out.println("textfield changed from " + oldValue + " to " + newValue);
            
             vbox_salle.getChildren().clear();
           // anchor.getChildren().clear();
                ls.clear();
            String nom=newValue;
           ls.addAll(SP.Search(newValue));
        //  System.out.println(ls);
          try {
                
        for (indiceProduit = 0; indiceProduit < taille_produit; indiceProduit++) {
            

                System.out.println("ss");
id_static=ls.get(indiceProduit).getId();
                nodes_offre[indiceProduit] = FXMLLoader.load(getClass().getResource("/tableView/front_item.fxml"));
  vbox_salle.getChildren().add(nodes_offre[indiceProduit]);
               
          
        }
          } catch (IOException e) {

            }
         });
              
             
                     
}

    @FXML
    private void RechercheArticle(KeyEvent event) {
    }
}