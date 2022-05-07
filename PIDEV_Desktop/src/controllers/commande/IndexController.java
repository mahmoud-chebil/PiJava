/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.commande;



import Gui.QrController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import controllers.commande.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import models.Commande;
import services.*;
import tests.MainGUI;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx_qrcodewriter.JavaFX_QRCodeWriter;
import javax.swing.JOptionPane;
import services.ServiceComImpl;
import Gui.QrController;
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class IndexController implements Initializable {

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
    @FXML
    private TableColumn<Commande, String> produits;
    @FXML
    private TableColumn<Commande, String> total;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
     public Text topText;
    public Button addButton;
    public VBox mainVBox;
    ServiceComImpl rs=new ServiceComImpl();
    ServiceProductImpl es=new ServiceProductImpl();

    //private ServiceUser US;
    @FXML
    private AnchorPane mainPain;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceComImpl service = new ServiceComImpl();
        List Commmande = service.afficher();
        ObservableList OL = FXCollections.observableArrayList(Commmande);
        commandes.setItems(OL);
       produits.setCellValueFactory(new PropertyValueFactory<>("detailspro"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressecomplet.setCellValueFactory(new PropertyValueFactory<>("adressecomplet"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
         total.setCellValueFactory(new PropertyValueFactory<>("total"));
         
       //   US = new ServiceUser();

      
        
         
         ServiceCartt sr = new ServiceCartt();
       
        try {
            sr.deleteCartf();
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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
            produits.setText(commandes.getSelectionModel().getSelectedItem().getDetailspro());
             //total.set(commandes.getSelectionModel().getSelectedItem().getTotal());
             System.out.println("Clicked : "+commandes.getSelectionModel().getSelectedItem().getTotal());       
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
        produits.setCellValueFactory(new PropertyValueFactory<>("detailspro"));
          total.setCellValueFactory(new PropertyValueFactory<>("total"));
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            
            
    }



    @FXML
    private void Edit(ActionEvent event) throws IOException {
      //    if (selectedCommande.getId() != 0) {
            formController af = new formController();
           
            formController.setCom(selectedCommande);
            Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/form.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
        //}
    }
       @FXML
    private void QR(ActionEvent event) throws IOException {
        if (selectedCommande.getId() != 0) {
          //  QrController qrc = new QrController();
           
            QrController.setCom(selectedCommande);
            Parent root = FXMLLoader.load(getClass().getResource("../../Gui/qr.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
        }
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
    private void showCommandeadmin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/index_1.fxml"));
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
    private void ShowQR(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/event/Category Event Back.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }
     public void Qr() {
     QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "https://www.facebook.com/groups/416857346411269/posts/554993805930955/";
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(JavaFX_QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
          
       StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(qrView);
            mainVBox.getChildren().add(stackPane);
    }

}
