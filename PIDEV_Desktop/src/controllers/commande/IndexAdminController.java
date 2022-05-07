/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.commande;



import controllers.commande.*;

import models.Commande;
import services.*;
import tests.MainGUI;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.ServiceComImpl;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class IndexAdminController implements Initializable {

    @FXML
    private TableView<Commande> commandes;
    @FXML
    private TableColumn<Commande, String> nom;
     @FXML
    private TableColumn<Commande, Integer> id;
     @FXML
    private TableColumn<Commande, String> prenom;
     @FXML
    private TableColumn<Commande, String> adressecomplet;
     @FXML
    private TableColumn<Commande, String> telephone;
     @FXML
    private TableColumn<Commande, String> email;
     
      @FXML
    private TableColumn<Commande, Integer> idetat;
     
    @FXML
    private TextField tfrecherche;
    private Commande selectedCommande;
    @FXML
    private Button EventsBack;
    @FXML
    private Button CatComEventsBack;
    @FXML
    private Button Commandes;
    @FXML
    private Button CommandeProduct;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceComImpl service = new ServiceComImpl();
        ObservableList OL = FXCollections.observableArrayList(service.afficherad());
        commandes.setItems(OL);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressecomplet.setCellValueFactory(new PropertyValueFactory<>("adressecomplet"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        idetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }    

    @FXML
    private void SelectItem(MouseEvent event) {
                selectedCommande = commandes.getSelectionModel().getSelectedItem();
 System.out.println("Clicked : "+commandes.getSelectionModel().getSelectedItem().getId());         
            nom.setText(commandes.getSelectionModel().getSelectedItem().getNom());
            prenom.setText(commandes.getSelectionModel().getSelectedItem().getPrenom()+"");
            adressecomplet.setText(commandes.getSelectionModel().getSelectedItem().getAdressecomplet());
            telephone.setText(commandes.getSelectionModel().getSelectedItem().getTelephone());
            email.setText(commandes.getSelectionModel().getSelectedItem().getEmail());
        commandes.getSelectionModel().getSelectedItem().getEtat();  
    }


    @FXML
    private void NewCommande(ActionEvent event) throws IOException {
         formController a = new formController();
        formController.setCom(null);
        Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/form.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }

   
  /*  private void Delete(ActionEvent event) {
        int reply = JOptionPane.showConfirmDialog(null, "Do you want delete this item?", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            ServiceComImpl service = new ServiceComImpl();
            service.supprimer(selectedCommande);
            this.initialize(null, null);
            JOptionPane.showMessageDialog(null, "Commande supprimé !");
        }
    }*/
    @FXML
   private void Delete(ActionEvent event) throws IOException{
          try{
           ServiceComImpl cs = new ServiceComImpl();
        cs.supprimer(commandes.getSelectionModel().getSelectedItem().getId());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
        try{
           ServiceComImpl cs = new ServiceComImpl();
            List Commande = cs.afficher();
        ObservableList list = FXCollections.observableArrayList(Commande);
        commandes.setItems(list);
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressecomplet.setCellValueFactory(new PropertyValueFactory<>("adressecomplet"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
         idetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            
            
    }
ServiceComImpl rs = new ServiceComImpl();


    @FXML
    private void triee(ActionEvent event) {
         ObservableList<Commande> items =FXCollections.observableArrayList();
        List<Commande> listuser = rs.afficherad();
       Set<Commande> liste= rs.triparetat(listuser);
       for(Commande r : liste) {
            String ch = r.toString();
            items.add(r);
        }
        commandes.setItems(items);
    }


    @FXML
    private void Edit(ActionEvent event) throws IOException {
      //    if (selectedCommande.getId() != 0) {
            formAdminController af = new formAdminController();
           
            formAdminController.setCom(selectedCommande);
            Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/form_1.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
        //}
    }

    @FXML
    private void Search(KeyEvent event) {
         String rech=tfrecherche.getText();
        
        ServiceComImpl service = new ServiceComImpl();
        ObservableList OL = FXCollections.observableArrayList(service.recherche(rech));
        commandes.setItems(OL);
    }

  /*  @FXML
    private void showProductCategories(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/productcategory/index.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }*/

    @FXML
    private void showCommande(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/index.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }

    @FXML
    private void ShowEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/event/EventsBack.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }

    @FXML
    private void ShowCategoryEvent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/event/Category Event Back.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }
    
}
