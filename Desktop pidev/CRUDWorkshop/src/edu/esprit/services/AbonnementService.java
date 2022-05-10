/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Abonnement;
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
 * @author LENOVO
 */
public class AbonnementService {
     Connection cnx;

    public AbonnementService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterAbonnement(Abonnement abo){
        String query = "insert into abonnement(nom,description,cout) values('"+abo.getNom()+"','"+abo.getDescription()+"','"+abo.getCout()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Abonnement Ajoute");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Abonnement> afficherAbonnement(){
      List<Abonnement> abonnements = new ArrayList<>();
      String sql = "select * from abonnement";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()){
                Abonnement a = new Abonnement();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString(2));
                a.setDescription(rs.getString("description"));
                a.setCout(rs.getString("cout"));
                //a.setType_id(rs.getInt("type_id"));
                abonnements.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
      return abonnements;
      
}
    
    public void modifierAmbonnement(String Description, int id){
         String requete="UPDATE abonnement SET Description='"+Description+"' where ID = '"+id+"'";
         

         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
          
           
            System.out.println("abonnement modifié");
        } catch (SQLException ex) {
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
 public void supprimerAbonnement(int id) {
       
       
            String requete = "delete from abonnement where id=?";
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
 
   
 public void SupprimerAbo(String nom){
       
       String requete = "delete from abonnement where nom=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,nom);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
 
   public int modifier (Abonnement c){
String sq13="UPDATE `abonnement`SET `nom`=?,`description`=?,`cout`=? WHERE nom =?";

            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getDescription());
                        

            pst.setString(3, c.getCout());
            pst.setString(4, c.getNom());
                       

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }

}
