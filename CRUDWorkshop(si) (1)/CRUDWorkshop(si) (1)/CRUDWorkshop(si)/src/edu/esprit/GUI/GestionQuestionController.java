/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import com.itextpdf.text.DocumentException;
import edu.esprit.entities.Choix;
import edu.esprit.entities.Question;
import edu.esprit.entities.Test;
import edu.esprit.services.ChoixService;
import edu.esprit.services.QuestionService;
import edu.esprit.services.ServicePdf;
import edu.esprit.tools.MaConnexion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionQuestionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    
    @FXML
    private TableView<Question> tablequestion;

    @FXML
    private TableColumn<Question, String> NomCol;
    
    @FXML
    private TextField txtNom;
    
    @FXML
    private TextField txtchoix1;
    
    @FXML
    private TextField txtchoix2;
    
    @FXML
    private TextField txtchoix3;
    
    @FXML
    private CheckBox etat1;

    @FXML
    private CheckBox etat2;

    @FXML
    private CheckBox etat3;
    
    @FXML
    private Button btnImprimer;
    
    @FXML
    private Button btnAjout;

    @FXML
    private Button btnSupp;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnModif;
    
    @FXML
    private Hyperlink bibliotheque;
    
    
    
    
    
    
    
    private int id_id;
    
    private Question c; 
    
    private String question_to_modifie;
    
    private Test test_instance;
            
            ObservableList<Question>  list =  FXCollections.observableArrayList();
      int  index= -1; 
      
    @FXML
    private ComboBox<String> affecter;
                Connection cnx = MaConnexion.getInstance().getCnx();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         ObservableList<Question>  list =  FXCollections.observableArrayList();
         
         try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("TestToDelete.fxml"));
            
            AnchorPane anchorPane = fxmlLoader.load();
            
            
        } catch (IOException ex) {
        }
         
          try { 
            
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT Contenu FROM question");
            int i=0;
            while(rs.next())
            {
                
                list.add(new Question(rs.getString(1)));
                System.out.println("lista = "+list.get(i).getContenu());
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }

          
          System.out.println("the id of test is = "+id_id);
          
          
          
      NomCol.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getContenu());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

   
    
  
        // TODO
  tablequestion.setItems(list);
  tablequestion.refresh();
        
        
    
}
    
     @FXML
     void affichage() {
        
        ObservableList<Question>  list =  FXCollections.observableArrayList();
          try { 
            
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT Contenu FROM question");
            int i=0;
            while(rs.next())
            {
                
                list.add(new Question(rs.getString(1)));
                System.out.println("lista = "+list.get(i).getContenu());
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }

          
          System.out.println("the id of test is = "+id_id);
          
          
          
      NomCol.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getContenu());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

   
    
  
        // TODO
  tablequestion.setItems(list);
  tablequestion.refresh();
        // TODO
        // TODO   
        
   

    }
    
    
    
    
    
    @FXML
    private void selectedl(MouseEvent event) {
        
        index=tablequestion.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        txtNom.setText(NomCol.getCellData(index).toString());
        
        question_to_modifie = txtNom.getText();

    }
    
    
    public ObservableList<Question> show1()
    { 
       

           try {
               ObservableList<Question> obl =FXCollections.observableArrayList();
                             Connection cnx = MaConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select Contenu from question");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Question ls = new Question();
                
                 ls.setContenu(rs.getString("contenu"));

                 
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
      ObservableList<Question> obl =FXCollections.observableArrayList();
      obl=show1(); 
      tablequestion.setItems(obl);
      System.out.println(""+obl);

                      
    }
      
    @FXML 
    private void supprimerquestion(ActionEvent event) {
        QuestionService abo = new QuestionService();
        Question ls = new Question();
        ls = tablequestion.getSelectionModel().getSelectedItem();
                

        abo.supprimerQuestion(ls.getContenu());
                        affiche();

    }
    
    
    
     @FXML
    private void modifierquestion(ActionEvent event) {
        String nom=txtNom.getText();

        QuestionService sp = new QuestionService();
        Question c = new Question();
        c.setContenu(nom);
        
        sp.modifierr(c,question_to_modifie);
        
        System.out.println("zsfezasqf  "+c.getContenu());
                affiche();

    }
    
    
    
    
     @FXML
    private void ajouterquestion(ActionEvent event) {
        int state1 = 0;
        int state2 = 0;
        int state3 = 0;
        
        if ( controlSaisie()){
         
         QuestionService ts = new QuestionService();
         ChoixService cs = new ChoixService();
         Question question = new Question(txtNom.getText());
         BooleanProperty test = etat1.selectedProperty();
         
         if(etat1.isSelected())
             state1 = 1;
         if(etat2.isSelected())
             state2 = 1;
         if(etat3.isSelected())
             state3 = 1;
                 
             
         Choix c1 = new Choix(txtchoix1.getText(),state1);
         Choix c2 = new Choix(txtchoix2.getText(),state2);
         Choix c3 = new Choix(txtchoix3.getText(),state3);
         
        ts.ajouterQuestion(question,c1,c2,c3);
       
                affiche();
                
        System.out.println("before refresh");

        tablequestion.refresh();
       
        System.out.println("after refresh");
       
    }}
    
    
    @FXML
    private void Imprimerquestions(ActionEvent event) throws FileNotFoundException, DocumentException {
        
        ServicePdf SP = new ServicePdf();
        SP.liste_ProjetPDF();
        
    }
    
    
    
    @FXML
    public void setTest(Test t) {
        test_instance.setId(t.getId());

    }
    
    
    void setid_id(int id){
        this.id_id = id;
        
        System.out.println("ffzefazfazf" + id);
        System.out.println("class id = " + this.id_id);
    }
    
    
    
    
    
    
     public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        
                

        
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
    
    
    
    @FXML 
    private void bibliothequequestion(ActionEvent event) {
        QuestionService abo = new QuestionService();
        Question ls = new Question();
        ls = tablequestion.getSelectionModel().getSelectedItem();
                

        abo.supprimerQuestion(ls.getContenu());
                        affiche();

    }
  
  

  
}
