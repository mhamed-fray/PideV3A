/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Abonnement;
import edu.esprit.services.AbonnementService;
import edu.esprit.test.FxMain;
import edu.esprit.test.MyListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ItemController implements Initializable {
    private AbonnementService fs= new AbonnementService();
    
    @FXML
    private Label NomLabel;
     @FXML
    private Label PrixLabel;
      @FXML
    private ImageView img;
      @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(abonnement);
    }
      
      private Abonnement abonnement;
    private MyListener myListener;

    /**
     * Initializes the controller class.
     * 
     */
        public void setData(Abonnement abonnement,MyListener myListener) {
        this.abonnement = abonnement;
        this.myListener = myListener;
        NomLabel.setText(abonnement.getNom());
        PrixLabel.setText(FxMain.CURRENCY + abonnement.getCout());
       // Image image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
       // img.setImage(image);
    }
         public String getData() {
        return PrixLabel.getText();
    }
       
         
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
    
}
