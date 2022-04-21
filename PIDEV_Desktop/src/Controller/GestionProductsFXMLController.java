/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import models.*;

import services.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import utils.DataSource;
/**
 * FXML Controller class
 *
 * @author month
 */
public class GestionProductsFXMLController implements Initializable {

    @FXML
    private TextField tnom;
    @FXML
    private TextField tdesc;
    @FXML
    private TextField tprix;
    @FXML
    private TextField timg;
  
   
    
    @FXML
    private AnchorPane scaff;
    @FXML
    private TableColumn<Product, Integer> idg;
    @FXML
    private TableColumn<Product, String> nomg;
    @FXML
    private TableColumn<Product, String> prixg;
    @FXML
    private TableColumn<Product, String> descg;
    ObservableList <Product> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Product> tvgames;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    int id_selected;
    @FXML
    private Button panier;
    @FXML
    private Button cartt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
              
   Connection con = DataSource.getInstance().getCnx();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `product`");
        while(rs.next()){
        list.add(new Product(rs.getInt(1),rs.getString("name"),rs.getString("price"),rs.getString("description")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(GestionProductsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            idg.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
            nomg.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
             prixg.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
            descg.setCellValueFactory(new PropertyValueFactory<Product,String>("descreption"));   
           
            
            tvgames.setItems(list);
}    

    @FXML
    private void ajouter(ActionEvent event) {
        ServiceProductImpl gs = new ServiceProductImpl();
        Product Game = new Product(tprix.getText(),tnom.getText(),tdesc.getText());
        gs.ajouter(Game);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("produit ajoutée");
         alert.show();
    }
    @FXML
    private void tableview_clicked(MouseEvent event) {
                 
                delete.setDisable(false);
                panier.setDisable(false);

                update.setDisable(false);
       
            tnom.setText(tvgames.getSelectionModel().getSelectedItem().getName());
            tdesc.setText(tvgames.getSelectionModel().getSelectedItem().getDescription());
            tprix.setText(tvgames.getSelectionModel().getSelectedItem().getPrice()+"");
 
             id_selected=tvgames.getSelectionModel().getSelectedItem().getId();
            
    }
    private Stage stage;
 private Scene scene;
 private Parent root;
    Statement stm;
    private void Trait_inc(javafx.event.ActionEvent event) throws IOException {
        
 Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLIncription.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
        
        
    }
    Connection con;
    Statement s;
    PreparedStatement pst;
    int myIndex;
    int id;
    private void deleteT(javafx.event.ActionEvent event)throws IOException {    
                ServiceProductImpl T = new ServiceProductImpl();


                             System.out.println(tvgames.getSelectionModel().getSelectedItem().getId()+"");
       try{
                                       T.supprimer(tvgames.getSelectionModel().getSelectedItem().getId());
                                       
                                       
            JOptionPane.showMessageDialog(null, "Data avec succée!");
            Product selectedItem = tvgames.getSelectionModel().getSelectedItem();
            tvgames.getItems().remove(selectedItem);       
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }
    }

   /* private void modifierT(javafx.event.ActionEvent event) {
        int index = id_selected;
                        GamesService T = new GamesService();
           Games t=new Games(Float.parseFloat(tprix.getText()),tnom.getText(),tdesc.getText(),timg.getText());
                     if(nomg.getText().length()!=0 && descg.getText().length()!=0 ){
        try {
            T.modifier(t, index);
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionGamesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("Tournoi modifier");
         alert.show();
         list.clear();
          try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi`");
        while(rs.next()){
        list.add(new Games(rs.getInt(1),rs.getFloat("prix"),rs.getString("name"),rs.getString("descreption"),rs.getString("img")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(GestionGamesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }          
          idg.setCellValueFactory(new PropertyValueFactory<Games,Integer>("id"));
            nomg.setCellValueFactory(new PropertyValueFactory<Games,String>("name"));
            descg.setCellValueFactory(new PropertyValueFactory<Games,String>("descreption"));   
            prixg.setCellValueFactory(new PropertyValueFactory<Games,Float>("prix"));
            tvgames.setItems(list);
    }
    else
                                     JOptionPane.showMessageDialog(null, "error un champ est vide");

                         }
*/
    @FXML
    private void ajouteaupanier(ActionEvent event) {
   
 ServiceCartt gs = new ServiceCartt();
 
            Cart cart = new Cart(tvgames.getSelectionModel().getSelectedItem().getId(),tvgames.getSelectionModel().getSelectedItem().getName());
            

        gs.ajouter(cart);
         

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("product ajoutée");
         alert.show();
    
    }


    @FXML
    private void carte(ActionEvent event)throws IOException  {
                 Parent root = FXMLLoader.load(getClass().getResource("/Gui/cartFXML.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 
    }
    
 private void commandev(ActionEvent event)throws IOException  {
                 Parent root = FXMLLoader.load(getClass().getResource("/views/commande/form.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 
    }
    

}
