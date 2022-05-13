/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.io.FileInputStream;
import java.sql.Date;


/**
 *
 * @author amarz
 */
public class Evenement {
     private int id;
    private String titre;
    private String description;
    private String localisation;
    private int nb_participants;
   private Date dat;
   // private FileInputStream fis;
   
   
    public Evenement(){
        
       
    }
  
   
        public Evenement(int id,String titre,String description,String localisation,int nb_participants,Date date ) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.nb_participants = nb_participants;
        this.dat=date;
    }
        public Evenement(int id,String titre,Date date,String description,String localisation,int nb_participants ) {
        this.id = id;
        this.titre = titre;
        this.dat=date;
        this.description = description;
        this.localisation = localisation;
        this.nb_participants = nb_participants;
        
    }

   
  
    public Date getDate() {
        return dat;
    }

    public void setDate(Date date) {
        this.dat = date;
    }

    public int getId() {
        return this.id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return this.localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    public int getNbp() {
        return this.nb_participants;
    }
        public void setNbp(int nbp) {
        this.nb_participants = nbp;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", localisation=" + localisation + ", nb_participants=" + nb_participants + ", dat=" + dat + '}';
    }

   
}

