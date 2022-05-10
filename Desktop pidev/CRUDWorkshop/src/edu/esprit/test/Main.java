/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.test;

import edu.esprit.entities.Abonnement;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Offre;
import edu.esprit.entities.Question;
import edu.esprit.entities.type;
import edu.esprit.services.AbonnementService;
import edu.esprit.services.TypeService;

import edu.esprit.entities.Test;
import edu.esprit.entities.User;
import edu.esprit.services.EvenementService;
import edu.esprit.services.OffreService;
import edu.esprit.services.QuestionService;

import edu.esprit.services.TestService;
import edu.esprit.services.UserService;

/**
 *
 * @author LENOVO
 */
public class Main {
     public static void main(String[] args) {
      Abonnement abo  = new Abonnement("VIP","ceci est description",200);
      AbonnementService as = new AbonnementService();
      //ajout
       as.ajouterAbonnement(abo);
       //affiche
        //  System.out.println( as.afficherAbonnement());
        //as.modifierAbonnement("description",25);
        //as.supprimerAbonnement(35);
        
        //test
        type t  = new type("normalllll");
      TypeService ts = new TypeService();
      //ajout
       ts.ajouterType(t);
      //affiche
          System.out.println( ts.afficherType());
          
          Test p = new Test("Ben Ali", "Mohamed","fff");
        TestService ps = new TestService();
        ps.ajouterTest(p);
        
        System.out.println(ps.afficherTest());;
       // ps.modifierTest("m",1);
        ps.supprimerTest(2);
        
        //Question
        Question q = new Question("question suivie");
        QuestionService qs = new QuestionService();
        qs.ajouterQuestion(q);
        
        System.out.println(ps.afficherTest());;
        //qs.modifierQuestion("question modifier",144);
       // ps.supprimerQuestion(2);
       
       //User
           User user = new User("sisioooo@gmail.com","15482","enable","ROLE_TESTTAKER");
        UserService us = new UserService();
      us.ajouterUser(user);
           System.out.println("helo");
       // user = new User("javafx@yahoo.com","15482","enable","ROLE_TESTTAKER");
     //user.setEmail("yessssssssss");
     //user.setPassword("dddd");
        //us.ModifyUser(user);
    
     
     //Evenement
        Evenement e  = new Evenement("formation","ceci est description","technopole ghazzela b14",0);
        EvenementService eve = new EvenementService();
        
        eve.ajouterEvenement(e);
           //affiche
          System.out.println( eve.afficherEvenement());
        
        //eve.supprimerEvenement(35);
        
        //Offre
        
        //os.supprimerOffre(1);
        
}}
     
