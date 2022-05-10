/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;
import edu.esprit.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import edu.esprit.tools.MaConnexion;
import java.security.NoSuchAlgorithmException;  
import java.security.MessageDigest;  
/**
 *
 * @author LENOVO
 */
public class UserService {
       Connection cnx;

    public UserService() {
        cnx=MaConnexion.getInstance().getCnx();
    } 
    public void ajouterUser(User u){
        String encryptedpassword = null;  
                try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(u.getPassword().getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        } 
       String query ="insert into user(email,password,etat,roles) values('"+u.getEmail()+"','"+encryptedpassword+"','"+u.getEtat()+"','"+u.getRoles()+"')";

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
    public void ModifyUser(User u){
        String encryptedpassword = null;  
                try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(u.getPassword().getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }         
        try {
           String query = "UPDATE user SET  email=? ,password=? ,roles=? ,etat=? WHERE id=?"; 
           PreparedStatement ste ;
           ste = cnx.prepareStatement(query);
                ste.setString(1,u.getEmail());
                ste.setString(2,encryptedpassword);
                ste.setString(3,u.getRoles());
                ste.setString(4,u.getEtat());
                ste.setInt(5,u.getId());
                
           
           ste.execute();
                       System.out.println("User with id:"+u.getId()+" modified");
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
