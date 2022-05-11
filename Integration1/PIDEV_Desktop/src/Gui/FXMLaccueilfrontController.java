/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import tests.MainGUI;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLaccueilfrontController implements Initializable {

    @FXML
    private ImageView flecheprec;
    @FXML
    private Button btreservation;
    @FXML
    private Button listeeven;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void precedent(MouseEvent event) throws IOException {
         flecheprec.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLAccueil.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void listeevenement(ActionEvent event) throws IOException {
          listeeven.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("ListeEvenement.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void reserver(ActionEvent event) throws IOException {
        btreservation.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLReservationEvenement.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
        @FXML
    private void ShowMarket(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/product/market.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
        
        
             /*  // btn_stat.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("market.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                MainGUI.pStage.show();*/
    }

}
