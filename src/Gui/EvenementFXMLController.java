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
import Static.BD;
import com.google.zxing.WriterException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EvenementFXMLController implements Initializable {


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
    private Button bn_ajouter;
    @FXML
    private DatePicker tf_debut;
    @FXML
    private DatePicker tf_fin;
    @FXML
    private ComboBox<String> bx_activite;
    @FXML
    private AnchorPane AjouterPane;
    @FXML
    private StackPane StackPane;
    @FXML
    private CheckBox recaptcha;
 

    /**
     * Initializes the controller class.
 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Services.ActiviteService service = new ActiviteService();
        
        bx_activite.getItems().addAll(service.getNomActivite());
        
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
    private void AjouterEvenement(ActionEvent event) throws SQLException, WriterException {
        
if(!controleDeSaisi() || !recaptcha.isSelected()){
    JOptionPane.showMessageDialog(null, "Veuillez verifier Recaptcha!");
     recaptcha.setStyle("-fx-border-color:red");
    
}
else
    
{        EvenementService EV = new EvenementService();
        ActiviteService A = new ActiviteService();
        
        
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
        
        Connection cnx = BD.getInstance().getCnx();
        rs = cnx.createStatement().executeQuery("SELECT id FROM activite where nom_activite='"+nom_activite+"'");
        rs.next();
        int id_activite=rs.getInt("id");
        


        
        
        Evenement e = new Evenement(prix,description,titre,places, dateD, dateF, tel,email,id_activite);
           
        try {
            EV.ajouterEvenement(e);
                    Notifications notif = Notifications.create()
                    .title("Evenement Creéé")
                    .text("Ajoutée avec succée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_LEFT);
            notif.show();
           /* Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Evenement ajoutée");
            alert.show();*/
           LoadScene();
            
            
            tf_titre.setText("");
            tf_desc.setText("");
            tf_email.setText("");
            tf_place.setText("");
            tf_tel.setText("");
            tf_prix.setText("");
            
            EV.qr(e);
            
         
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
  }
        private void LoadScene() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/ListeEvenement.fxml"));
        Scene scene = bn_ajouter.getScene();
        root.translateYProperty().set(scene.getHeight());

        StackPane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            StackPane.getChildren().remove(AjouterPane);
        });
        timeline.play();
    }

    
    
}
