/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Abonnement;
import edu.esprit.entities.type;
import edu.esprit.services.AbonnementService;
import edu.esprit.services.TypeService;
import edu.esprit.tools.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionTypeController implements Initializable {
     @FXML
    private TableView<type> TableType;
  
    @FXML
    private TableColumn<type, String> TypeCol;
     @FXML
    private TextField TypeTxt;
     
      private type c; 
            
            ObservableList<type>  list =  FXCollections.observableArrayList();
      int  index= -1; 
    @FXML
    private ComboBox<String> affecter;
                Connection cnx = MaConnexion.getInstance().getCnx();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<type>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT  type FROM type");
            while(rs.next())
            {
                list.add(new type( rs.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionTypeController.class.getName()).log(Level.SEVERE, null, ex);
        }

       TypeCol.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getType());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

    
    
  
        // TODO
 TableType.setItems(list);
  TableType.refresh();
        // TODO
        // TODO   
        // TODO
    }

@FXML
    private void selectedl(MouseEvent event) {
        index=TableType.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        TypeTxt.setText(TypeCol.getCellData(index).toString());
               

    }
    public ObservableList<type> show1()
    { 
       

           try {
               ObservableList<type> obl =FXCollections.observableArrayList();
                             Connection cnx = MaConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select type from type ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 type ls = new type();
                
                 ls.setType(rs.getString("type"));

                 
                 
               
             

                  
                  System.out.println("");
         obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    }
    
     public void affiche() {
        
           
                      
      TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
      
      ObservableList<type> obl =FXCollections.observableArrayList();
     obl=show1(); 
      TableType.setItems(obl);
      System.out.println(""+obl);

                      
    }
      @FXML
    private void modifiertype(ActionEvent event) {
        String type=TypeTxt.getText();
        

        AbonnementService sp = new AbonnementService();
        Abonnement c = new Abonnement();
        c.setNom(nom);
        c.setDescription(description);
        c.setCout(cout);

        
        sp.modifier(c);
                affiche();

    }
     
    
}
