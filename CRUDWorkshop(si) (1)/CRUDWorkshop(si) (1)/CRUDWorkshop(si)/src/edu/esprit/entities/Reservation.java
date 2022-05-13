/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author amarz
 */
public class Reservation {
    
    private int id;
    private int id_eve;
    private int nbplace;

    public Reservation() {
    }

    public Reservation(int id, int id_eve, int nbplace) {
        this.id = id;
        this.id_eve = id_eve;
        this.nbplace = nbplace;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_eve() {
        return id_eve;
    }

    public void setId_eve(int id_eve) {
        this.id_eve = id_eve;
    }

    public int getNbplace() {
        return nbplace;
    }

    public void setNbplace(int nbplace) {
        this.nbplace = nbplace;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", id_eve=" + id_eve + ", nbplace=" + nbplace + '}';
    }
    
    
    
    
    
    
}
