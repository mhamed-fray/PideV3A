/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.Offre;
import helpers.DbConnect;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Offre;

/**
 *
 * @author LENOVO
 */
public class OffreService {
      Connection cnx;

    public OffreService() {
        cnx=DbConnect.getConnect();
    }
    public boolean ajouterOffre(Offre e){
         String req="insert into offre (titre,secteur,description,localisation,salaire) values (?,?,?,?,?)";
        Boolean inserted=false;
        try {
            PreparedStatement
            pst=cnx.prepareStatement(req);
            pst.setString(1,e.getTitre());
            pst.setString(2,e.getSecteur());
            pst.setString(3,e.getDescription());
            pst.setString(4,e.getLocalisation());
            pst.setDouble(5,e.getSalaire());
        
            inserted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
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
    
    public boolean modifierOffre(Offre e){
         String req="UPDATE offre SET titre=?,secteur=?,description=?,localisation=?,salaire=? WHERE id=?";
       Boolean updated=false;
        try {
           PreparedStatement pst=cnx.prepareStatement(req);
          
            pst.setString(1,e.getTitre());
            pst.setString(2,e.getSecteur());
            pst.setString(3,e.getDescription());
            pst.setString(4,e.getLocalisation());
            pst.setDouble(5,e.getSalaire());
             pst.setInt(6,e.getId());
            updated=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return updated;
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
 
 
   public Offre get_offre(int id) {
        List<Offre> list =new ArrayList<>();
        try{
            String req ="select * from Offre where id="+id;
            Statement st = cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
    while(rs.next()){
        Offre r =new Offre();
        r.setId(rs.getInt("id"));
        r.setTitre(rs.getString("titre"));
        r.setSecteur(rs.getString("secteur"));
        r.setDescription(rs.getString("description"));
        r.setLocalisation(rs.getString("localisation"));
        r.setSalaire(rs.getDouble("salaire"));
       
      
       
        
        
         return r;
          
    }
        } catch (SQLException ex) {
        Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
   
   
 
 public List<Offre> Search(String nom) {
      
         List<Offre> list =new ArrayList<>();
        try {
            PreparedStatement
           pst=cnx.prepareStatement("SELECT * from Offre WHERE secteur like ?");
              nom+= "%";
                pst.setString(1,nom);
                ResultSet
              rs=pst.executeQuery();
            while(rs.next())
            {
                list.add(new Offre(rs.getInt("id"),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getFloat(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
        
         }
 
 
}
