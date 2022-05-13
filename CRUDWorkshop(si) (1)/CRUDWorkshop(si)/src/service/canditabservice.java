/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.canditab;
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
import models.Offre;


/**
 *
 * @author LENOVO
 */
public class canditabservice {
      Connection cnx;

    public canditabservice() {
        cnx=DbConnect.getConnect();
    }
     
   
    
    public boolean ajoutercandidature(canditab e){
         String req="insert into canditab (nom,prenom,email,message,num) values (?,?,?,?,?)";
        Boolean inserted=false;
        try {
            PreparedStatement
            pst=cnx.prepareStatement(req);
            pst.setString(1,e.getNom());
            pst.setString(2,e.getPrenom());
            pst.setString(3,e.getEmail());
            pst.setString(4,e.getMessage());
           pst.setInt(5,e.getNum());

           
        
            inserted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(canditabservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
        
    }
     public canditab get_cand(int id) {
        List<Offre> list =new ArrayList<>();
        try{
            String req ="select * from canditab where id="+id;
            Statement st = cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
    while(rs.next()){
        canditab r =new canditab();
        r.setId(rs.getInt("id"));
        r.setNom(rs.getString("nom"));
        r.setPrenom(rs.getString("prenom"));
        r.setMessage(rs.getString("email"));
        r.setMessage(rs.getString("message"));
                r.setNum(rs.getInt("num"));

        
       
       
     return r;
          
    }
        } catch (SQLException ex) {
        Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
 
    public List<canditab> affichercandidatures(){
      List<canditab> canditabs = new ArrayList<>();
      String sql = "select * from canditab";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()){
                canditab a = new canditab();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("prenom"));
                a.setEmail(rs.getString("email"));
                a.setMessage(rs.getString("message"));
               a.setNum(rs.getInt("num"));

              
       
              




                canditabs.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
      return canditabs;
      
}
   
    
    public boolean modifiercandidature(canditab e){
         String req="UPDATE canditab SET nom=?,prenom=?,email=?,message=? WHERE id=?";
       Boolean updated=false;
        try {
           PreparedStatement pst=cnx.prepareStatement(req);
          
            pst.setString(1,e.getNom());
            pst.setString(2,e.getPrenom());
            pst.setString(3,e.getEmail());
            pst.setString(4,e.getMessage());
           pst.setInt(4,e.getNum());

           
             pst.setInt(6,e.getId());
            updated=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(canditabservice.class.getName()).log(Level.SEVERE, null, ex);
        }
     return updated;
     }
 public void supprimercandidature(int id) {
       
       
            String requete = "delete from canditab where id=?";
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
