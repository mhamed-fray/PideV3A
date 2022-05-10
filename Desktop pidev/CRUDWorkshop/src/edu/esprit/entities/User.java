/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author LENOVO
 */
public class User {
     private int id ;
    private String email ; 
    private String etat ; 
    private String password ;
    private String roles ;

    public User() {
    }

    public User(int id, String email, String etat, String password, String roles) {
        this.id = id;
        this.email = email;
        this.etat = etat;
        this.password = password;
        this.roles = roles;
    }
    
    public User(String email,String password, String etat ,String roles) {
       
        this.email = email;
        this.etat = etat;
        this.password = password;
        this.roles = roles;
       
    }

    public String getEmail() {
        return email;
    }

    public String getEtat() {
        return etat;
    }

    public String getRoles() {
        return roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
      return "User{" + "id=" + id + ", email=" + email + ", etat=" + etat + ", roles=" + roles + '}';
   
    }
}
