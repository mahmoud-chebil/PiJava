/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Activite;
import Entities.Evenement;
import services.ActiviteService;
import services.EvenementService;
import utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ActiviteFXMLController implements Initializable {


    @FXML
    private TableView<Activite> tableAc;
    @FXML
    private TableColumn<Activite, Integer> idC;
    @FXML
    private TableColumn<Activite, String> nomC;
    @FXML
    private TableColumn<Activite, String> typeC;
    @FXML
    private TableColumn<Activite, Integer> prixC;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_prix;
    @FXML
    private AnchorPane btn_stat;

    
    
    
    @FXML
    public void table(){
        ActiviteService as = new ActiviteService();
        ObservableList<Activite> list = (ObservableList<Activite>) as.afficherActivite();
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomC.setCellValueFactory(new PropertyValueFactory<>("nom_activite"));
        typeC.setCellValueFactory(new PropertyValueFactory<>("type_activite"));
        prixC.setCellValueFactory(new PropertyValueFactory<>("prix_activite"));

        tableAc.setItems(list);
        
       
    }

           
    @FXML
    private void AjouterActivite(ActionEvent event) throws IOException  {

    if(controleDeSaisiActivite()){
        
        ActiviteService as = new ActiviteService();
        Activite a = new Activite(tf_nom.getText(), tf_type.getText(), Integer.parseInt(tf_prix.getText()));
            as.ajouterActivite(a);
            Notifications notif = Notifications.create()
                    .title("Activite Ajoutée")
                    .text("Ajouter avec succée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_LEFT);
            notif.show();
            /*Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Activite ajoutée");
            alert.show();*/
            
            tf_nom.setText("");
            tf_type.setText("");
            tf_prix.setText("");
            

    }
    
        
    }
    
    @FXML
    private void OnTableClick(MouseEvent event)
    {
      Activite act= tableAc.getSelectionModel().getSelectedItem();
      tf_nom.setText(act.getNom_activite());
      tf_type.setText(act.getType_activite());
      tf_prix.setText(""+act.getPrix_activite());

    }

    @FXML
    private void ModifierActivite(ActionEvent event) {
        
        ActiviteService as = new ActiviteService();
        //Activite act= tableAc.getSelectionModel().getSelectedItem();
        Activite act = new Activite(tableAc.getSelectionModel().getSelectedItem().getId(),tf_nom.getText(), tf_type.getText(), Integer.parseInt(tf_prix.getText()));
        try {
            as.modifierActivite(act);
            
            Notifications notif = Notifications.create()
                    .title("Activite Modifiée")
                    .text("Modifiée avec succée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_LEFT);
            notif.show();
           /* Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Activite Modifiée");
            alert.show();*/
            
            tf_nom.setText("");
            tf_type.setText("");
            tf_prix.setText("");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
     @FXML
    private void supprimerActivite(ActionEvent event) throws SQLException {
         ActiviteService as = new ActiviteService();
         Activite act= tableAc.getSelectionModel().getSelectedItem();
        try {
            as.SupprimerActivite(act.getId());
            
            
                    Notifications notif = Notifications.create()
                    .title("Activite supprimée")
                    .text("Supprimée avec succée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_LEFT);
            notif.show();
           /* Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Activite Supprimer");
            alert.show();*/
            
            tf_nom.setText("");
            tf_type.setText("");
            tableAc.refresh();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
private boolean controleDeSaisiActivite() {  

    if (tf_nom.getText().isEmpty()  || tf_type.getText().isEmpty()|| tf_prix.getText().isEmpty()) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier les champs SVP");
            alert.showAndWait();
            return false; 
    } else {

          
           if (!Pattern.matches("[A-z]*", tf_nom.getText())) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Information");
                 alert.setHeaderText(null);
                 alert.setContentText("Vérifier champ nom d'activite");
                 alert.showAndWait();
                 tf_nom.requestFocus();
                 tf_nom.selectEnd();
                 return false;
                
            }if (!Pattern.matches("[A-z]*", tf_type.getText())) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Information");
                  alert.setHeaderText(null);
                  alert.setContentText("Vérifier champ type");
                  alert.showAndWait();
                  tf_type.requestFocus();
                  tf_type.selectEnd();
                  return false;
            }
            if (!Pattern.matches("[0-9]*", tf_prix.getText())) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Information");
                  alert.setHeaderText(null);
                  alert.setContentText("Vérifier champ prix ");
                  alert.showAndWait();
                  tf_prix.requestFocus();
                  tf_prix.selectEnd();
                  return false;
            }
           
        }
        return true;
    }



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        table();
    }    

    @FXML
    private void Stats(ActionEvent event) throws IOException {
                btn_stat.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("PieChart.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    

    
}
