/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafixauth;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Hazem
 */
public class JavaFixAuth extends Application {
    public static int loggedInID;
    public static int RestpasswordID;
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
//ReclamationAcceuilControlle
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
     
     
        
        
//        User d =new User("ay","+21699999999","fares@e.e","faresmekni","image");
//        User f =new User(5,"dude","+21611111111","b@b.b","faresmekni","image");
//        ServiceUser serv = new ServiceUser();
        //serv.ajouter(d);
        //System.out.println(serv.getAll());
        //System.out.println(serv.getById(5));
        //serv.modifier(f);
//        serv.supprimer(f);
        
        
    }
    
}
