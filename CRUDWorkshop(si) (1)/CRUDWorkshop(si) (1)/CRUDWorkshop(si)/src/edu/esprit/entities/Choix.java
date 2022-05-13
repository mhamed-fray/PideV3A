/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author Moez
 */
public class Choix {
    
    private int id;
    private String contenu;
    private int etatchoix;
    private int checked;
    private Question question_ch;

    public Choix() {
    }

    public Choix(int id, String contenu, int etatchoix) {
        this.id = id;
        this.contenu = contenu;
        this.etatchoix = etatchoix;
    }

    public Choix(int id, String contenu, int etatchoix, int checked, Question question_ch) {
        this.id = id;
        this.contenu = contenu;
        this.etatchoix = etatchoix;
        this.checked = checked;
        this.question_ch = question_ch;
    }
    
    public Choix(String contenu, int etatchoix, int checked, Question question_ch) {
        this.id = id;
        this.contenu = contenu;
        this.etatchoix = etatchoix;
        this.checked = checked;
        this.question_ch = question_ch;
    }
    
    
    public Choix(int id,int etatchoix, int checked) {
        this.id = id;
        this.etatchoix = etatchoix;
        this.checked = checked;
    }
    

    public Choix(String contenu,Question question_ch) {
        this.contenu = contenu;
        this.etatchoix = etatchoix;
        this.question_ch = question_ch;
    }
    
    
    
    public Choix(String contenu, int etatchoix) {
        this.contenu = contenu;
        this.etatchoix = etatchoix;
    }
    
    public Choix(int id,String contenu) {
        this.id = id;
        this.contenu = contenu;
    }

    public String getContenu() {
        return contenu;
    }

    public int getEtatchoix() {
        return etatchoix;
    }

    public int getId() {
        return id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setEtatchoix(int etatchoix) {
        this.etatchoix = etatchoix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }
    
    
    
    
    
    
    
}
