/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Offre;
import edu.esprit.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class OffreService {
      Connection cnx;

    public OffreService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterOffre(Offre off){
        String query = "insert into offre(titre,secteur,description,localisation,salaire) values('"+off.getTitre()+"','"+off.getSecteur()+"','"+off.getDescription()+"',"+off.getLocalisation()+"',"+off.getSalaire();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Offre Ajoute");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Offre> afficherOffre(){
      List<Offre> Offres = new ArrayList<>();
      String sql = "select * from Offre";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()){
                Offre a = new Offre();
                a.setId(rs.getInt("id"));
                a.setTitre(rs.getString("titre"));
                a.setSecteur(rs.getString("secteur"));
                a.setDescription(rs.getString("description"));
                a.setLocalisation(rs.getString("localisation"));
               a.setSalaire(rs.getFloat("salaire"));




                Offres.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
      return Offres;
      
}
    
    public void modifierOffre(String titre, int id){
         String requete="UPDATE offre SET titre='"+titre+"' where id = '"+id+"'";
         

         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
          
           
            System.out.println("Offre modifié");
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
 public void supprimerOffre(int id) {
       
       
            String requete = "delete from offre where id=?";
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
}
