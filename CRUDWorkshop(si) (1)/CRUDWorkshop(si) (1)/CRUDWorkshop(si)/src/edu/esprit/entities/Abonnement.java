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
public class Abonnement implements Comparable<Abonnement>{
     private int id,likes;
     private String nom,description,cout;
     private String type_id,user_id;
     private Likes likes_id;
     

    public Abonnement() {
    }
    

    public Abonnement(int id, String cout, String nom, String description) {
        this.id = id;
        this.cout = cout;
        this.nom = nom;
        this.description = description;
    }
    public Abonnement( String nom, String description,String cout) {
       
        this.cout = cout;
        this.nom = nom;
        this.description = description;
    }
    public Abonnement( String nom, String description,String cout,String type_id) {
       
        this.cout = cout;
        this.nom = nom;
        this.description = description;
        this.type_id = type_id; 
    }
      public Abonnement( String nom, String description,String cout,String type_id,String user_id) {
       
        this.cout = cout;
        this.nom = nom;
        this.description = description;
        this.type_id = type_id; 
         this.user_id = user_id; 
    }
      public Abonnement( String nom, String description,String cout,String type_id,String user_id,int likes) {
       
        this.cout = cout;
        this.nom = nom;
        this.description = description;
        this.type_id = type_id; 
         this.user_id = user_id; 
         this.likes = likes;
    }


    public Abonnement(int id, String cout, String nom, String description, String type_id, Likes likes_id) {
        this.id = id;
        this.cout = cout;
        this.nom = nom;
        this.description = description;
        this.type_id = type_id;
        this.likes_id = likes_id;
    }

    public Abonnement(String nom, String description) {
        this.nom=nom;
        this.description=description;
    }

   
    

    public String getCout() {
        return cout;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setCout(String cout) {
        this.cout = cout;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", nom=" + nom + ", description=" + description +", cout=" + cout +", type_id=" + type_id +", likes_id=" + likes_id + '}';
    }

    public Likes getLikes_id() {
        return likes_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setLikes_id(Likes likes_id) {
        this.likes_id = likes_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
    
    public Integer getCoutAsInt() {
        return Integer.parseInt(cout);
    }
   
    
     public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
      public String getUser_id() {
        return user_id;
    }

    @Override
    public int compareTo(Abonnement o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes){
        this.likes = likes;
    }
    
    
    
}
