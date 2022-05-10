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
 * @author LENOVO
 */
public class TestService {
     Connection cnx;
    public TestService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
 public void ajouterTest(Test p){
        String query ="insert into test(datedebut,datefin,titre) values('"+p.getDatedebut()+"','"+p.getDatefin()+"','"+p.getTitre()+"')";
        
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
        String sql="insert into test(datedebut,datefin,titre) values (?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getDatedebut());
            ste.setString(2, p.getDatefin());
            ste.setString(3, p.getTitre());
            ste.executeUpdate();
            System.out.println("Test Ajoutée !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 public List<Test> afficherTest(){
        List<Test> personnes = new ArrayList<>();
        String sql ="select * from test";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               Test p = new Test();
                p.setId(rs.getInt("id"));
                p.setDatedebut(rs.getString(2));
                p.setDatefin(rs.getString("datefin"));
                p.setTitre(rs.getString("titre"));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return personnes;
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
 public void supprimerTest(int id) {
       
       
            String requete = "delete from test where id=?";
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
