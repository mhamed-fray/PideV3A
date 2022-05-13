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
import edu.esprit.tools.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Moez
 */
public class QuizController implements Initializable{
    
    private int qu_nbr = 0;
    
    @FXML
    private Pane mypane;
    
    @FXML
    private CheckBox checkbox_choix1;

    @FXML
    private CheckBox checkbox_choix2;

    @FXML
    private CheckBox checkbox_choix3;

    @FXML
    private Label text_question;

    @FXML
    private Label text_choix2;

    @FXML
    private Label text_choix1;

    @FXML
    private Label text_choix3;

    @FXML
    private Label text_test;
    
    @FXML
    private Button nextbutton;
    
    @FXML
    private Hyperlink next_text;
    
    
    private Choix choix1_checked;
    private Choix choix2_checked;
    private Choix choix3_checked;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
         ObservableList<Question>  list_qu =  FXCollections.observableArrayList();
         ObservableList<Test>  list_test =  FXCollections.observableArrayList();
         ObservableList<Choix>  list_ch =  FXCollections.observableArrayList();
         
          try { 
            
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs_test = cnx.createStatement().executeQuery("SELECT * FROM test where id = '29'");
            ResultSet rs_qu = cnx.createStatement().executeQuery("SELECT * FROM question where test_id = '29'");
            while(rs_test.next())
            {
                list_test.add(new Test(rs_test.getString(4),rs_test.getString(5),rs_test.getString(6)));
                
            }
            while(rs_qu.next())
            {
                list_qu.add(new Question(Integer.parseInt(rs_qu.getString(1)),rs_qu.getString(5)));
                
            }
            ResultSet rs_ch = cnx.createStatement().executeQuery("SELECT * FROM choix where question_id = '"+list_qu.get(0).getId()+"'");
            while(rs_ch.next())
            {
                list_ch.add(new Choix(Integer.parseInt(rs_ch.getString(1)),rs_ch.getString(5)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        text_test.setText(list_test.get(0).getTitre());   
        text_question.setText(list_qu.get(0).getContenu());
        text_choix1.setText(list_ch.get(0).getContenu());
        text_choix2.setText(list_ch.get(1).getContenu());
        text_choix3.setText(list_ch.get(2).getContenu());
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

        choix1_checked = list_ch.get(0);
        choix2_checked = list_ch.get(1);
        choix3_checked = list_ch.get(2);
        
        
        
        
    
}
    
    @FXML
    public void nextquestion() throws IOException {
        
        ChoixService cs = new ChoixService();
        
        int state1 = 0;
        int state2 = 0;
        int state3 = 0;
        
        
        if(checkbox_choix1.isSelected())
        {
            System.out.println("choix1 selected");
            state1 = 1;
        }
        if(checkbox_choix2.isSelected())
        {
            System.out.println("choix2 selected");
            state2 = 1;
        }
        if(checkbox_choix3.isSelected())
        {
            System.out.println("choix3 selected");
            state3 = 1;
        }
        
        choix1_checked.setChecked(state1);
        choix2_checked.setChecked(state2);
        choix3_checked.setChecked(state3);
        
        cs.checked_Choix(choix1_checked,choix2_checked,choix3_checked);
        
                
        
        
        
        if(qu_nbr == 8) 
        {
            
            
            
            next_text.setText("confirm quiz");
            
            confirmquiz();
            
            
        }else
        {
            qu_nbr++;
            
        
         ObservableList<Question>  list_qu =  FXCollections.observableArrayList();
         ObservableList<Test>  list_test =  FXCollections.observableArrayList();
         ObservableList<Choix>  list_ch =  FXCollections.observableArrayList();
         
          try { 
            
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs_test = cnx.createStatement().executeQuery("SELECT * FROM test where id = '29'");
            ResultSet rs_qu = cnx.createStatement().executeQuery("SELECT * FROM question where test_id = '29'");
            while(rs_test.next())
            {
                list_test.add(new Test(rs_test.getString(4),rs_test.getString(5),rs_test.getString(6)));
                
            }
            while(rs_qu.next())
            {
                list_qu.add(new Question(Integer.parseInt(rs_qu.getString(1)),rs_qu.getString(5)));
              
            }
            ResultSet rs_ch = cnx.createStatement().executeQuery("SELECT * FROM choix where question_id = '"+list_qu.get(qu_nbr).getId()+"'");
            while(rs_ch.next())
            {
                list_ch.add(new Choix(Integer.parseInt(rs_ch.getString(1)),rs_ch.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
       text_test.setText(list_test.get(0).getTitre());   
       text_question.setText(list_qu.get(qu_nbr).getContenu());
       text_choix1.setText(list_ch.get(0).getContenu());
       text_choix2.setText(list_ch.get(1).getContenu());
       text_choix3.setText(list_ch.get(2).getContenu());
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

        
        choix1_checked = list_ch.get(0);
        choix2_checked = list_ch.get(1);
        choix3_checked = list_ch.get(2);
        
        
        checkbox_choix1.setSelected(false);
        checkbox_choix2.setSelected(false);
        checkbox_choix3.setSelected(false);
        
        // TODO

        // TODO
        // TODO   
        
        
        }
}
    
    @FXML
    public void confirmquiz(){
        
        
        ChoixService cs = new ChoixService();
        
        int state1 = 0;
        int state2 = 0;
        int state3 = 0;
        
        
        if(checkbox_choix1.isSelected())
        {
            System.out.println("choix1 selected");
            state1 = 1;
        }
        if(checkbox_choix2.isSelected())
        {
            System.out.println("choix2 selected");
            state2 = 1;
        }
        if(checkbox_choix3.isSelected())
        {
            System.out.println("choix3 selected");
            state3 = 1;
        }
        
        choix1_checked.setChecked(state1);
        choix2_checked.setChecked(state2);
        choix3_checked.setChecked(state3);
        
        cs.checked_Choix(choix1_checked,choix2_checked,choix3_checked);
        
        
        ObservableList<Question>  list_qu =  FXCollections.observableArrayList();
         ObservableList<Test>  list_test =  FXCollections.observableArrayList();
         ObservableList<Choix>  list_ch =  FXCollections.observableArrayList();
         
          try { 
            
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs_test = cnx.createStatement().executeQuery("SELECT * FROM test where id = '29'");
            ResultSet rs_qu = cnx.createStatement().executeQuery("SELECT * FROM question where test_id = '29'");
            while(rs_test.next())
            {
                list_test.add(new Test(rs_test.getString(4),rs_test.getString(5),rs_test.getString(6)));
                
            }
            while(rs_qu.next())
            {
                list_qu.add(new Question(Integer.parseInt(rs_qu.getString(1)),rs_qu.getString(5)));
                
            }
            ResultSet rs_ch = cnx.createStatement().executeQuery("SELECT * FROM choix where question_id = '"+list_qu.get(9).getId()+"'");
            while(rs_ch.next())
            {
                list_ch.add(new Choix(Integer.parseInt(rs_ch.getString(1)),rs_ch.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
       text_test.setText(list_test.get(0).getTitre());   
       text_question.setText(list_qu.get(9).getContenu());
       text_choix1.setText(list_ch.get(0).getContenu());
       text_choix2.setText(list_ch.get(1).getContenu());
       text_choix3.setText(list_ch.get(2).getContenu());
       
       choix1_checked = list_ch.get(0);
        choix2_checked = list_ch.get(1);
        choix3_checked = list_ch.get(2);
       
       next_text.setOnAction(s->{
           
             int state11 = 0;
             int state22 = 0;
             int state33 = 0;
        
        
        if(checkbox_choix1.isSelected())
        {
            System.out.println("choix1 selected");
            state11 = 1;
        }
        if(checkbox_choix2.isSelected())
        {
            System.out.println("choix2 selected");
            state22 = 1;
        }
        if(checkbox_choix3.isSelected())
        {
            System.out.println("choix3 selected");
            state33 = 1;
        }
        
        choix1_checked.setChecked(state11);
        choix2_checked.setChecked(state22);
        choix3_checked.setChecked(state33);
        
        cs.checked_Choix(choix1_checked,choix2_checked,choix3_checked);
                                             
            try {    
                FXMLLoader loader =new FXMLLoader(getClass().getResource("note.fxml"));
                Parent root;
                
                root = (Parent) loader.load();
                Stage window= (Stage) mypane.getScene().getWindow();
                window.setScene(new Scene(root));
                
                            
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
                    

            });
       
        
    
    }
    
}
