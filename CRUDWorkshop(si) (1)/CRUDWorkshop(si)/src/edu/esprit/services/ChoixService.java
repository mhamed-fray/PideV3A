/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Choix;
import edu.esprit.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moez
 */
public class ChoixService {
    Connection cnx;
    public ChoixService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
 public void ajouterChoix(Choix p){
        String query ="insert into choix(contenu) values('"+p.getContenu()+"')";
        
            Statement ste;
        try {
            ste = cnx.createStatement();
             ste.executeUpdate(query);
            System.out.println("Question Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
        
    }
 public void ajouterChoix2(Choix p){
        String sql="insert into choix(contenu) values (?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getContenu());
            ste.executeUpdate();
            System.out.println("Choix Ajoutée !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 public List<Choix> afficherChoix(int qu_id){
        List<Choix> personnes = new ArrayList<>();
        String sql ="select * from choix where question_id="+qu_id;
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               Choix p = new Choix();
                p.setId(rs.getInt("id"));
                p.setContenu(rs.getString(5));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return personnes;
    }

 public void modifierChoix(String Contenu, int id){
    String requete="UPDATE choix SET Contenu='"+Contenu+"' where ID = '"+id+"'";
         

         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
          
           
            System.out.println("test modifié");
        } catch (SQLException ex) {
            Logger.getLogger(ChoixService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
 
 
 public void supprimerChoix(int id) {
       
       
            String requete = "delete from choix where id=?";
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
 
 
 public void supprimerChoix(String nom){
       
       String requete = "delete from choix where nom=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,nom);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(ChoixService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
 
 public int modifierChoix (Choix c){
     
     
     
String sq13="UPDATE `choix`SET `contenu`=? WHERE contenu =?";

            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, c.getContenu());
                       

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ChoixService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }
 
    
    
    public void checked_Choix(Choix c1,Choix c2,Choix c3){
        String query1 ="update choix set checked="+c1.getChecked()+" where id="+c1.getId();
        String query2 ="update choix set checked="+c2.getChecked()+" where id="+c2.getId();
        String query3 ="update choix set checked="+c3.getChecked()+" where id="+c3.getId();
        
            Statement ste;
        try {
            ste = cnx.createStatement();
             ste.executeUpdate(query1);
             ste.executeUpdate(query2);
             ste.executeUpdate(query3);
            System.out.println("quiz Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
        
    }
    
}
