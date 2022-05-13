/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Abonnement;
import edu.esprit.entities.User;

/**
 *
 * @author LENOVO
 */
public interface PaymentInterface {
     public String createCustomer(User user);
     public void chargeCreditCard(Abonnement abonnement);
}
