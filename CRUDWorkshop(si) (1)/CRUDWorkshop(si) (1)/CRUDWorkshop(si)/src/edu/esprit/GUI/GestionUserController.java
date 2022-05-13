/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import edu.esprit.entities.Abonnement;
import edu.esprit.entities.User;
import edu.esprit.services.AbonnementService;
import edu.esprit.services.UserService;
import edu.esprit.tools.MaConnexion;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, Integer> id; 
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> password;
           @FXML
    private TableColumn<User, String> etat;

     @FXML
    private TableColumn<User, String> roles;
    @FXML
    private TextField txtEmail;
    	@FXML
	private TextField filterField;
    @FXML
    private PasswordField txtPassword;
   @FXML
   private ComboBox <String> ComboxRole ;
      @FXML
   private ComboBox <String> ComboxEtat;

   private  final String EMAIL_REGEX = "^[a-zA-Z][a-zA-Z0-9._-]*\\@\\w+(\\.)*\\w+\\.\\w+$";
    private  final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
         String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
	private ObservableList<User> dataList = FXCollections.observableArrayList();

  ObservableList<String>  listrole =  FXCollections.observableArrayList("Entreprise","TestTaker","TestMaker");
    ObservableList<String>  listetat =  FXCollections.observableArrayList("enable","disable");
     private User c; 
            
    ObservableList<User>  list =  FXCollections.observableArrayList();
      int  index= -1; 
    @FXML
    private ComboBox<String> affecter;
                Connection cnx = MaConnexion.getInstance().getCnx();
   private Label label;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ComboxRole.setItems(listrole);
          ComboxEtat.setItems(listetat);
         ObservableList<User>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT  email, etat,roles FROM user");
            while(rs.next())
            {
                list.add(new User( rs.getString(1),rs.getString(2), rs.getString(3)));
             dataList.add(new User( rs.getString(1),rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       email.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getEmail());
        });

    etat.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getEtat());
            
        });
    roles.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getRoles());
            
        });  
        // TODO
          System.out.println("eeeeeeeeeee"+list);
            tableUser.setItems(list);
             tableUser.refresh();
               FilteredList<User> filteredData = new FilteredList<>(dataList, b -> true);

		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (employee.getRoles().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}

				     else  
				    	 return false;
			});
		});

	SortedList<User> sortedData = new SortedList<>(filteredData);
	sortedData.comparatorProperty().bind(tableUser.comparatorProperty());

	tableUser.setItems(sortedData);
}

    @FXML
    private void selectedl(MouseEvent event) {
        index=tableUser.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
                txtEmail.setText(email.getCellData(index).toString());
            txtPassword.setText(tableUser.getSelectionModel().getSelectedItem().getPassword());
 
    }

    public ObservableList<User> show1()
    { 
           try {
               ObservableList<User> obl =FXCollections.observableArrayList();
                             Connection cnx = MaConnexion.getInstance().getCnx();
                 PreparedStatement pt= cnx.prepareStatement("select id, email, password , etat , roles from user ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                 User ls = new User();
                 ls.setEmail(rs.getString("email"));
                 ls.setPassword(rs.getString("password"));
                 ls.setEtat(rs.getString("etat"));
                 ls.setRoles(rs.getString("roles"));
                 obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    } 
     public void affiche() {
      //   id.setCellValueFactory(new PropertyValueFactory<>("id"));               
      email.setCellValueFactory(new PropertyValueFactory<>("email"));
//      password.setCellValueFactory(new PropertyValueFactory<>("password"));
      etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
      roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
      ObservableList<User> obl =FXCollections.observableArrayList();
      
       obl=show1(); 
      tableUser.setItems(obl);
  
                      
    }
      @FXML
    private void supprimerloc(ActionEvent event) {
        if(tableUser.getSelectionModel().isEmpty() ==false){
           
              Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Delete User");
      alert.setHeaderText("Are you sure want to delete this user?");
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         this.label.setText("No selection!");
      } else if (option.get() == ButtonType.OK) {
          UserService us = new UserService();
        User ls = new User();
        ls = tableUser.getSelectionModel().getSelectedItem();
      
     us.Supprimeruser(ls.getEmail());
                    affiche();   
      } else if (option.get() == ButtonType.CANCEL) {
         this.label.setText("Cancelled!");
      } else {
         this.label.setText("-");
      }
       
    }else {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de selection");
     
           alert.setContentText("vous n avez pas selectionez aucun element");
            alert.showAndWait();
         
            
        }
    }
        @FXML
    private void modifierabonnement(ActionEvent event) {
    
    if(tableUser.getSelectionModel().isEmpty() ==true){
           Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de selection");
     
           alert.setContentText("vous n avez pas selectionez aucun element");
            alert.showAndWait();}
    else {
       if ( controlSaisie()){
        String email=txtEmail.getText();
        String password=txtPassword.getText();
        String etat=ComboxEtat.getValue();
         String roles=ComboxRole.getValue();
      
        UserService sp = new UserService();
        User c = new User();
        c.setEmail(email);
        c.setPassword(password);
        c.setEtat(etat);
        c.setRoles(roles);
        c.setId(tableUser.getSelectionModel().getSelectedItem().getId());
       Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Modify User");
      alert.setHeaderText("Are you sure want to modify  user with email " + tableUser.getSelectionModel().getSelectedItem().getEmail());
         Optional<ButtonType> option = alert.showAndWait();

     if (option.get() == null) {
         this.label.setText("No selection!");
      } else if (option.get() == ButtonType.OK) {
              sp.ModifyUser(c,tableUser.getSelectionModel().getSelectedItem().getEmail());
                  affiche();
//         this.label.setText("User modified!");
      } else if (option.get() == ButtonType.CANCEL) {
         this.label.setText("Cancelled!");
      } else {
         this.label.setText("-");
     }  
    } 
              }
  }
      @FXML
    private boolean ajouterloc(ActionEvent event) {
       if ( controlSaisie()){
          UserService us = new UserService();
            if(us.FindUserbyEmail(txtEmail.getText()) == true){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Email Used !!!");
                alert.setContentText("The email " + txtEmail.getText()+ "is already used\n" + "Please try another");
                alert.showAndWait();
             return false;            
            }
         UserService ts = new UserService();
             ts.ajouterUser(new User(txtEmail.getText(),txtPassword.getText(),ComboxEtat.getValue(),ComboxRole.getValue()));
            affiche();       
             tableUser.refresh();
        }
    return true ;
    }
 
     public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
        if(isValidEmail(txtEmail.getText()) == false){
           System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+isValidEmail(txtEmail.getText()));
           alert.setContentText("The email " + txtEmail.getText()+ "is not a valid email, please try another");
            alert.showAndWait();
            return false;
             
        }
        if(isValidPassword(txtPassword.getText(),regex) ==false){
            alert.setContentText("Must have at least one numeric character\n" +
                               "Must have at least one lowercase character\n" +
                                "Must have at least one uppercase character\n" +
                                  "Must have at least one special symbol among @#$%\n" +
                                   "Password length should be between 8 and 20");
            alert.showAndWait();
            return false;
        }
                 
         if(ComboxRole.getValue() == null ){
           alert.setContentText("Le role ne doit pas etre null");
           alert.showAndWait();
           return false;
       }
         if(ComboxEtat.getValue() == null ){
           alert.setContentText("L etat ne doit pas etre null");
           alert.showAndWait();
           return false;
       }

        return true;

    }
      public  boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
       public static boolean isValidPassword(String password,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
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
