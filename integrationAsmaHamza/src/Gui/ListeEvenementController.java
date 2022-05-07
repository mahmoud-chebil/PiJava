/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Activite;
import Entities.Evenement;
import Services.ActiviteService;
import Services.EvenementService;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ListeEvenementController implements Initializable {


    @FXML
    private TableColumn<Evenement, Integer> idC;
    @FXML
    private TableColumn<Evenement, String> titreC;
    @FXML
    private TableColumn<Evenement, Integer> prixC;
    @FXML
    private TableColumn<Evenement, Integer> placeC;
    @FXML
    private TableColumn<Evenement, Integer> telC;
    @FXML
    private TableColumn<Evenement, String> descrC;
    @FXML
    private TableColumn<Evenement, Date> debutC;
    @FXML
    private TableColumn<Evenement, Date> finC;
    @FXML
    private TableColumn<Evenement, String> emailC;
    @FXML
    private TableColumn<Evenement, String> activiteC;

    @FXML
    private TableView<Evenement> tableEv;
    @FXML
    private Button refrecher;
    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_prix;
    @FXML
    private TextArea tf_desc;
    @FXML
    private TextField tf_place;
    @FXML
    private TextField tf_tel;
    @FXML
    private TextField tf_email;
    @FXML
    private DatePicker tf_debut;
    @FXML
    private DatePicker tf_fin;
    @FXML
    private ComboBox<String> bx_activite;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private TextField tf_id;

    @FXML
    private AnchorPane ListePane;
    @FXML
    private TextField tf_recherche;
    @FXML
    private ImageView btnpred;
    @FXML
    private Button btn_creer;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ActiviteService A = new ActiviteService();
        bx_activite.getItems().addAll(A.getNomActivite());
        table();
    }  
    
    private boolean controleDeSaisi() {  

    if (tf_titre.getText().isEmpty()  || tf_desc.getText().isEmpty()  ||  tf_email.getText().isEmpty()|| tf_place.getText().isEmpty()  || tf_tel.getText().isEmpty()  ||
            tf_debut.getValue()==null|| tf_fin.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier les champs SVP");
            alert.showAndWait();
            return false; 
       } else {

                if (!Pattern.matches("[A-z]*", tf_titre.getText())) {
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setTitle("Information");
                         alert.setHeaderText(null);
                         alert.setContentText("Vérifier champ titre d'événement");
                         alert.showAndWait();
                         tf_titre.requestFocus();
                         tf_titre.selectEnd();
                         return false;
                
                }
                if (!Pattern.matches("[A-z]*", tf_desc.getText())) {
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setTitle("Information");
                         alert.setHeaderText(null);
                         alert.setContentText("Vérifier le champ Description");
                         alert.showAndWait();
                         tf_desc.requestFocus();
                         tf_desc.selectEnd();
                         return false;
                }
                if (!Pattern.matches("[0-9]*", tf_tel.getText())) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Vérifier  le cham telephone ");
                        alert.showAndWait();
                        tf_tel.requestFocus();
                        tf_tel.selectEnd();
                        return false;
                 }
                if (!Pattern.matches("[0-9]*", tf_prix.getText())) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Vérifier champ prix d'événement");
                        alert.showAndWait();
                        tf_prix.requestFocus();
                        tf_prix.selectEnd();
                        return false;
                }
                if (!Pattern.matches("[0-9]*", tf_place.getText())) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Vérifier champ nombre de places d'événement");
                        alert.showAndWait();
                        tf_place.requestFocus();
                        tf_place.selectEnd();
                        return false;
                }
            
        }
        return true;
    }
    
    @FXML
    private void precedant(MouseEvent event) throws IOException {
           btnpred.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLaccueilfront.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    @FXML
    private void OnTableClick(MouseEvent event)
    {
      Evenement e= tableEv.getSelectionModel().getSelectedItem();
      tf_titre.setText(e.getTitre_evenement());
      tf_desc.setText(e.getDecription_evenement());
      tf_email.setText(e.getEmail());
      tf_place.setText(""+e.getNbre_places());
      tf_tel.setText(""+e.getTel());
      tf_prix.setText(""+e.getPrix_evenement());
      tf_debut.setValue((e.getDate_debut().toLocalDate()));
      tf_fin.setValue((e.getDate_fin().toLocalDate()));
      bx_activite.setValue(""+e.getId_activite());
      tf_id.setText(""+e.getId());
      
    }
    
    @FXML
    public void table(){
        EvenementService es = new EvenementService();
        ObservableList<Evenement> list = (ObservableList<Evenement>) es.afficherEvenement();
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixC.setCellValueFactory(new PropertyValueFactory<>("prix"));
        descrC.setCellValueFactory(new PropertyValueFactory<>("description_evenement"));
        titreC.setCellValueFactory(new PropertyValueFactory<>("titre_evenement"));
        placeC.setCellValueFactory(new PropertyValueFactory<>("nbre_places"));
        debutC.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        finC.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        telC.setCellValueFactory(new PropertyValueFactory<>("tel"));
        emailC.setCellValueFactory(new PropertyValueFactory<>("email"));
        activiteC.setCellValueFactory(new PropertyValueFactory<>("id_activite"));
        
        
        

        tableEv.setItems(list);
        
       
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
        
        EvenementService es = new EvenementService();
        Evenement e= tableEv.getSelectionModel().getSelectedItem();
        try {
            es.supprimerEvenement(e.getId());
                        Notifications notif = Notifications.create()
                    .title("Evenement Supprimeré")
                    .text("Supprimer avec succée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_LEFT);
            notif.show();
          /*  Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Activite Supprimer");
            alert.show();*/
            
            tf_titre.setText("");
            tf_desc.setText("");
            tf_place.setText("");
            tf_email.setText("");
            tf_tel.setText("");
            tf_prix.setText("");
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifierEvenement(ActionEvent event) throws SQLException{
        
        
    if(controleDeSaisi()){  
         EvenementService EV = new EvenementService();
        ActiviteService A = new ActiviteService();

        
        int id = Integer.parseInt(tf_id.getText());
        int prix = Integer.parseInt(tf_prix.getText());
        String description = tf_desc.getText();
        String titre = tf_titre.getText();
        int places = Integer.parseInt(tf_place.getText());
        Date dateD = Date.valueOf(tf_debut.getValue());
        Date dateF = Date.valueOf(tf_fin.getValue());
        int  tel = Integer.parseInt(tf_tel.getText());
        String email = tf_email.getText();
        String nom_activite = bx_activite.getValue();        
        ResultSet rs = null ;
        
    Connection cnx = MyDB.getInstance().getCon();
        rs = cnx.createStatement().executeQuery("SELECT id FROM activite where nom_activite='"+nom_activite+"'");
        rs.next();
        int id_activite=rs.getInt("id");
        
        EvenementService es = new EvenementService();
        Evenement e = new Evenement(id,prix,description,titre,places, dateD, dateF, tel,email,id_activite);       
        try {
            es.modifierEvenement(e);
                    Notifications notif = Notifications.create()
                    .title("Evenement Modifiéé")
                    .text("Modifié avec succé")
                    .graphic(null)
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_LEFT);
            notif.show();
           /* Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Evenement Modifiée");
            alert.show();*/
            
            tf_titre.setText("");
            tf_email.setText("");
            tf_prix.setText("");
            tf_place.setText("");
            tf_desc.setText("");
            tf_tel.setText("");
            tf_id.setText("");
            tf_debut.setValue(null);
            tf_fin.setValue(null);
           // bx_activite.setValue(null);
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    @FXML
    private void creerEvenement(ActionEvent event) throws IOException {
             btn_creer.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("EvenementFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }



     
}
