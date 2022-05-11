/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reservation;
import Entities.singletont;
import utils.DataSource;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLremiseController implements Initializable {

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
    private Label details;
    @FXML
    private Label iddevis;
    @FXML
    private TextField remise;
    Connection con;
    Statement stm;
     Connection cnx =DataSource.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void precedent(MouseEvent event) throws IOException{
      flecheprec.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDevis.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void remise(ActionEvent event) throws IOException{
                    
        singletont holder = singletont.getInstance();
        Integer iddevis = holder.getIddev();  
        float ptot,arem;   
        

        boolean n=true;
        String ch=remise.getText().toUpperCase();
        String rem=remise.getText();
  
      
        
                for(int i=0;i<ch.length();i++)
                    if((ch.charAt(i)<'0')||(ch.charAt(i)>'9'))
                          n=false;
                
        if ((remise.getText().length()==0))
            {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Attention");
                 alert.setContentText("Veuiller saisir la remise");
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
              
       
        else
        {
             float remint= Float.parseFloat(rem);
             if ((remint<=0)||(remint>=100))
             {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Attention");
                 alert.setContentText("Veuiller saisir une remise entre 0 et 100");
                 alert.show();
            }     
             else{  try{
            ResultSet rs = cnx.createStatement().executeQuery("SELECT prix_tot,remise from `devis` where id='"+iddevis+"'");
            if(rs.next())
                     {
                       ptot=rs.getFloat(1);
                        arem=rs.getFloat(2);
                       float pt=ptot*(1-(remint/100));
                       float r=arem+remint;
                       String rst2 = "UPDATE  devis SET prix_tot='"+pt+"' where id='"+iddevis+"'";
                       String rst = "UPDATE  devis SET remise='"+r+"' where id='"+iddevis+"'";       
                       con= DataSource.getInstance().getCnx();
                      stm = con.createStatement();
                       stm.executeUpdate(rst);
                       stm.executeUpdate(rst2);
                       
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Succès");
                   alert.setContentText("Remise effectuée avec succès");
                   Optional<ButtonType> option = alert.showAndWait();

                   if (option.get() == ButtonType.OK) 
                   {
                      btnremise.getScene().getWindow().hide();
                      Parent root = FXMLLoader.load(getClass().getResource("FXMLDevis.fxml"));
                      Stage mainStage = new Stage();
                      Scene scene = new Scene(root);
                      mainStage.setScene(scene);
                      mainStage.show();}
                   }
                       
                     
           
        


          }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
                                       }
             }
        }

    }

    @FXML
    private void charger(MouseEvent event){
    
            singletont holder = singletont.getInstance();
           Integer id = holder.getIddev();  
            
            iddevis.setText(id.toString());

    }
    
}
