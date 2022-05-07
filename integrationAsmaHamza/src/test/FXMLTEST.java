/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author xDrais
 */
public class FXMLTEST extends Application {

  Parent parent ;
    Stage stage ;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            this.stage = primaryStage ;
            parent =FXMLLoader.load(getClass().getResource("/Gui/FXMLAccueil.fxml"));
            Scene scene =new Scene(parent);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
        Logger.getLogger(FXMLTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
