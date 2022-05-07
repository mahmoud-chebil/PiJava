/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author month
 */
public class testFXMain extends Application {
    
   Parent parent ;
    Stage stage ;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            this.stage = primaryStage ;
                parent =FXMLLoader.load(getClass().getResource("/Gui/GestionProductsFXML.fxml"));
            Scene scene =new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("gestion Games");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(testFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
