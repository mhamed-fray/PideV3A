/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Abonnement;
import edu.esprit.services.AbonnementService;
import edu.esprit.tools.MaConnexion;
import java.awt.event.ItemEvent;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private TableColumn<Abonnement, String> TypeIDCol;
   
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtCout;
    @FXML
    private TextField typeIDtxt;
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
         try {
            fillcombo();
           
        } 
   catch (SQLException ex) {
        } 
        
        
        
         ObservableList<Abonnement>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT  nom, description, cout,type_id FROM abonnement");
            while(rs.next())
            {
                list.add(new Abonnement( rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4)));
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
    TypeIDCol.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getType_id());
            
        }
    );
    
  
        // TODO
 tableAbo.setItems(list);
  tableAbo.refresh();
        // TODO
        // TODO   
        
        
    
}
    ObservableList combo = FXCollections.observableArrayList();
     public void fillcombo() throws SQLException {
        PreparedStatement pst;
        String query = "SELECT type FROM `type`";
        pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            combo.add(rs.getString("type"));
            affecter.setItems(combo);
      
            
           
            

    }}
     @FXML
    private void retour(ActionEvent event) throws IOException 
    {
        
              Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionType.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
     //fct pour ajouter selected item comboboc dans le textfield
     @FXML
     private void comboSelected() throws SQLException{
         PreparedStatement pst;
     String query="SELECT id FROM `type` where type='"+affecter.getValue()+"'";
      pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            typeIDtxt.setText(rs.getString("id"));
        
        }
     }
    @FXML
    private void selectedl(MouseEvent event) {
        index=tableAbo.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        txtNom.setText(NomCol.getCellData(index).toString());
                txtDescription.setText(DescriptionCol.getCellData(index).toString());
                txtCout.setText(CoutCol.getCellData(index).toString());
                typeIDtxt.setText(TypeIDCol.getCellData(index).toString());

    }
    public ObservableList<Abonnement> show1()
    { 
       

           try {
               ObservableList<Abonnement> obl =FXCollections.observableArrayList();
                             Connection cnx = MaConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select nom,description,cout,type_id from abonnement ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Abonnement ls = new Abonnement();
                
                 ls.setNom(rs.getString("nom"));

                 ls.setDescription(rs.getString("description"));
                 ls.setCout(rs.getString("cout"));
                 ls.setType_id(rs.getString("type_id"));
                 
               
             

                  
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
      TypeIDCol.setCellValueFactory(new PropertyValueFactory<>("type_id"));
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
        String typeId=typeIDtxt.getText();

        AbonnementService sp = new AbonnementService();
        Abonnement c = new Abonnement();
        c.setNom(nom);
        c.setDescription(description);
        c.setCout(cout);
        c.setType_id(typeId);

        
        sp.modifier(c);
                affiche();

    }
     @FXML
    private void ajouterloc(ActionEvent event) throws SQLException {
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
       ts.ajouterAbonnement(new Abonnement(txtNom.getText(),txtDescription.getText(),txtCout.getText(),typeIDtxt.getText()));
       
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
