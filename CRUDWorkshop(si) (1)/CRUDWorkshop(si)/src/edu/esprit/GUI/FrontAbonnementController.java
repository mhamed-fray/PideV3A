/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Abonnement;
import edu.esprit.entities.User;
import edu.esprit.services.AbonnementService;
import edu.esprit.services.PaymentService;
import edu.esprit.test.FxMain;
import edu.esprit.test.MyListener;
import edu.esprit.tools.MaConnexion;
import javafx.geometry.Insets;
import java.io.IOException;
import java.math.BigDecimal;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FrontAbonnementController implements Initializable {
    private AbonnementService fs= new AbonnementService();
    private String ch="c";
    private String state ="e";
       @FXML
    private VBox ChosenAboCard;

    @FXML
    private Label TypeAboLabel;

    @FXML
    private Label NomAboLabel;

    @FXML
    private Label PrixAboLabel;

    @FXML
    private ImageView AboImage;
      @FXML
    private ScrollPane scroll;
    @FXML
    private TextArea descriptionarea;
    @FXML
    private TextField Typetxt;
    @FXML
    private ComboBox<String> triercombo;

    @FXML
    private GridPane grid;
        @FXML
    private HBox mypane;
            @FXML
    private Button fh;

    @FXML
    private Button fb;
    @FXML
    private TextField search;
        private List<Abonnement> abonnements = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    Connection cnx = MaConnexion.getInstance().getCnx();

     private List<Abonnement> getData() throws SQLException {
        List<Abonnement> abonnements = new ArrayList<>();
        Connection cnx = MaConnexion.getInstance().getCnx();
        ResultSet rs = cnx.createStatement().executeQuery("SELECT  nom, description, cout,type_id FROM abonnement");
         while(rs.next())
            {
                abonnements.add(new Abonnement( rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4)));
            }
        

        return abonnements;
    }
     private void setChosenAbo(Abonnement abonnement) throws SQLException {
        NomAboLabel.setText(abonnement.getNom());
        PrixAboLabel.setText(FxMain.CURRENCY + abonnement.getCout());
        descriptionarea.setText(abonnement.getDescription());
        PreparedStatement pst;
        Connection cnx = MaConnexion.getInstance().getCnx();
        String query = "SELECT  type FROM type where id='"+abonnement.getType_id()+"'";
         pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            Typetxt.setText(rs.getString("type"));
        }
        //image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
        //fruitImg.setImage(image);
      
    }
      public String getData2() {
        return PrixAboLabel.getText();
    }
    
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        triercombo.setValue("Sort by");
        triercombo.getItems().addAll("name","price");
      
       
        
            
        
        /*try {
            fillcombo();
           
        } 
   catch (SQLException ex) {
        } */
         
           try {
               abonnements.addAll(getData());
           } catch (SQLException ex) {
               Logger.getLogger(FrontAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
           }
               if (abonnements.size() > 0) {
               try {
                   setChosenAbo(abonnements.get(0));
               } catch (SQLException ex) {
                   Logger.getLogger(FrontAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
               }
            myListener = new MyListener() {
                @Override
                public void onClickListener(Abonnement abonnement) {
                    try {
                        setChosenAbo(abonnement);
                    } catch (SQLException ex) {
                        Logger.getLogger(FrontAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < abonnements.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../GUI/Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                
                itemController.setData(abonnements.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                
                //set grid width
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
      @FXML
    private void sortCroissant(ActionEvent event) {
        
        
        if( (triercombo.getValue().equals("name") ||triercombo.getValue().equals("price"))){     
       
        mypane.getChildren().clear();
       
        mypane.getChildren().addAll(triercombo,fh,fb);

        
        for (int i = 0; i < fs.tri(triercombo.getValue(), "asc").size(); i++) {
            
            
            Text title =new Text(fs.tri(triercombo.getValue(), "asc").get(i).getNom());
            title.setLayoutX(215);
          
            title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            
            Text price  =new Text(String.valueOf(fs.tri(triercombo.getValue(), "asc").get(i).getCout()));
            price.setLayoutX(615);
           
            price.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            
            
            
            
               
            
            
            mypane.getChildren().addAll(title,price);
            
          
        
      
    }
        }
    }
     @FXML
    private void sortDecroissant(ActionEvent event) throws IOException {
    if( (triercombo.getValue().equals("name") ||triercombo.getValue().equals("price"))){     
       
        mypane.getChildren().clear();
       
        mypane.getChildren().addAll(triercombo,fh,fb);

        int column = 0;
        int row = 1;
        for (int i = 0; i < fs.tri(triercombo.getValue(), "desc").size(); i++) {
           
            //TextArea title=new TextArea(fs.tri(triercombo.getValue(), "desc").get(i).getNom());
            Text title =new Text(fs.tri(triercombo.getValue(), "desc").get(i).getNom());
            title.setLayoutX(215);
             if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(title, column++, row);
                //set grid width
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(title, new Insets(10));
          
            title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            
            Label price  =new Label(String.valueOf(fs.tri(triercombo.getValue(), "desc").get(i).getCout()));
            price.setLayoutX(615);
           
            price.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            
            
            
            
               
            
            
            mypane.getChildren().addAll(title,price);
            
          
        
      
    }
        }
    }
    
     @FXML
    private void recherche(ActionEvent event) throws SQLException {
        int k=-1; 
        for (int i = 0; i < abonnements.size(); i++) {
        
            if(abonnements.get(i).getNom() == null ? search.getText() == null : abonnements.get(i).getNom().equals(search.getText()))
            k=i;
         }
       
        if(k!=-1)
        setChosenAbo(abonnements.get(k));
    }
    

   
 
 @FXML
    private void acheter(Event event) throws IOException 
    {
        
              Parent root = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
  
    
}  

