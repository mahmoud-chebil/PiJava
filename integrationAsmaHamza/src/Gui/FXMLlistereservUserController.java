/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reservation;
import Entities.singletont;
import Services.EvenementServiceR;
import Services.ReservationService;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLlistereservUserController implements Initializable {

    @FXML
    private ImageView flecheprec;
    @FXML
    private Button btreservation;
    @FXML
    private TableView<Reservation> tabreserv;
    @FXML
    private TableColumn<Reservation, Integer> col_numres;
    @FXML
    private TableColumn<Reservation, Integer> col_desceven;
    @FXML
    private TableColumn<Reservation, Date> col_dateres;
    @FXML
    private TableColumn<Reservation, Integer> col_etat;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btndevis;

    Connection con;
    Statement stm;
     Connection cnx =MyDB.getInstance().getCon();
    Stage stage ;
    ObservableList<Reservation> listeres = FXCollections.observableArrayList();    
    ReservationService rs=new ReservationService();
    EvenementServiceR es=new EvenementServiceR();
    String desceven;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {

       listeres = rs.afficherByIdUser(1);
        col_numres.setCellValueFactory(new PropertyValueFactory<>("id"));    
        col_desceven.setCellValueFactory(new PropertyValueFactory<>("even"));
        col_dateres.setCellValueFactory(new PropertyValueFactory<>("dateres"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        tabreserv.getItems().addAll(listeres);

       
    }   catch (SQLException ex) { 
            Logger.getLogger(FXMLListerReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void supprimer(ActionEvent event) throws IOException , SQLException{
       Reservation selectedRes=tabreserv.getSelectionModel().getSelectedItem();
      if(tabreserv.getSelectionModel().getSelectedIndex()==-1)
                   JOptionPane.showMessageDialog(null, "veuillez choisir une réservation");
       else if (selectedRes.getEtat()==1)
       { Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Echec");
           alert.setContentText("Vous ne pouvez pas annuler une réservation validée");
           alert.show();
       }
      else
       {

       try{
           rs.Delete(selectedRes.getId());
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Succès");
           alert.setContentText("Annulation de réservation confirmée");

           Optional<ButtonType> option = alert.showAndWait();

                   if (option.get() == ButtonType.OK) 
                   {
                      btnsupprimer.getScene().getWindow().hide();
                      Parent root = FXMLLoader.load(getClass().getResource("FXMLlistereservUser.fxml"));
                      Stage mainStage = new Stage();
                      Scene scene = new Scene(root);
                      mainStage.setScene(scene);
                      mainStage.show();}
                   

       }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
       }
       
       }

    @FXML
    private void consulterDevis(ActionEvent event) throws IOException{
        Reservation selectedRes=tabreserv.getSelectionModel().getSelectedItem();
      if(tabreserv.getSelectionModel().getSelectedIndex()==-1)
                   JOptionPane.showMessageDialog(null, "veuillez choisir une réservation");

      else
       {
       Node node = (Node) event.getSource();
       Stage stage = (Stage) node.getScene().getWindow();
       stage.close();
      try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDevisUserFront.fxml"));
 
            singletont holder=singletont.getInstance();
   
            holder.setRes(selectedRes);
   
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
          } catch (IOException e) {
           System.err.println(String.format("Error: %s", e.getMessage()));
       }
     
       }
   
        
    }

    @FXML
    private void btnreserver(ActionEvent event) throws IOException {
        
                      btnsupprimer.getScene().getWindow().hide();
                      Parent root = FXMLLoader.load(getClass().getResource("FXMLReservationEvenement.fxml"));
                      Stage mainStage = new Stage();
                      Scene scene = new Scene(root);
                      mainStage.setScene(scene);
                      mainStage.show();
    }
 

    }
      

