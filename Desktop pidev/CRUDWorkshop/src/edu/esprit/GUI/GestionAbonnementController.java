/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Abonnement;
import edu.esprit.services.AbonnementService;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class GestionAbonnementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Abonnement> tableAbo;
  
    @FXML
    private TableColumn<Abonnement, String> NomCol;
    @FXML
    private TableColumn<Abonnement, String> DescriptionCol;
    @FXML
    private TableColumn<Abonnement, String> CoutCol;
    @FXML
    private TableColumn<Abonnement, String> TypeCol;
   
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtCout;
     private Abonnement c; 
            
            ObservableList<Abonnement>  list =  FXCollections.observableArrayList();
      int  index= -1; 
    @FXML
    private ComboBox<String> affecter;
                Connection cnx = MaConnexion.getInstance().getCnx();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         ObservableList<Abonnement>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT  nom, description, cout FROM abonnement");
            while(rs.next())
            {
                list.add(new Abonnement( rs.getString(1),rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }

       NomCol.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNom());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

    DescriptionCol.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getDescription());
        });
    CoutCol.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getCout());
            
        });
    
  
        // TODO
 tableAbo.setItems(list);
  tableAbo.refresh();
        // TODO
        // TODO   
        
        
    
}
    @FXML
    private void selectedl(MouseEvent event) {
        index=tableAbo.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        txtNom.setText(NomCol.getCellData(index).toString());
                txtDescription.setText(DescriptionCol.getCellData(index).toString());
                txtCout.setText(CoutCol.getCellData(index).toString());

    }
    public ObservableList<Abonnement> show1()
    { 
       

           try {
               ObservableList<Abonnement> obl =FXCollections.observableArrayList();
                             Connection cnx = MaConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select nom,description,cout from abonnement ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Abonnement ls = new Abonnement();
                
                 ls.setNom(rs.getString("nom"));

                 ls.setDescription(rs.getString("description"));
                 ls.setCout(rs.getString("cout"));
                 
               
             

                  
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
        
           
                      
      NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
      DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
      CoutCol.setCellValueFactory(new PropertyValueFactory<>("cout"));
      TypeCol.setCellValueFactory(new PropertyValueFactory<>("type_id"));
      ObservableList<Abonnement> obl =FXCollections.observableArrayList();
     obl=show1(); 
      tableAbo.setItems(obl);
      System.out.println(""+obl);

                      
    }
      @FXML
    private void supprimerloc(ActionEvent event) {
        AbonnementService abo = new AbonnementService();
        Abonnement ls = new Abonnement();
        ls = tableAbo.getSelectionModel().getSelectedItem();
                

        abo.SupprimerAbo(ls.getNom());
                        affiche();

    }
     @FXML
    private void modifierabonnement(ActionEvent event) {
        String nom=txtNom.getText();
        String description=txtDescription.getText();
        String cout=txtCout.getText();

        AbonnementService sp = new AbonnementService();
        Abonnement c = new Abonnement();
        c.setNom(nom);
        c.setDescription(description);
        c.setCout(cout);

        
        sp.modifier(c);
                affiche();

    }
     @FXML
    private void ajouterloc(ActionEvent event) {
        if ( controlSaisie()){
         
         AbonnementService ts = new AbonnementService();
         txtCout.textProperty().addListener(new ChangeListener<String>() {
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            txtCout.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }
});
       ts.ajouterAbonnement(new Abonnement(txtNom.getText(),txtDescription.getText(),txtCout.getText()));
                affiche();

       tableAbo.refresh();
       
    }}
    
     public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        if(checkIfStringContainsNumber(txtNom.getText())){
            alert.setContentText("Le nom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                if(checkIfStringContainsNumber(txtDescription.getText())){
            alert.setContentText("La description ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                 
        
                  if(checkIfStringContainsNumber2(txtCout.getText())){
            alert.setContentText("Le cout ne doit pas contenir des caracteres");
            alert.showAndWait();
            return false;
        }

        
        return true;
    }
       public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

       return true;
    }
    
    public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfStringContainsNumber2(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
                return true;
            }
        }
        return false;
    }
  
  

  
}
