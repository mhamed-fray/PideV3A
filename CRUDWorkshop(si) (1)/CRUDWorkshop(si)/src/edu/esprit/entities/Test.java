/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;



/**
 *
 * @author HP
 */
public class Test {
    
     private int id;
     private String duree;
     private String nbrp;
     private String titre;
     private int sujet_id;
     
public Test() {
                }
public Test(int id, String duree, String nbrp, String titre)
    {
        this.id = id;
        this.duree =duree;
        this.nbrp = nbrp;
         this.titre = titre;

    }
public Test(int id, String duree, String nbrp, String titre,int sujet_id)
    {
        this.id = id;
        this.duree =duree;   
        this.nbrp = nbrp;
         this.titre = titre;
         this.sujet_id=sujet_id;

    }


    public Test( String duree, String nbrp, String titre)
    {
        this.duree =duree;
        this.nbrp = nbrp;
         this.titre = titre;
    }
    public Test( String duree, String nbrp, String titre,int sujet_id)
    {
        this.duree =duree;
        this.nbrp = nbrp;
         this.titre = titre;
         this.sujet_id =sujet_id;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getNbrp() {
        return nbrp;
    }

    public void setNbrp(String nbrp) {
        this.nbrp =nbrp;
    }
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre =titre;
    }
    public void setSujet_id(int sujet_id) {
        this.sujet_id = sujet_id;
    }

    public int getSujet_id() {
        return sujet_id;}

    @Override
    public String toString() {
        return "Test{" + "id=" + id + ", duree=" + duree + ", nbrp=" + nbrp + ", titre=" + titre + ", sujet_id=" + sujet_id + '}';
    }

    

    
    
}
    
    
    

                
