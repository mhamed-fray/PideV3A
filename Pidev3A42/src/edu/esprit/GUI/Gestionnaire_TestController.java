/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;
import com.itextpdf.text.Image;
import com.itextpdf.text.Jpeg;
import java.time.LocalDateTime;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.year;
import edu.esprit.entities.Test;
import edu.esprit.services.TestService;
import edu.esprit.tools.MaConnexion;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import static java.time.Instant.from;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.activation.DataSource;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
  
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Gestionnaire_TestController implements Initializable {

    @FXML
    private TableColumn<Test, String> tab_col1;
    @FXML
    private TableColumn<Test, String> tab_col2;
    @FXML
    private TableColumn<Test, String> tab_col3;
    @FXML
    private JFXTextField txt_search;
   
    @FXML
    private JFXTextField txt_titre;
    @FXML
    private JFXButton btn_ajouter;
    Connection cnx = MaConnexion.getInstance().getCnx();
     
      public PreparedStatement st;
  
      public ResultSet result;
         
  ObservableList<Test>  list =  FXCollections.observableArrayList();
      private List<Test> formations = new ArrayList<>();
    @FXML
    private TableView<?> table;
      
    int index=-1;
    @FXML
    private JFXButton btn_supprimer;
    @FXML
    private JFXButton bttn_modifier;
    @FXML
    private JFXTextField txt_duree;
    @FXML
    private JFXTextField txt_nbrp;
    @FXML
    private Pagination pagination;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  
                    
        try{
                       
         
            ResultSet rs=cnx.createStatement().executeQuery("select * from test ");
           
            while(rs.next())
            {
                list.add(new Test(rs.getString("duree"),rs.getString("nbrp"),rs.getString("titre")) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestionnaire_TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        tab_col1.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tab_col2.setCellValueFactory(new PropertyValueFactory<>("nbrp"));
        tab_col3.setCellValueFactory(new PropertyValueFactory<>("titre"));
       // table.setItems((ObservableList) list);
       
                    affiche1();  
         search_user();
        
            
      
     


        
        }
    
    
/////////////////////////AJOUTER_TEST//////////////////////////////////
    @FXML
    private void Ajouter_Test(ActionEvent event) {
        
        String titre=txt_titre.getText();
        String duree=txt_duree.getText();
        String nbrp=txt_nbrp.getText();
        
       
        
      if(!duree.equals("")&&!duree.equals("") &&!duree.equals(""))
      {
          
          Test p=new Test(duree,nbrp,titre);
          TestService ps=new TestService();
          ps.ajouterTest(p);
          affiche1();
 
        Notifications notificationBuilder = Notifications.create()
                                                     .title("Test  ajouté")
                                                     .text("Test :\n Titre ="+txt_titre.getText()+"\n Dure ="+txt_duree.getText()+"")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.BOTTOM_RIGHT) ;
        
          notificationBuilder.darkStyle();
         notificationBuilder.showConfirm();
    }}
    /////////////////////////////////////////////////////////////////////
    
    
    
    /////////////////////SUPPRIMER_TEST////////////////////////////////

    @FXML
    private void Supprimer_Test(ActionEvent event) {
        
        TestService test = new TestService();
        Test t = new Test();
        t= (Test) table.getSelectionModel().getSelectedItem();
        test.supprimerTest(t.getTitre());
        affiche1();  
        Alert alert =new Alert(AlertType.WARNING,"le sujet supprime de titre ="+txt_titre.getText()+"");
        alert.showAndWait();
        
                
            
        
    }
    ///////////////////////////////////////////////////////////
    
    
    /////////////////MODIFIER_TEST///////////////////////////

    @FXML
    private void Modifier_Test(ActionEvent event) {
            
        String duree=txt_duree.getText();
        String nbrp=txt_nbrp.getText();
        String titre=txt_titre.getText();

        TestService sp = new TestService();
        Test c = new Test();
        c.setDuree(duree);
        c.setNbrp(nbrp);
        c.setTitre(titre);

        
        sp.modifier(c);
                affiche1();
                Alert alert =new Alert(AlertType.CONFIRMATION,"done");
        alert.showAndWait();
   }
    
    //////////////////////////////////////////////////////
    
    
    
    /////////////////////////AFFICHER_TEST/////////////////////
   
 public void affiche1() {
        
           
                         
      tab_col1.setCellValueFactory(new PropertyValueFactory<>("duree"));
     tab_col2.setCellValueFactory(new PropertyValueFactory<>("nbrp"));
      tab_col3.setCellValueFactory(new PropertyValueFactory<>("titre"));
     
      ObservableList<Test> list =FXCollections.observableArrayList();
     list=show1(); 
      table.setItems((ObservableList)list);
      System.out.println(""+list);

                      
    }

    
 
 public ObservableList<Test> show1()
    { 
       

           try {
               ObservableList<Test> obl =FXCollections.observableArrayList();
                             
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select duree,nbrp,titre from test ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Test ls = new Test();
                    
                     ls.setDuree(rs.getString("duree"));
                     ls.setNbrp(rs.getString("titre"));
                     ls.setTitre(rs.getString("titre"));
                 
               
             

                  
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
        index=table.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        
                 
             
                txt_titre.setText(tab_col3.getCellData(index).toString());
                 txt_duree.setText(tab_col1.getCellData(index).toString());
                  txt_nbrp.setText(tab_col2.getCellData(index).toString());
                
    
    }
    
     ///////////////////////////////////////////////////////////////
    
    
    
   void search_user() {          
       
         tab_col1.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tab_col2.setCellValueFactory(new PropertyValueFactory<>("nbrp"));
        tab_col3.setCellValueFactory(new PropertyValueFactory<>("titre"));
        
     
       
        TestService ser=new TestService();
       
        List<Test> li =ser.ListClasse();
        ObservableList<Test> data = FXCollections.observableArrayList(li);
        table.setItems((ObservableList)data);

        FilteredList<Test> filteredData = new FilteredList<>(data, b -> true);  
 txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(test -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
   
    if (test.getDuree().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
      return true; // Filter matches username
    } else if (test.getNbrp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
      return true; // Filter matches password
    } else if (String.valueOf(test.getTitre()).indexOf(lowerCaseFilter)!=-1) {
         return true;// Filter matches email
    } // Filter matches password
        else  
          return false; // Does not match.
   });
  });  
  SortedList<Test> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind((ObservableValue)table.comparatorProperty());  
  table.setItems((SortedList)sortedData);      
    }

   
   
   
   
   
    @FXML
    private void serach1(javafx.scene.input.MouseEvent event) {
        String sql ="select  duree,nbrp,titre from test where titre ='"+txt_search.getText()+"'";
        int m=0;
        try{
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            if(result.next())
            {
                txt_duree.setText(result.getString("duree"));
                txt_nbrp.setText(result.getString("nbrp"));
                txt_titre.setText(result.getString("titre"));
                m=1;
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        if(m==0){
            Alert alert = new Alert(AlertType.ERROR, "aucun test trouvé");
            alert.showAndWait();
        }
    }
 
}


    
