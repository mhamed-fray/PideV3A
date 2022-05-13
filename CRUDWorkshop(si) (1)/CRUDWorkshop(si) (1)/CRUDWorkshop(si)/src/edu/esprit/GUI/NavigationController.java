/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author nidha
 */
public class NavigationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
     private void offre(MouseEvent event)
     {
         loadpage("tableView");
     }
     @FXML
     private void cand(MouseEvent event)
     {
         loadpage("tablecand");
     }

    private void loadpage(String page) {
        Parent root=null;
        try{
            root=FXMLLoader.load(getClass().getResource(page+".fxml"));
            
        }catch(IOException ex)
        {
              Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
     
}
