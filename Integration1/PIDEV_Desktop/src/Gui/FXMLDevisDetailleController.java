/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLDevisDetailleController implements Initializable {

    @FXML
    private ImageView flecheprec;
    @FXML
    private Button btreserver;
    @FXML
    private Label idres;
    @FXML
    private Label user;
    @FXML
    private Label email;
    @FXML
    private Label dateres;
    @FXML
    private Label descevent;
    @FXML
    private Label datedeb;
    @FXML
    private Label datefin;

    @FXML
    private Label nbpers;
    @FXML
    private Label pu;
    @FXML
    private Label total;
    Connection cnx =DataSource.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        try {
        
           ResultSet rs = cnx.createStatement().executeQuery("SELECT * From Reservation where id= (Select max(id) from `reservation`)");
           if(rs.next())
           {
            idres.setText(rs.getString("id"));
            dateres.setText(rs.getString("dateres"));
            nbpers.setText(rs.getString("nb_personne"));
           }
                       
           ResultSet rsuser = cnx.createStatement().executeQuery("SELECT * From user where id="+rs.getInt(3));
           if(rsuser.next())
           {
           user.setText(rsuser.getString("username"));
           email.setText(rsuser.getString("email"));
           }
            ResultSet rseven = cnx.createStatement().executeQuery("SELECT * From evenement where id="+rs.getInt(2));
           if(rseven.next())
           {
           descevent.setText(rseven.getString("description_evenement"));
           datedeb.setText(rseven.getString("date_debut"));
           datefin.setText(rseven.getString("date_fin"));
           pu.setText(rseven.getString("prix_evenement"));
          
           }
             Integer t=rseven.getInt(2)*rs.getInt(5);
             total.setText(t.toString());
             
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDevisDetailleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

     @FXML
     public void precedent() throws IOException{
    
     flecheprec.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLReservationEvenement.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    
    @FXML
     public void reserver() throws IOException{
    
     btreserver.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLReservationEvenement.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
}
