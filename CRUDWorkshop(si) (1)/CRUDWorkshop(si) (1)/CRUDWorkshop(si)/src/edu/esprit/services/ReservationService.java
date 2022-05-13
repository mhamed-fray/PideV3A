/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Reservation;
import edu.esprit.tools.MaConnexion;
import java.sql.Connection;
import java.sql.Date;
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
 * @author amarz
 */
public class ReservationService {
    
    
    Connection cnx;
 
    PreparedStatement ste;
    public ReservationService() {
        cnx=MaConnexion.getInstance().getCnx();
    }

   
    public void ajouterResrvation(int res,int id){
       String sql = "INSERT INTO reservation(eve_id,nbplace) VALUES(?,?)";
        try {
            ste = cnx.prepareStatement(sql);
            
            ste.setInt(1, id);
            ste.setInt(2, res);
            cnx.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Reservation ajoutée!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Reservation> afficherReservation(){
      List<Reservation> reservations = new ArrayList<>();
      String sql = "select * from reservation";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()){
                Reservation e = new Reservation();
                e.setId(rs.getInt("id"));
                e.setId_eve(rs.getInt("id_eve"));
                e.setNbplace(rs.getInt("nbplace"));
                
              //  e.setDate(rs.getDate("dat"));
                //a.setType_id(type_id);
                reservations.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
      return reservations;
      
}
    
    public void modifierReservation(int id,int id_eve,int nbplace){
         String requete="UPDATE reservation SET id_eve='"+id_eve+"' nbplace='"+nbplace+"' where ID = '"+id+"'";
         

         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
          
           
            System.out.println("reservation modifié");
        } catch (SQLException ex) {
            Logger.getLogger(edu.esprit.services.EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
 public void supprimerReservation(int id) {
       
       
            String requete = "delete from reservation where id=?";
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
 public Evenement Selectevent(int idevent) {
        Evenement pu = null;
        try {
            String req = "select  * from evenement where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idevent);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
               System.out.println(rs);
                pu =new Evenement(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getInt(6));
           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pu;
    }
 
 public Evenement findById(int id) {
        Evenement a = null;
        try {
            String req = "select * from evenement where id=? ";
             PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                a = new Evenement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDate(6));
                System.out.println("bgfhjkjhxdhjhvjhgk" + rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
 
 public void increment(int nb,int id) throws SQLException{
      try {
            String req = "select nb_participants from evenement where id=?";
             PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int a = rs.getInt(1);
                a +=nb;
                String req2 = "update evenement set nb_participants=? where id=? ";
             PreparedStatement st2 = cnx.prepareStatement(req2);
            st2.setInt(1, a);
            st2.setInt(2, id);
             st2.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          
        
        

     
 }
 
 
    
    
}
