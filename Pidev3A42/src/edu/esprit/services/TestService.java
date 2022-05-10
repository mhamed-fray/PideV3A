/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Test;
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
public class TestService {
    Connection cnx;
    public TestService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
 public void ajouterTest(Test p){
        String query ="insert into test(duree,nbrp,titre) values('"+p.getDuree()+"','"+p.getNbrp()+"','"+p.getTitre()+"')";
        
            Statement ste;
        try {
            ste = cnx.createStatement();
             ste.executeUpdate(query);
            System.out.println("Test Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
        
    }
 public void ajouterTest2(Test p){
        String sql="insert into test(duree,nbrp,titre) values (?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getDuree()); 
            ste.setString(2, p.getNbrp());
            ste.setString(3, p.getTitre());
            ste.executeUpdate();
            System.out.println("Test Ajoutée !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 public List<Test> afficherTest(){
        List<Test> list = new ArrayList<>();
        String sql ="select * from test";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               Test p = new Test();
                p.setId(rs.getInt("id"));
                p.setDuree(rs.getString(2));
                p.setNbrp(rs.getString("nombre des pages"));
                p.setTitre(rs.getString("titre"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

 public void modifierTest(String Titre, int id){
         String requete="UPDATE test SET Titre='"+Titre+"' where ID = '"+id+"'";
         

         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
          
           
            System.out.println("test modifié");
        } catch (SQLException ex) {
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
 public void supprimerTest(String titre) {
       
       
            String requete = "delete from test where titre=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,titre );
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
     
        
    }
 
    public List<Test> afficher1() {
        List<Test> list = new ArrayList<>();
        
        try {
            String req = "SELECT duree,nbrp,titre from test";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Test( rs.getString("duree"),rs.getString("nbrp"),rs.getString("titre")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
public int modifier (Test c){
String sq13="UPDATE `test`SET `duree`=?,`nbrp`=?,`titre`=? WHERE duree =?";

            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, c.getDuree());
            pst.setString(2, c.getNbrp());
                        

            pst.setString(3, c.getTitre());
            pst.setString(4, c.getDuree());
                       

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }


public List<Test> ListClasse() {
        List<Test> Mylist = new ArrayList<>();
        try {
            String requete = "select * from test ORDER BY  titre ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
      ResultSet e = pst.executeQuery();
            while (e.next()) {
               Test pre = new Test();
             
            
            pre.setDuree(e.getString("duree"));
            pre.setNbrp(e.getString("nbrp"));
            pre.setTitre(e.getString("titre"));
            
            
            
           
           
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
 


}

