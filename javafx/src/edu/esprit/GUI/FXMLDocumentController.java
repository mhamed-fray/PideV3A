/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import java.sql.ResultSet;
import edu.esprit.entities.Evenement;
import Utils.MaConnexion;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author amarz
 */
public class FXMLDocumentController implements Initializable {
    
    
  @FXML
    private TextField ev_id;
    @FXML
    private TextField ev_nom;
    @FXML
    private TextField ev_loc;
    @FXML
    private DatePicker ev_dat;
    @FXML
    private TextArea ev_desc;
    @FXML
    private Button btnAjouter;
    @FXML
    private TableColumn<Evenement, Integer> colid;
    @FXML
    private TableColumn<Evenement, String> coltitre;
    @FXML
    private TableColumn<Evenement, String> coldesc;
    @FXML
    private TableColumn<Evenement, String> colloc;
    @FXML
    private TableColumn<Evenement, Integer> colloc1;
    @FXML
    private TableColumn<Evenement, Date> coldat;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TableView<Evenement> tvevent;
    @FXML
    private Label label;
    @FXML
    private Button next;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
     
        if(event.getSource()== btnAjouter){
            insertRecord();
        }else if (event.getSource()== btnupdate)
                {
                   modifrec(); 
                }else if (event.getSource()== btndelete){
                    deletbutt();
                }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showEvents();
    } 
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testandhire","root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error :"+ ex.getMessage());
            return null;
        }
    
}
    
    
    
    public ObservableList<Evenement> getEventList()
    {
        ObservableList<Evenement> evList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM evenement";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Evenement ev;
            while(rs.next()){
               
                
                ev = new Evenement(rs.getInt("id"),rs.getString("titre"),rs.getString("description"),rs.getString("localisation"),rs.getInt("nb_participants"),rs.getDate("dat"));
                evList.add(ev);
                
            }
        }catch (Exception ex ){
            ex.printStackTrace();
        }
        return evList;
        
    }
    
    
    public void showEvents(){
        ObservableList<Evenement> List =getEventList();
         colid.setCellValueFactory(new PropertyValueFactory<Evenement , Integer>("id"));
        coltitre.setCellValueFactory(new PropertyValueFactory<Evenement , String>("titre"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Evenement , String>("description"));
        
        colloc.setCellValueFactory(new PropertyValueFactory<Evenement , String>("localisation"));
        colloc1.setCellValueFactory(new PropertyValueFactory<Evenement , Integer>("nb_participants"));
       
        coldat.setCellValueFactory(new PropertyValueFactory<Evenement , Date>("dat"));
    tvevent.setItems(List);
    
    }
    private void insertRecord(){
        
        String nom=ev_nom.getText();
        String loc=ev_loc.getText();
        String desc=ev_desc.getText();
        
        if(!nom.equals("") && !loc.equals("") && !desc.equals("") && !ev_dat.getValue().equals(null) ){
        java.util.Date date =java.util.Date.from(ev_dat.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date sqlDate=new Date(date.getTime());
        String query = "INSERT INTO evenement (titre , description , localisation,dat )  VALUES('" + ev_nom.getText() + "','" + ev_desc.getText() + "','" +  ev_loc.getText() + "','" + sqlDate + "' )";
    executeQuery(query);
     ev_nom.setText("");
     ev_loc.setText("");
     ev_desc.setText("");
     ev_dat.setValue(null);
    showEvents();
    Alert alert = new Alert(AlertType.CONFIRMATION,"Evenement ajouter avec succes!",javafx.scene.control.ButtonType.OK);
        alert.showAndWait();
    }
        else {
        Alert alert = new Alert(AlertType.WARNING,"veuillez remplir tous les champs!",javafx.scene.control.ButtonType.OK);
        alert.showAndWait();
        }
        
    }
    
    private void modifrec(){
        String nom=ev_nom.getText();
        String loc=ev_loc.getText();
        String desc=ev_desc.getText();
    
        if(!nom.equals("") && !loc.equals("") && !desc.equals("") && !ev_dat.getValue().equals(null) ){
        
        String query =" UPDATE evenement SET  description = '" + ev_desc.getText() + "', localisation = '" +  ev_loc.getText() + "' , dat = '" +  ev_dat.getValue() + "' WHERE id='" + ev_id.getText() + "' "; 
        executeQuery(query);
        Alert alert = new Alert(AlertType.CONFIRMATION,"Evenement modifier avec succes!",javafx.scene.control.ButtonType.OK);
        alert.showAndWait();
        showEvents();
    }
         else {
        Alert alert = new Alert(AlertType.WARNING,"veuillez remplir tous les champs!",javafx.scene.control.ButtonType.OK);
        alert.showAndWait();
        }
        
    }
    private void deletbutt(){
        String query ="DELETE FROM evenement WHERE id ='" + ev_id.getText() + "' ";
        executeQuery(query);
        Alert alert = new Alert(AlertType.CONFIRMATION,"Evenement supprimer avec succes!",javafx.scene.control.ButtonType.OK);
        alert.showAndWait();
        showEvents();
    }
    

    private  void executeQuery(String query) {
      Connection   conn = getConnection();
      Statement st;
      try{
          st=conn.createStatement();
          
          st.executeUpdate(query);
      }catch(Exception ex){
          ex.printStackTrace();
      }
    }
   /* private void nbparticipant(){
        String query="SELECT nb_participants FROM"
    }*/
    
}
