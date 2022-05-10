/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.test;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class FxMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/GestionQuestion.fxml"));  //charger les fichiers fxml
                    Scene scene = new Scene(root);
                    
                    primaryStage.setTitle("Ajouter abonnement !");
                    primaryStage.setScene(scene);
                    primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
