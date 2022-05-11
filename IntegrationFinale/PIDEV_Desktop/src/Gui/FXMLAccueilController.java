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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLAccueilController implements Initializable {

    @FXML
    private Button btfront;
    @FXML
    private Button btback;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
         
   @FXML
     public void Afficherfront() throws IOException{
    
     btfront.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLaccueilfront.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
        @FXML
     public void AfficherBack() throws IOException{
    
     btback.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLback.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }





}
