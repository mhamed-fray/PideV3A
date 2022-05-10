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
public class Test {
     private int id;
    private String datedebut;
     private String datefin;
     private String titre;

public Test() {
                }
public Test(int id, String datedebut, String datefin, String titre)
    {
        this.id = id;
        this.datedebut =datedebut;
        this.datefin = datefin;
         this.titre = titre;

    }


    public Test(String datedebut, String datefin,String titre) 
    {
        this.datedebut =datedebut;
        this.datefin = datefin;
         this.titre = titre;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String Datedebut) {
        this.datedebut = Datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin =datefin;
    }
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre =titre;
    }

    @Override
    public String toString() {
        return "Test{" + "id=" + id + ", datedebut=" + datedebut + ", datefin=" + datefin + ", titre=" + titre + '}';
    }
    
}
