/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.entities.Choix;
import edu.esprit.entities.Question;
import edu.esprit.entities.Test;
import edu.esprit.services.ChoixService;
import edu.esprit.services.QuestionService;
import edu.esprit.tools.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 *
 * @author Moez
 */



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



    



public class TestMakerQuestionController {
    
    
    private int qu_nbr = 0;
    
    
    
    @FXML
    private AnchorPane mypane;

    @FXML
    private TextField txtNom;

    @FXML
    private Button btnAjout;

    @FXML
    private Button btnRetour;

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
    private Hyperlink next_text;
    
    

    
    
    private int id_id;
    
    private Question c; 
    
    private String question_to_modifie;
    
    private Test test_instance;
            
            
      
    
    
 
    
    
    @FXML
    private void ajouterquestion() {
        
        next_text.setOnAction(s->{
           
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
       
                
        }
                    

            });
       
                
                
        
       
    }
    
    
    @FXML
    public void nextquestion(ActionEvent event) throws IOException {
        
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
       
                
        }
        
        if(qu_nbr == 8) 
        {
            
            next_text.setText("Confirm questions");
            
            ajouterquestion();
            
            
        }else
        {
            qu_nbr++;
        }
          
       
  
        
        // TODO

        // TODO
        // TODO   
        
        
        
        
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
    
}
