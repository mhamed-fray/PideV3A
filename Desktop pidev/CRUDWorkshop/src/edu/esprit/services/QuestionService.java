/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;


import edu.esprit.entities.Choix;
import edu.esprit.entities.Question;
import edu.esprit.tools.MaConnexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author LENOVO
 */
public class QuestionService {
     Connection cnx;
    public QuestionService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
 public void ajouterQuestion(Question p,Choix c1,Choix c2,Choix c3){
     int qu_exist = 0;
     
     try {
               ObservableList<Question> obl =FXCollections.observableArrayList();
                             Connection cnx = MaConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select Contenu from question");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 
                System.out.println("contenu from the query "+rs.getString("contenu"));
                 if(rs.getString("contenu").equals(p.getContenu()))
                 {
                    System.out.println("in if statement");
                    qu_exist = 1;
                 }
                 
                  System.out.println("");
         
                  }
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
     
     if(qu_exist == 1)
     {
         Alert alert =new Alert(AlertType.CONFIRMATION,"already exists try another one");
        alert.showAndWait();
     }
     else{
        String query ="insert into question(contenu) values('"+p.getContenu()+"')";
        
            Statement ste;
        try {
            ste = cnx.createStatement();
            String [] colNames = new String [] { "id" };
            ste.execute(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ste.getGeneratedKeys();
     
            
           while (rs.next()) {
  java.math.BigDecimal idColVar = rs.getBigDecimal(1);     
                                      // Get automatically generated key 
                                      // value
  System.out.println("automatically generated key value = " + idColVar);
  ste.executeUpdate("insert into choix(contenu,etatchoix,question_id) values('"+c1.getContenu()+"','"+c1.getEtatchoix()+"','"+idColVar+"')");
  ste.executeUpdate("insert into choix(contenu,etatchoix,question_id) values('"+c2.getContenu()+"','"+c2.getEtatchoix()+"','"+idColVar+"')");
  ste.executeUpdate("insert into choix(contenu,etatchoix,question_id) values('"+c3.getContenu()+"','"+c3.getEtatchoix()+"','"+idColVar+"')");
}
           
           
            System.out.println("Question Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
     }
        
    }
 
 public void ajouterQuestion2(Question p){
        String sql="insert into question(contenu) values (?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getContenu());
            ste.executeUpdate();
            System.out.println("Question Ajoutée !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 public List<Question> afficherQuestion(){
        List<Question> personnes = new ArrayList<>();
        String sql ="select * from question";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Question p = new Question();
                p.setId(rs.getInt("id"));
                p.setContenu(rs.getString(2));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return personnes;
    }

 public void modifierQuestion(String Contenu, int id){
         String requete="UPDATE question SET Contenu='"+Contenu+"' where ID = '"+id+"'";
         

         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
          
           
            System.out.println("test modifié");
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
 public void supprimerQuestion(int id) {
       
       
            String requete = "delete from question where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
       
    }
 
 
 public void supprimerQuestion(String contenu){
       
       String requete = "delete from question where contenu=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,contenu);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
 
 public int modifierr(Question c){
String sq13="UPDATE `question`SET `contenu`=? WHERE contenu=?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, c.getContenu());
            
            pst.setString(2, c.getContenu());
                       
            System.out.println("qurey "+ pst);

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }
 

}
