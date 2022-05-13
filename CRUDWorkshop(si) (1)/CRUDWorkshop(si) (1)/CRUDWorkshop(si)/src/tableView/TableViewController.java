/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableView;

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
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.annotations.Property;
import models.Offre;
import service.OffreService;
/**
 * FXML Controller class
 *
 * @author hocin
 */
public class TableViewController implements Initializable {
    
    OffreService es=new OffreService() ;

    @FXML
    private TableView<Offre> Offrestable;
    @FXML
    private TableColumn<Offre, String> idCol;
    @FXML
    private TableColumn<Offre, String> titrecol;
    @FXML
    private TableColumn<Offre, String> secteurcol;
    @FXML
    private TableColumn<Offre, String> descriptioncol;
    @FXML
    private TableColumn<Offre, String> localisationcol;
    @FXML
    private TableColumn<Offre, String> salairecol;
    @FXML
    private TableColumn<Offre, String> actioncol;
     @FXML
    private TextField titreflld;
      @FXML
    private TextField secteurflld;
       @FXML
    private TextField descriptionflld;
        @FXML
    private TextField localisationflld;
         @FXML
    private TextField salaireflld;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Offre Offre = null ;
    ObservableList<Offre>  Offrelist = FXCollections.observableArrayList();
   
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
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    

    @FXML
    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tableView/addOffre.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    @FXML
    private void refreshTable() {
          Offrelist.clear();
          Offrelist.addAll(es.afficherOffre());
          Offrestable.setItems(Offrelist);
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Loaded Succefully !");
            alert.showAndWait();
        
        
        
    }

    @FXML
    private void print(MouseEvent event) {
    }
    @FXML
    private void updatee(MouseEvent event) 
    {   
        
         int id=Offre.getId();
         String titre =titreflld.getText();
         String secteur =secteurflld.getText();
         String description=descriptionflld.getText();
         String localisation=localisationflld.getText();
         Double salaire = Double.valueOf(salaireflld.getText());
        // Double salaire=salaireflld.getText();
           Offre e=new Offre(titre,secteur,description,localisation,salaire);
           e.setId(id);
            es.modifierOffre(e);
            refreshTable();  
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Offre Modifié !");
            alert.showAndWait();
           //  clean();
    }
     void getSelected(String titre, String secteur, String description,String localisation,Double salaire) {

        
        titreflld.setText(titre);
        secteurflld.setText(secteur);
        descriptionflld.setText(description);
        localisationflld.setText(localisation);
        salaireflld.setText(String.valueOf(salaire));

    }
    

    private void loadDate() {
        
        connection = DbConnect.getConnect();
        refreshTable();
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titrecol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        secteurcol.setCellValueFactory(new PropertyValueFactory<>("secteur"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
        localisationcol.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        salairecol.setCellValueFactory(new PropertyValueFactory<>("salaire"));

        
        //add cell of button edit 
         Callback<TableColumn<Offre, String>, TableCell<Offre, String>> cellFoctory = (TableColumn<Offre, String> param) -> {
            // make cell containing buttons
            final TableCell<Offre, String> cell = new TableCell<Offre, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                                Offre = Offrestable.getSelectionModel().getSelectedItem();
                                /*query = "DELETE FROM `Offre` WHERE id  ="+Offre.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();*/
                                es.supprimerOffre(Offre.getId());
                                refreshTable();
                                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Deleted Succefully !");
            alert.showAndWait();
                            
                            
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                           Offre = Offrestable.getSelectionModel().getSelectedItem();
                             getSelected(Offre.getTitre(),Offre.getSecteur(),Offre.getDescription(),Offre.getLocalisation(),Offre.getSalaire());
                            
                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         actioncol.setCellFactory(cellFoctory);
         Offrestable.setItems(Offrelist);
         
         
    }
    
}
