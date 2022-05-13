/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.test;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author LENOVO
 */
public class FxMain extends Application{
    public static final String CURRENCY = "$";
    @Override
    public void start(Stage primaryStage) throws Exception {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Backend.fxml"));  //charger les fichiers fxml
                    Scene scene = new Scene(root);
                    
                    primaryStage.setTitle("Ajouter User !");
                //    scene.setFill(Color.TRANSPARENT);
                 primaryStage.setScene(scene);
                //    primaryStage.initStyle(StageStyle.TRANSPARENT);
                    
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
