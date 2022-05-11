/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Evenement;
import Entities.Reservation;
import services.EvenementServiceR;
import services.ReservationService;
import utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ReservationEvenementFXMLController implements Initializable {

    @FXML
    private Button breserver;
    @FXML
    private TextField nbpers;
    @FXML
    private ImageView fleche;
    @FXML
    private ComboBox comboEven;   
    @FXML
    private Button btnreserver;      
    @FXML
    private Label labcons;
    
    Connection con;

    Stage stage ;
    ObservableList<Reservation> listeres = FXCollections.observableArrayList();    
    ObservableList<Evenement> listeeven = FXCollections.observableArrayList();    

    ReservationService Sres=new ReservationService();
    EvenementServiceR Seven=new EvenementServiceR();
    Connection cnx =DataSource.getInstance().getCnx();
    Integer nombreplace;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       //remplir le combobox avec les description des évènements disponibles
        try {
            ObservableList<String> options = FXCollections.observableArrayList();
           
            ResultSet rs = cnx.createStatement().executeQuery("SELECT id,description_evenement from `evenement`");
            while(rs.next()){
                options.add(rs.getString("id")+":"+rs.getString("description_evenement"));
            }
            comboEven.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
     @FXML
     public void precedent() throws IOException{
    
     fleche.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLAccueil.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
        @FXML
     public void reserver() throws IOException{
    
     labcons.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLReservationEvenement.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
     @FXML
     public void consuler() throws IOException{
    
     btnreserver.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLlistereservUser.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
     @FXML   
    private void ajouter(ActionEvent event) throws IOException{
          String s;
        ReservationService rs=new ReservationService();
        if(comboEven.getSelectionModel().isEmpty())
                s="";
        else
                s = comboEven.getSelectionModel().getSelectedItem().toString();
        
               
            boolean n=true;
            String ch=nbpers.getText().toUpperCase();
                for(int i=0;i<ch.length();i++)
                    if((ch.charAt(i)<'0')||(ch.charAt(i)>'9'))
                          n=false;
                
           if ((nbpers.getText().length()==0))
            {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Attention");
                 alert.setContentText("Veuiller saisir le nombre de personne");
                 alert.show();
            } 
        else 
            if(n==false)    
            {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Attention");
                 alert.setContentText("Veuiller saisir un nombre positif");
                 alert.show();
            }   
 
            else if(s.length()==0)
            {    Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Attention");
                 alert.setContentText("Veuiller choisir un évènement");
                 alert.show();
            }
           else
                {
         String evenid=s.substring(0,s.indexOf(":"));
        int idev =(int) Float.parseFloat(evenid);
        Reservation res = new Reservation(0,Integer.parseInt(nbpers.getText()),Date.valueOf(LocalDate.now()),0,idev,69,0);
        try {

            ResultSet rs2 = cnx.createStatement().executeQuery("SELECT nbre_places from `evenement`where id='"+idev+"'");
            if (rs2.next())
                nombreplace=rs2.getInt(1);
             
            if(nombreplace>=Integer.parseInt(nbpers.getText()))        
                 { rs.ajouter(res);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Succès");
                   alert.setContentText("Résevation et devis ajoutés");
                   Optional<ButtonType> option = alert.showAndWait();

                   if (option.get() == ButtonType.OK) 
                   {
                      breserver.getScene().getWindow().hide();
                      Parent root = FXMLLoader.load(getClass().getResource("FXMLDevisDetaille.fxml"));
                      Stage mainStage = new Stage();
                      Scene scene = new Scene(root);
                      mainStage.setScene(scene);
                      mainStage.show();}
                   }
               
                     else 
                   { 
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Echec");
                     alert.setContentText("Désolé cher client, il ne reste que "+nombreplace+" places pour cet évènement");
                     alert.show();
                   }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            }
    }
}
    