/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;
import edu.esprit.entities.Sujet;
import edu.esprit.tools.MaConnexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author HP
 */
public class SujetService {
    Connection cnx;

public SujetService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
 public void ajouterSujet(Sujet s){
        String query ="insert into sujet(sujet) values('"+s.getSujet()+"')";
        
            Statement ste;
        try {
            ste = cnx.createStatement();
             ste.executeUpdate(query);
            System.out.println("sujet Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
        
    }
 
 public List<Sujet> afficherSujet(){
        List<Sujet> list = new ArrayList<>();
        String sql ="select * from sujet";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               Sujet p = new Sujet();
                p.setId(rs.getInt("id"));
                p.setSujet(rs.getString("sujet"));
               
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

 public void modifierSujet(String sujet, int id){
         String requete="UPDATE sujet SET sujet='"+sujet+"' where ID = '"+id+"'";
         

         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
          
           
            System.out.println("sujet modifié");
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
 public void supprimerSujet(String sujet) {
       
       
            String requete = "delete from sujet where sujet=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,sujet );
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
     
        
    }
 
    public List<Sujet> afficher1() {
        List<Sujet> list = new ArrayList<>();
        
        try {
            String req = "SELECT sujet from sujet";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Sujet( rs.getString("sujet")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
public int modifier (Sujet c){
String sq14="UPDATE `sujet`SET `sujet`=? WHERE sujet=?";

            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq14);
            pst.setString(1, c.getSujet());
            pst.setString(2, c.getSujet());
                        

                       

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }


public List<Sujet> ListClasse() {
        List<Sujet> Mylist = new ArrayList<>();
        try {
            String requete = "select * from sujet ORDER BY sujet ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
      ResultSet e = pst.executeQuery();
            while (e.next()) {
               Sujet pre = new Sujet();
             
            
            pre.setSujet(e.getString("sujet"));
            
            
            
            
           
           
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
}





