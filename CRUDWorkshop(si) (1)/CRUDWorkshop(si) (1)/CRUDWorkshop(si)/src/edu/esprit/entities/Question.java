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
public class Question {
     private int id;
    private String contenu;

    public Question() {
    }

    public Question(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
    }
    
    public Question(String contenu) {
        
        this.contenu = contenu;
    }

    public String getContenu() {
        return contenu;
    }

    public int getId() {
        return id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setId(int id) {
        this.id = id;
    }

}
