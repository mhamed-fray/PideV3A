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
public class Evenement {
      private int id;
    private String titre;
    private String description;
    private String localisation;
    private int nb_participants;
   
   
    public Evenement(){
       
    }
    public Evenement(String titre,String description,String localisation,int nb_participants ) {
       
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.nb_participants = nb_participants;
    }
   
        public Evenement(int id,String titre,String description,String localisation,int nb_participants ) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.nb_participants = nb_participants;
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
}
