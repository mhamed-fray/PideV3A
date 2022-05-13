/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;


import javafx.scene.shape.Rectangle;
import edu.esprit.entities.Choix;
import edu.esprit.entities.Question;
import edu.esprit.tools.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;

/**
 *
 * @author Moez
 */
public class NoteController implements Initializable {
    
    
    @FXML
    private Label note;

    @FXML
    private Label comment_note;

    @FXML
    private Button confirm_button;
    
    
    
    
    
    private int note_final = 0;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        Choix choix1_register = new Choix();
        Choix choix2_register = new Choix();
        Choix choix3_register = new Choix();
        
         ObservableList<Question>  list_qu =  FXCollections.observableArrayList();
         
          try { 
              
              
            
            Connection cnx = MaConnexion.getInstance().getCnx();
            
            ResultSet rs_qu = cnx.createStatement().executeQuery("SELECT * FROM question where test_id = '29'");
            while(rs_qu.next())
            {
                ObservableList<Choix>  list_ch =  FXCollections.observableArrayList();
                
                
                
                
                list_qu.add(new Question(Integer.parseInt(rs_qu.getString(1)),rs_qu.getString(5)));
                ResultSet rs_ch = cnx.createStatement().executeQuery("SELECT * FROM choix where question_id = '"+Integer.parseInt(rs_qu.getString(1))+"'");
                while(rs_ch.next())
                    {
                        list_ch.add(new Choix(Integer.parseInt(rs_ch.getString(1)),Integer.parseInt(rs_ch.getString(4)),Integer.parseInt(rs_ch.getString(6))));
                        
                    }
                
                
                choix1_register = list_ch.get(0);
                choix2_register = list_ch.get(1);
                choix3_register = list_ch.get(2);
                
                System.out.println(choix1_register.getChecked());
                System.out.println(choix2_register.getChecked());
                System.out.println(choix3_register.getChecked());
                    
                
                
                if( choix1_register.getEtatchoix() == 1 && choix1_register.getChecked() == 1 || choix2_register.getEtatchoix() == 1 && choix2_register.getChecked() == 1 || choix3_register.getEtatchoix() == 1 && choix3_register.getChecked() == 1 ) {
                
                    note_final++;
                
                } 
                
                            
                        
            }
            
            
        } catch (SQLException ex) {
            System.out.println("in note exc^ption");
            Logger.getLogger(GestionQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

    
  
        // TODO

        // TODO
        // TODO   
        
       note.setText(""+note_final);
       
       
       if(note_final < 6)
       {
           comment_note.setText("Test echoué");
           comment_note.setTextFill(Color.RED);
           note.setTextFill(Color.RED);
       }else{
           comment_note.setText("Test validé");
           comment_note.setTextFill(Color.LIGHTGREEN);
           note.setTextFill(Color.LIGHTGREEN);
       }
           
       
       
        
        
    
}

    
        
    
    
    
    
}
