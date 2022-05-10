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
public class Abonnement {
     private int id;
     private String nom,description,cout;
     private type type_id;
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

    public Abonnement(int id, String cout, String nom, String description, type type_id, Likes likes_id) {
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

    public type getType_id() {
        return type_id;
    }

    public void setLikes_id(Likes likes_id) {
        this.likes_id = likes_id;
    }

    public void setType_id(type type_id) {
        this.type_id = type_id;
    }
    
    public Integer getCoutAsInt() {
        return Integer.parseInt(cout);
    }
    
    
    
}
