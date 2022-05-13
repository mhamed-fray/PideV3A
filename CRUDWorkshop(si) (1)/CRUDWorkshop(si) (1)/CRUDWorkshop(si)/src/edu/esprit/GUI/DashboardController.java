/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.scene.AmbientLight;
import javafx.scene.shape.Sphere;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Jamil
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  // set title for the stage
  Stage primaryStage = new Stage();
        PieChart pieChart = new PieChart();

        PieChart.Data slice1 = new PieChart.Data("Test Taker", 5);
        PieChart.Data slice2 = new PieChart.Data("Test Maker", 7);
        PieChart.Data slice3 = new PieChart.Data("Entreprise", 3);


        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);


        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setStartAngle(30);

        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 12 arial;");

        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());

                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        }

        primaryStage.setTitle("JavaFX PieChart (o7planning.org)");
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(pieChart, caption);
       
       

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
//   
//    Scene scene = new Scene(new Group());
//        stage.setTitle("Imported Fruits");
//      stage.setWidth(500);
//      stage.setHeight(500);
// 
//        ObservableList<PieChart.Data> pieChartData =
//                FXCollections.observableArrayList(
//                new PieChart.Data("Testtaker", 13),
//                new PieChart.Data("testmaker", 25),
//                new PieChart.Data("entrreprise", 10),
//                new PieChart.Data("Pears", 22),
//                new PieChart.Data("Apples", 30));
//        final PieChart chart = new PieChart(pieChartData);
//        chart.setTitle("Imported Fruits");
//final Label caption = new Label("");
//caption.setTextFill(Color.DARKORANGE);
//caption.setStyle("-fx-font: 24 arial;");
//
//for (final PieChart.Data data : chart.getData()) {
//    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
//        new EventHandler<MouseEvent>() {
//            @Override public void handle(MouseEvent e) {
//                caption.setTranslateX(e.getSceneX());
//                caption.setTranslateY(e.getSceneY());
//                caption.setText(String.valueOf(data.getPieValue()) + "%");
//             }
//        });
//}
//     ((Group) scene.getRoot()).getChildren().add(chart);
//  stage.setScene(scene);
//  stage.show();
//
//  
    }

}