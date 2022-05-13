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
import models.canditab;

/**
 * FXML Controller class
 *
 * @author nidhal
 */
public class ConsultercandController implements Initializable {

    @FXML
    private JFXTextField nomfld;
    @FXML
    private JFXTextField prenomfld;
    @FXML
    private JFXTextField emailfld;
    @FXML
    private JFXTextField messagefld;
    

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    canditab canditab = null;
    private boolean update;
    int candid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void accepter(MouseEvent event) {

       

    }
    
    @FXML
    private void refuser(MouseEvent event) {

       

    }

    

    

    

    void setTextField(String nom, String prenom, String email, String message) {

        
        nomfld.setText(nom);
        prenomfld.setText(prenom);
        emailfld.setText(email);
                messagefld.setText(message);



    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}
