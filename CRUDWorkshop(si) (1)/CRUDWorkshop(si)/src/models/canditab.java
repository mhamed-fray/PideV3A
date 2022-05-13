/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author nidha
 */
public class canditab {
   private  int id,num;
   private  String nom,prenom,email,message;
   

    public canditab(String nom, String prenom, String email, String message,int num) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.message = message;
        this.num=num;
      
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    

    public canditab() {
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "canditab{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", message=" + message + '}';
    }

    public canditab(int id, String nom, String prenom, String email, String message) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.message = message;
    }
    
}
