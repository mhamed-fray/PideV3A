/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Helper;
import edu.esprit.entities.Question;
import edu.esprit.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import edu.esprit.tools.MaConnexion;
import java.security.NoSuchAlgorithmException;  
import java.security.MessageDigest;  
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
/**
 *
 * @author LENOVO
 */
public class UserService {
       Connection cnx;
String role ="";
    public UserService() {
        cnx=MaConnexion.getInstance().getCnx();
    } 
    public void registeruser(User u){
        String encryptedpassword = null;  
        
           Argon2 argon2 = Argon2Factory.create(
            Argon2Factory.Argon2Types.ARGON2id,
            16,
            32);
         encryptedpassword = argon2.hash(4, 65536, 1, u.getPassword());   

       String query ="insert into user(email,password,roles,etat) values('"+u.getEmail()+"','"+encryptedpassword+"','"+"[\"ROLE_TESTTAKER\"]"+"','"+"enable"+"')";

            Statement ste;
        try {
            ste = cnx.createStatement();
             ste.executeUpdate(query);
             System.out.println(query);
            System.out.println("User Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void ajouterUser(User u){
          
        String encryptedpassword = null;  
        
           Argon2 argon2 = Argon2Factory.create(
            Argon2Factory.Argon2Types.ARGON2id,
            16,
            32);
         encryptedpassword = argon2.hash(4, 65536, 1, u.getPassword());   
if(u.getRoles() =="TestTaker"){
     role = "[\"ROLE_TESTTAKER\"]";
}else if (u.getRoles() =="TestMaker"){
    role = "[\"ROLE_TESTMAKER\"]";
}else{
   role = "[\"ROLE_ENTREPRISE\"]";
     }
       String query ="insert into user(email,password,roles,etat) values('"+u.getEmail()+"','"+encryptedpassword+"','"+role+"','"+u.getEtat()+"')";

            Statement ste;
        try {
            ste = cnx.createStatement();
             ste.executeUpdate(query);
             System.out.println(query);
            System.out.println("User Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
     public void deleteById(int id) {
        try {
            String query = "DELETE FROM user WHERE id=?";
            PreparedStatement ste;
            ste = cnx.prepareStatement(query);
            ste.setInt(1, id);
             ste.execute();
             System.out.println("eeee"+query);
            System.out.println("User with id:"+id+" Deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
      public void Supprimeruser(String email){
       
       String requete = "delete from user where email=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,email);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
      public String getPasswordByUsername(String email) {
        String sql = "select * from user where email=?";
        try {
           PreparedStatement st = cnx.prepareStatement(sql);
           st.setString(1, email);
           ResultSet rs = st.executeQuery();
             System.out.println("queryyyy : "+rs);
            rs.beforeFirst();
            if (rs.next()) {
          
                return rs.getString("password");
                  
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }
       public String getRole(String email) {
        String sql = "select * from user where email=?";
        try {
           PreparedStatement st = cnx.prepareStatement(sql);
           st.setString(1, email);
           ResultSet rs = st.executeQuery();
             System.out.println("queryyyy : "+rs);
            rs.beforeFirst();
            if (rs.next()) {
          
                return rs.getString("roles");
                  
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }
     public void saveNewPassword(String email , String password){
           String encryptedpassword = null;  
           Argon2 argon2 = Argon2Factory.create(
            Argon2Factory.Argon2Types.ARGON2id,
            16,
            32);
         encryptedpassword = argon2.hash(4, 65536, 1, password);     
                   try {
            
           String query = "UPDATE user SET  password=? WHERE email=?"; 
           PreparedStatement ste ;
           ste = cnx.prepareStatement(query);
                ste.setString(1,encryptedpassword);
                ste.setString(2,email);

     ste.executeUpdate();
          System.out.println("New password is saved successfuly " + ste);
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
     }
    public void ModifyUser(User u , String email){
           
     
        String encryptedpassword = null;  
           Argon2 argon2 = Argon2Factory.create(
            Argon2Factory.Argon2Types.ARGON2id,
            16,
            32);
         encryptedpassword = argon2.hash(4, 65536, 1, u.getPassword());     
                   try {
if(u.getRoles() =="TestTaker"){
     role = "[\"ROLE_TESTTAKER\"]";
}else if (u.getRoles() =="TestMaker"){
    role = "[\"ROLE_TESTMAKER\"]";
}else{
   role = "[\"ROLE_ENTREPRISE\"]";
     }            
           String query = "UPDATE user SET  email=?  ,password=? ,etat=? ,roles=?  WHERE email=?"; 
           PreparedStatement ste ;
           ste = cnx.prepareStatement(query);
                ste.setString(1,u.getEmail());
                ste.setString(2,encryptedpassword);
                ste.setString(3,u.getEtat());
                ste.setString(4,role);
                ste.setString(5,email);
                  System.out.println(ste);
     ste.executeUpdate();
                       System.out.println("User with email:"+u.getEmail()+" modified");
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
             
      }
public boolean FindUserbyEmail( String email){
    String sql = "select * from user where email=?";
    try {
           PreparedStatement st = cnx.prepareStatement(sql);
           st.setString(1, email);
           ResultSet rs = st.executeQuery();
  
            rs.beforeFirst();
            if (rs.next()) {
          
              return true;
                  
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return false ;
}
}
