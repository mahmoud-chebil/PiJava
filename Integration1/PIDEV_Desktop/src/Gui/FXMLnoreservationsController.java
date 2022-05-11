/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reservation;
import services.ReservationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLnoreservationsController implements Initializable {

    @FXML
    private ImageView flecheprec;
    @FXML
    private Button btreservations;
    @FXML
    private Button btndevis;
   ObservableList<Reservation> listeres = FXCollections.observableArrayList(); 
    ReservationService rs=new ReservationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
     public void precedent() throws IOException{
    
     flecheprec.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLback.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void Allreservations(ActionEvent event) throws IOException{
        try{ 
            listeres = rs.afficher();
            if(listeres.isEmpty())
            {
                 btreservations.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLnoreservations.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
            else
            {              
               btreservations.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLListerReservation.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
                 }   catch (SQLException ex) { 
            Logger.getLogger(FXMLListerReservationController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void Alldevis(ActionEvent event) {
    }
    
}
