/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Abonnement;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherAbonnementController implements Initializable {
     @FXML
    private TableView<Abonnement> AbonnementsTable;
    @FXML
    private TableColumn<Abonnement, String> idcol;
    @FXML
    private TableColumn<Abonnement, String> nomcol;
    @FXML
    private TableColumn<Abonnement, String> descriptioncol;
    @FXML
    private TableColumn<Abonnement, String> coutcol;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Abonnement abonnement = null ;
    ObservableList<Abonnement>  AbonnementList = FXCollections.observableArrayList();
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
           idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
        coutcol.setCellValueFactory(new PropertyValueFactory<>("cout"));
        
        AbonnementsTable.setItems(AbonnementList);
    }
    
      

    
    
}
