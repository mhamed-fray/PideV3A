/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.tools.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AbonnementStatController implements Initializable {
    
     @FXML
    private BarChart<String, Number> stat;

    @FXML
    private CategoryAxis tyabo;

    @FXML
    private NumberAxis nbrabo;
    Connection cnx = MaConnexion.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
             stati();
             /*try {
             ResultSet rs = cnx.createStatement().executeQuery("SELECT COUNT(abonnement.id) FROM abonnement INNER JOIN abonnement.type_id=type.id GROUP BY type");
             Number val =  ((Number) rs.getObject(1)).intValue();
             ResultSet rs1=cnx.createStatement().executeQuery("SELECT type FROM type");
             XYChart.Series<String,Number>series=new XYChart.Series<>();
             series.setName("Nombre d'abonnement par type");
             series.getData().add(new XYChart.Data<>("type1",val));
             series.getData().add(new XYChart.Data<>("type2",val));
             series.getData().add(new XYChart.Data<>("type3",val));
             stat.getData().add(series);
             // TODO
             } catch (SQLException ex) {
             Logger.getLogger(AbonnementStatController.class.getName()).log(Level.SEVERE, null, ex);
             }*/
         } catch (SQLException ex) {
             Logger.getLogger(AbonnementStatController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }
public void stati()throws SQLException{
    PreparedStatement pst;
        String query = "SELECT COUNT(abonnement.id),type FROM abonnement INNER JOIN type ON abonnement.type_id=type.id GROUP BY type";
        pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        ArrayList<Number>nbr=new ArrayList<>();
        ArrayList<String>ty=new ArrayList<>();
        while (rs.next()){
        nbr.add(rs.getDouble(1));
        ty.add(rs.getString(2));
        }
        rs.close();
        //CategoryAxies xAxies= new CategoryAxies();  
        XYChart.Series dataSeries1=new XYChart.Series();
         dataSeries1.setName("nombre d'abonnement de chaque type");
         for(int i=0;i<ty.size();i++){
         dataSeries1.getData().add(new XYChart.Data(ty.get(i),nbr.get(i)));
         }
         stat.getData().add(dataSeries1);
 }
    
}
