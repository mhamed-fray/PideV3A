/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Abonnement;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface IAbonnementServices {
//public void ModifierTrip(Trip p);
         public List<Abonnement> afficher1();
         public void modifierAbonnement(String Description, int id);
        public int modifierL (Abonnement c);
}
