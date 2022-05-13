/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author LENOVO
 */
public class Offre {
    private int id;
    private String titre,secteur,description,localisation;
    private double salaire;
    

    public Offre(int id, String titre, String secteur, String description, String localisation, double salaire) {
        this.id = id;
        this.titre = titre;
        this.secteur = secteur;
        this.description = description;
        this.localisation = localisation;
        this.salaire = salaire;
    }
      public Offre(String titre, String secteur, String description, String localisation, double salaire) {
       
        this.titre = titre;
        this.secteur = secteur;
        this.description = description;
        this.localisation = localisation;
        this.salaire = salaire;
    }

    public Offre() {
    }

    public Offre(String titre, String secteur, String description, String localisation, String salaire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", titre=" + titre + ", secteur=" + secteur + ", description=" + description + ", localisation=" + localisation + ", salaire=" + salaire + '}';
    }

    public String valueOf(double salaire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
