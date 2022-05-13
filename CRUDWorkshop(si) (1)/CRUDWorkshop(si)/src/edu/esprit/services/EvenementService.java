/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;
import edu.esprit.entities.Evenement;
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
public class EvenementService {
      Connection cnx;

    public EvenementService() {
        cnx=MaConnexion.getInstance().getCnx();
    }

   
    public void ajouterEvenement(Evenement ev){
        String query = "insert into evenement(id,titre,description,localisation,nb_participants) values('"+ev.getId()+"','"+ev.getTitre()+"','"+ev.getDescription()+"','"+ev.getLocalisation()+"','"+ev.getNbp()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Evenement Ajoute");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Evenement> afficherEvenement(){
      List<Evenement> evenements = new ArrayList<>();
      String sql = "select * from evenement";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()){
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setTitre(rs.getString(2));
                e.setDescription(rs.getString("description"));
                e.setLocalisation(rs.getString("localisation"));
                e.setNbp(rs.getInt("nbp"));
                //a.setType_id(type_id);
                evenements.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
      return evenements;
     
}
   
    public void modifierEvenement(String localisation,String Description, int id, int nbp){
         String requete="UPDATE abonnement SET Description='"+Description+"' localisation='"+localisation+"' where ID = '"+id+"'";
         

         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
         
           
            System.out.println("evenement modifié");
        } catch (SQLException ex) {
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
 public void supprimerEvenement(int id) {
       
       
            String requete = "delete from evenement where id=?";
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
