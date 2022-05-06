/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reservation;
import Entities.singletont;
import Utils.MyDB;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLDevisController implements Initializable {

    @FXML
    private AnchorPane btnvalidation;
    @FXML
    private ImageView flecheprec;
    @FXML
    private Button btreservations;
    @FXML
    private Button btndevis;
    @FXML
    private Button btnremise;
    @FXML
    private ComboBox combodevis;
    @FXML
    private Button btndetails;
    @FXML
    private Label details;
    Connection cnx =MyDB.getInstance().getCon();
    String s,descEvent,username;
    float ptot,remise,pu;
    int idres,nbpers,ideven,iduser,nombrereserv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            ObservableList<String> options = FXCollections.observableArrayList();
           
            ResultSet rs = cnx.createStatement().executeQuery("SELECT devis_id from `reservation` where etat='"+1+"'");
            while(rs.next()){
                options.add(rs.getString("devis_id"));
            }
            combodevis.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void precedent(MouseEvent event) throws IOException{
    
    flecheprec.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLback.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
}

    @FXML
    private void remise(ActionEvent event) throws IOException{
      
       
       if(combodevis.getSelectionModel().isEmpty())
                s="";
        else
                s = combodevis.getSelectionModel().getSelectedItem().toString();
        if(s.length()==0)
            {    Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Attention");
                 alert.setContentText("Veuiller choisir un numéro de devis");
                 alert.show();
            }
        else
        {
             
      try {
           Node node = (Node) event.getSource();
           Stage stage = (Stage) node.getScene().getWindow();
           stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLremise.fxml"));
 
            singletont holder=singletont.getInstance();
   
            holder.setIddev((int) Float.parseFloat(s));
   
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
          } catch (IOException e) {
           System.err.println(String.format("Error: %s", e.getMessage()));
       }}
    }

    @FXML
    private void detailDevis(ActionEvent event) throws IOException{
        if(combodevis.getSelectionModel().isEmpty())
                s="";
        else
                s = combodevis.getSelectionModel().getSelectedItem().toString();
        if(s.length()==0)
            {    Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Attention");
                 alert.setContentText("Veuiller choisir un numéro de devis");
                 alert.show();
            }
           else
                {
                     try {
                    int iddevis =(int) Float.parseFloat(s);
                     ResultSet rs = cnx.createStatement().executeQuery("SELECT * from `devis` where id='"+iddevis+"'");
                     if(rs.next())
                     {
                       ptot=rs.getFloat(3);
                       idres=rs.getInt(2);
                       remise=rs.getFloat(4);
                     }
                     ResultSet rs2 = cnx.createStatement().executeQuery("SELECT * from `reservation` where devis_id='"+iddevis+"'");
                   if(rs2.next())
                   {
                       nbpers=rs2.getInt(5);
                       ideven=rs2.getInt(2);
                       iduser=rs2.getInt(3);
                   }   
                      ResultSet rseven = cnx.createStatement().executeQuery("SELECT * From evenement where id="+ideven);
                     if(rseven.next())
                   {
                       descEvent=rseven.getString(3);
                       pu=rseven.getFloat(2);
                   } 
            
                      ResultSet user = cnx.createStatement().executeQuery("SELECT * From user where id="+iduser);
                     if(user.next())
                   {
                       username=user.getString(5);
                   } 
                     ResultSet nbreserv = cnx.createStatement().executeQuery("SELECT count(*) From reservation where user_id="+iduser);
                     if(nbreserv.next())
                   {
                       nombrereserv=nbreserv.getInt(1);
                   } 
            
 
                           details.setText("-------------------------------------------------------------\n Client : "+username+"         Nombre de réservation: "+nombrereserv+
                                   "\n Evènement de ce devis: "+descEvent+"\n Prix unitaire: "+pu+
                                   "DT \n Nombre de personnes: "+nbpers+"\n Prix total avant remise:"+(pu*nbpers)+
                                   "\n Remise: "+remise+" %"+"\n Prix Total après remise: "+ptot );
                     } catch (SQLException ex) {
            Logger.getLogger(ReservationEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
                
    }
}
