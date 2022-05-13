/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpers.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.annotations.Property;
import models.canditab;
import service.canditabservice;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class TablecandController implements Initializable {
    canditabservice es =new canditabservice();

    @FXML
    private TableView<canditab> canditable;
    @FXML
    private TableColumn<canditab, String> idColl;
    @FXML
    private TableColumn<canditab, String> nomcol;
    @FXML
    private TableColumn<canditab, String> prenomcol;
    @FXML
    private TableColumn<canditab, String> emailcol;
    @FXML
    private TableColumn<canditab, String> messagecol;
     @FXML
    private TableColumn<canditab, String> numcol;
  
    @FXML
    private TableColumn<canditab, String> actioncol;
     @FXML
    private TextField nomflld;
      @FXML
    private TextField prenomflld;
       @FXML
    private TextField emailflld;
        @FXML
    private TextArea messageflld;
        @FXML
    private TextField numfllld;
        
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    canditab canditab = null ;
    ObservableList<canditab>  candilist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> idcoll;
   
// display root in UI

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }    
    
    @FXML
      private void send(MouseEvent event) {
        sms();
    }

    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tableView/addStudent.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TablecandController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void refreshTable() {
      candilist.clear();
          candilist.addAll(es.affichercandidatures());
          canditable.setItems(candilist);
        
        
        
    }

    @FXML
    private void print(MouseEvent event) {
    }
     void getSelected(String nom, String prenom, String email,String message,Integer num) {

        
        nomflld.setText(nom);
        prenomflld.setText(prenom);
        emailflld.setText(email);
        messageflld.setText(message);
        numfllld.setText(String.valueOf(num));

       

    }
    void sms()
    {
         canditab = canditable.getSelectionModel().getSelectedItem();
     //  int canditab.getnom();
       // int num=27765995;
     int num =getnum(canditab);
        
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("nidhal.zoukeri@esprit.tn");
        defaultClient.setPassword("BF7D891A-1DDE-9DCF-A892-3D477C85F26E");
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("votre candidature est aceeptée ! nous allons vous envoyer la date de votre entretien bientot " );
        smsMessage.to("+216"+num);
        smsMessage.source("reservation");
        

        List<SmsMessage> smsMessageList = Arrays.asList(smsMessage);
        // SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = apiInstance.smsSendPost(smsMessages);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SmsApi#smsSendPost");
            e.printStackTrace();
        }
    }

    private void loadDate() {
        
        connection = DbConnect.getConnect();
        refreshTable();
        
     //  idColl.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        messagecol.setCellValueFactory(new PropertyValueFactory<>("message"));
        numcol.setCellValueFactory(new PropertyValueFactory<>("num"));


        
        //add cell of button edit 
         Callback<TableColumn<canditab, String>, TableCell<canditab, String>> cellFoctory = (TableColumn<canditab, String> param) -> {
            // make cell containing buttons
            final TableCell<canditab, String> cell = new TableCell<canditab, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView viewIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        viewIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             canditab = canditable.getSelectionModel().getSelectedItem();
                                /*query = "DELETE FROM `Offre` WHERE id  ="+Offre.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();*/
                                es.supprimercandidature(canditab.getId());
                                refreshTable();
                            
                           

                          

                        });
                        viewIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            canditab = canditable.getSelectionModel().getSelectedItem();
                           getSelected(canditab.getNom(),canditab.getPrenom(),canditab.getEmail(),canditab.getMessage(),canditab.getNum());

                            

                           

                        });

                        HBox managebtn = new HBox(viewIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(viewIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         actioncol.setCellFactory(cellFoctory);
         canditable.setItems(candilist);
         
         
    }

    private int getnum(canditab c) {
        
        
     return c.getNum();
      

      
    }
    
    
  
    
}
