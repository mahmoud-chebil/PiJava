/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import models.Stock;
import models.User;
import services.ServiceUser;
import utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import tests.MainGUI;

/**
 * FXML Controller class
 *
 * @author Fares
 */
public class ItemController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label tfusername;
    @FXML
    private Label tfemail;
    @FXML
    private Label tftelephone;
    @FXML
    private Label tfroles;
    @FXML
    private Button tfis_expired;
    
    private User user;
    @FXML
    private Label tfid;
    @FXML
    private Button bann;
    @FXML
    private Button unbann;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
   public Button getbtn(){
   return tfis_expired;
   }
   public Button getbann(){
   return bann;
   }
   
   
   public User getu(){
   return user;
   }
    public Button getunbann(){
    return unbann;
   }
   
           
    public void Setdata(User u) {
        this.user = u;
        tfid.setText(""+u.getId());
        tfemail.setText(u.getEmail());
        tfusername.setText(u.getUsername());
        tftelephone.setText(u.getTelephone());
        tfroles.setText(u.getRoles());
        //tfis_expired.setOnAction(u.setIs_expired(true));
       //u.setIs_expired(Boolean.parseBoolean(tfis_expired.getText()));

    }
    public void SetdataStock(Stock u) {
        tfid.setText(String.valueOf(u.getId()));
        tfemail.setText(String.valueOf(u.getQuantity()));
        tfusername.setText(u.getName());
        tftelephone.setText(String.valueOf(u.getRest_q()));

    }
    private void executeQuery(String req) {
        Connection cnx = DataSource.getInstance().getCnx();
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
      public void DeleteStock() {
        String req = "delete from Stock where id=" + tfid.getText() + "";
        executeQuery(req);
        
    }
    @FXML
        public void Delete() {
        String req = "delete from user where id=" + tfid.getText() + "";
        executeQuery(req);
        
    }
    @FXML
         public void bann() {
        String req = "UPDATE user SET is_expired=" +true +" where id=" + tfid.getText() + "";
        executeQuery(req);
        
    }
    @FXML
               public void unbann() {
        String req = "UPDATE user SET is_expired=" +false +" where id=" + tfid.getText() + "";
        executeQuery(req);
        
    }


//    @FXML
//    private void Delete(ActionEvent event) {
//        ServiceUser co = new ServiceUser();
//        User u = new User();
//        co.supprimer1(tfid.getT);
//    }
      @FXML
    private void showpanel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLback.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }

   

}
