/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableView;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import helpers.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import models.Offre;
import service.OffreService;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class addOffreController implements Initializable {
     OffreService es=new OffreService() ;

    @FXML
    private JFXTextField titrefld;
    @FXML
    private JFXTextField secteurfld;
    @FXML
    private JFXTextField descriptionfld;
    @FXML
    private JFXTextField localisationfld;
    @FXML
    private JFXTextField salairefld;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Offre Offre = null;
    private boolean update;
    int offreid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
  
    @FXML
    private void save(MouseEvent event) {

        connection = DbConnect.getConnect();
        String titre = titrefld.getText();
         String secteur = secteurfld.getText();
       
        String description = descriptionfld.getText();
        String localisation = localisationfld.getText();
         Double salaire = Double.valueOf(salairefld.getText());


        if (titre.isEmpty() || secteur.isEmpty() || description.isEmpty()|| localisation.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
             Offre e=new Offre(titre,secteur,description,localisation,salaire);
             es.ajouterOffre(e);
             
             clean();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Offre Ajouté");
            alert.showAndWait();
        }

    }

    @FXML
    private void clean() {
        titrefld.setText(null);
        secteurfld.setText(null);
        descriptionfld.setText(null);
        localisationfld.setText(null);
        salairefld.setText(null);

        
    }
/*
    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `Offre`( `titre`, `secteur`, `description`, `localisation`, `salaire`) VALUES (?,?,?,?,?)";

        }else{
            query = "UPDATE `Offre` SET "
                    + "`titre`=?,"
                    + "`secteur`=?,"
                    + "`description`=?,"
                      + "`localisation`=?,"
                    + "`salaire`= ? WHERE id = '"+offreid+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, titrefld.getText());
            preparedStatement.setString(2, secteurfld.getText());
            preparedStatement.setString(3, descriptionfld.getText());
            preparedStatement.setString(4, localisationfld.getText());
            preparedStatement.setString(5, salairefld.getText());

           //preparedStatement.setString(2, String.valueOf(salairefld));

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
*/
    void setTextField(int id, String titre, String secteur, String description, String localisation,double salaire) {

        offreid = id;
        titrefld.setText(titre);
        secteurfld.setText(secteur);
        descriptionfld.setText(description);
                localisationfld.setText(localisation);

                salairefld.setText(String.valueOf(salaire));


    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}
