/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import edu.esprit.entities.Abonnement;
import edu.esprit.entities.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class PaymentService {
    
     private static final String TEST_STRIPE_SECRET_KEY = "sk_test_51KYFffJhbqELi4FTGEfUDtM3CMIOpLovdOV738NQrcU6LhBrZO8FnlKardhECxipf5x3UL9ukE0DOfbosA0rOlvt00nWbGeTDF";

  public PaymentService() {
    Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
  }

  
    public String createCustomer(User user) {
        Map<String, Object> customerParams = new HashMap<String, Object>();
    customerParams.put("description", 
      "blalalalaal" + " " + "ben fouleen");
	customerParams.put("email", "foulen@foulen.com");
		
	String id = null;
		
	try { 
      // Create customer
	  Customer stripeCustomer = Customer.create(customerParams);
	  id = stripeCustomer.getId();
	  System.out.println(stripeCustomer);
	} catch (CardException e) {
	  // Transaction failure
	} catch (RateLimitException e) {
	  // Too many requests made to the API too quickly
	} catch (InvalidRequestException e) {
	  // Invalid parameters were supplied to Stripe's API
	}catch (AuthenticationException e) {
	  // Authentication with Stripe's API failed (wrong API key?)
	}
         // Network communication with Stripe failed
         catch (StripeException e) {
	  // Generic error
	}
         // Something else happened unrelated to Stripe
	
    return id;	
  }
    

   
    
    public static void chargeCreditCard(Abonnement abonnement) {
        // Stripe requires the charge amount to be in cents
    int chargeAmountCents = (int) abonnement.getCoutAsInt() * 100;

    //User user = order.getUser();

	Map<String, Object> chargeParams = new HashMap<String, Object>();
	chargeParams.put("amount", chargeAmountCents);
	chargeParams.put("currency", "usd");
	chargeParams.put("description", "Monthly Charges");		
	chargeParams.put("customer", "1");
			
	try {
	  // Submit charge to credit card 
	  Charge charge = Charge.create(chargeParams);
      System.out.println(charge);
    } catch (CardException e) {
	  // Transaction was declined
	  System.out.println("Status is: " + e.getCode());
	  System.out.println("Message is: " + e.getMessage());
	} catch (RateLimitException e) {
	  // Too many requests made to the API too quickly
	} catch (InvalidRequestException e) {
	  // Invalid parameters were supplied to Stripe's API
    }catch (AuthenticationException e) {
	  // Authentication with Stripe's API failed (wrong API key?)
	}
         // Network communication with Stripe failed
          catch (StripeException e) {
	  // Generic error
	} catch (Exception e) {
	  // Something else happened unrelated to Stripe
	}	
  }
}
    

