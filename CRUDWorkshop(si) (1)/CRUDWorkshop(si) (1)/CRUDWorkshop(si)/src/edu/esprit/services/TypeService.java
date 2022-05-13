/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Abonnement;
import edu.esprit.entities.type;
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
public class TypeService {
    Connection cnx;

    public TypeService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterType(type t){
        String query = "insert into type(type) values('"+t.getType()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Type Ajoute");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<type> afficherType(){
      List<type> types = new ArrayList<>();
      String sql = "select * from type";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()){
                type t = new type();
                t.setId(rs.getInt("id"));
                t.setType(rs.getString(2));
                types.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
      return types;
      
}
    
     public void modifiertype(String type, int id){
         String requete="UPDATE type SET type='"+type+"' where ID = '"+id+"'";
         

         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
          
           
            System.out.println("type modifié");
        } catch (SQLException ex) {
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
      public int modifier (type c){
String sq1="UPDATE `type`SET `type`=? WHERE type =?";

            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq1);
            pst.setString(1, c.getType());
            pst.setString(2, c.getType());
            
                       

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(TypeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }
    
    
}
