/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;


import edu.esprit.entities.Sujet;
import edu.esprit.entities.Test;
import edu.esprit.services.SujetService;
import edu.esprit.services.TestService;
import edu.esprit.tools.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Gestionnaire_SujetController implements Initializable {

    @FXML
    private TableView<?> tables;
    @FXML
    private TableColumn<Sujet, String> sujet_col;
    
     Connection cnx = MaConnexion.getInstance().getCnx();
     
      public PreparedStatement st;
  
      public ResultSet result;
      int index=0;
         
  ObservableList<Sujet>  list1 =  FXCollections.observableArrayList();
    @FXML
    private TextField txt_rech;
    @FXML
    private Button btn_ajouter1;
    @FXML
    private Button btn_supprimer1;
    @FXML
    private Button btn_modifer1;
    @FXML
    private TextField txt_sujet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  
                    affiche1();  
        try{
                       
         affiche1();  
            ResultSet rs=cnx.createStatement().executeQuery("select * from sujet");
           
            while(rs.next())
            {
                list1.add(new Sujet(rs.getString("sujet") ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestionnaire_SujetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        sujet_col.setCellValueFactory(new PropertyValueFactory<>("sujet"));
       
      tables.setItems((ObservableList) list1);
       
                        affiche1();  
                        search_user();
        
            
      
     


        
        }
    
    
/////////////////////////AJOUTER_TEST//////////////////////////////////
    @FXML
    private void Ajouter_Sujet(ActionEvent event) {
        
        String sujet=txt_sujet.getText();
        
        
       
        
      if(!sujet.equals(""))
      {
          
          Sujet p=new Sujet(sujet);
          SujetService ps =new SujetService();
          ps.ajouterSujet(p);
          affiche1();
 
//        Notifications notificationBuilder = Notifications.create()
//                                                     .title("Sujet  ajouté")
//                                                     .text("Sujet  :\n Titre ="+txt_sujet.getText()+"\n")
//                                                     .graphic(null)
//                                                     .hideAfter(javafx.util.Duration.seconds(5) )
//                                                      .position(Pos.BOTTOM_RIGHT) ;
//        
//          notificationBuilder.darkStyle();
//         notificationBuilder.showConfirm();
//         affiche1();  
    }}
    /////////////////////////////////////////////////////////////////////
    
    
    
    /////////////////////SUPPRIMER_TEST////////////////////////////////

    @FXML
    private void Supprimer_Sujet (ActionEvent event) {
        
        SujetService sujet = new SujetService();
        Sujet  t = new Sujet ();
        t= (Sujet ) tables.getSelectionModel().getSelectedItem();
        sujet.supprimerSujet (t.getSujet());
        affiche1();  
        Alert alert =new Alert(Alert.AlertType.WARNING,"le sujet supprime de titre ="+txt_sujet.getText()+"");
        alert.showAndWait();
        
                
            
        
    }
    ///////////////////////////////////////////////////////////
    
    
    /////////////////MODIFIER_TEST///////////////////////////

    @FXML
    private void Modifier_Sujet (ActionEvent event) {
            
        String sujet=txt_sujet.getText();
        

        SujetService sp = new SujetService();
        Sujet c = new  Sujet();
        c.setSujet(sujet);
        

        
        sp.modifier(c);
                affiche1();
                Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"done");
        alert.showAndWait();
   }
    
    //////////////////////////////////////////////////////
    
    
    
    /////////////////////////AFFICHER_TEST/////////////////////
   
 public void affiche1() {
        
           
                         
      sujet_col.setCellValueFactory(new PropertyValueFactory<>("sujet"));
     
     
      ObservableList<Sujet> list =FXCollections.observableArrayList();
     list1=show1(); 
      tables.setItems((ObservableList)list);
      System.out.println(""+list);

                      
    }

    
 
 public ObservableList< Sujet> show1()
    { 
       

           try {
               ObservableList< Sujet> obl =FXCollections.observableArrayList();
                             
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select sujet from sujet ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                  Sujet ls = new  Sujet();
                    
                     ls.setSujet(rs.getString("sujet"));
                     
                 
               
             

                  
                  System.out.println("");
         obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    } 
 ///////////////////////////////////////////////////////////////
 
 
 
 //////////////////////SELECTION///////////////////////////////
    @FXML
    private void selectedl(javafx.scene.input.MouseEvent event) throws SQLException {
        index=tables.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        
                 
             
                txt_sujet.setText(sujet_col.getCellData(index).toString());
                 
                
    
    }
    
     ///////////////////////////////////////////////////////////////
    
    
    
   void search_user() {          
       
         sujet_col.setCellValueFactory(new PropertyValueFactory<>("sujet"));
       
     
       
        SujetService ser=new SujetService();
       
        List< Sujet> li =ser.ListClasse();
        ObservableList< Sujet> data = FXCollections.observableArrayList(li);
        tables.setItems((ObservableList)data);

        FilteredList< Sujet> filteredData = new FilteredList<>(data, b -> true);  
 txt_rech.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(test -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
   
    if (test.getSujet().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
      return true; // Filter matches username
    } 
        else  
          return false; // Does not match.
   });
  });  
  SortedList< Sujet> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind((ObservableValue)tables.comparatorProperty());  
  tables.setItems((SortedList)sortedData);      
    }

   
   
   
   
   
   
    
}
