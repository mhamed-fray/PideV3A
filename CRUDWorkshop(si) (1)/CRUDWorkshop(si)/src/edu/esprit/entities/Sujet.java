/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author HP
 */
public class Sujet {

    public Sujet() {
    
    }

    @Override
    public String toString() {
        return "Sujet{" + "id=" + id + ", sujet=" + sujet + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public int getId() {
        return id;
    }

    public String getSujet() {
        return sujet;
    }

    public Sujet(String sujet) {
        this.sujet = sujet;
    }

    public Sujet(int id, String sujet) {
        this.id = id;
        this.sujet = sujet;
    }
    private int id;
    private String sujet;
    
}
