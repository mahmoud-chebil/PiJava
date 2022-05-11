/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.CarttFXMLController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import controllers.commande.formController;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Commande;
import tests.qr;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author poste 1
 */
public class QrController implements Initializable {
    private static Commande ev;
    @FXML
    private AnchorPane qr;
@FXML
private ImageView imageqr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Commande evt = null;
        ObservableList<Commande> list1 = FXCollections.observableArrayList();
        Connection cnx = DataSource.getInstance().getCnx();
        int id=ev.getId();
        System.out.println(id);
        ResultSet rst = null;
        
//                rst = cnx.createStatement().executeQuery("SELECT * FROM commande WHERE id=?");
//                evt = new Commande(rst.getInt("id"),rst.getString("nom"),rst.getString("prenom"),rst.getString("adressecomplet"),rst.getString("telephone"),rst.getFloat("total"),rst.getString("email"),rst.getString("produits"));
//              System.out.println(evt);



String myWeb = " Votre nom =  " + ev.getNom()+ " , Votre prenom =  "+ev.getPrenom()+ " , votre adresse = " + ev.getAdressecomplet()+" Vos produits sont = "+ev.getDetailspro()+ " , le totale de votre commande est " + ev.getTotal()+" DT";
QRCodeWriter qrCodeWriter = new QRCodeWriter();
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
    Logger.getLogger(qr.class.getName()).log(Level.SEVERE, null, ex);
}
ImageView qrView = new ImageView();
qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
StackPane root = new StackPane();
root.getChildren().add(qrView);
Scene scene = new Scene(root, 350, 350);
Stage primaryStage=new Stage();
primaryStage.setTitle("QR Code de votre commande");
primaryStage.setScene(scene);
//        primaryStage.initModality(Modality.APPLICATION_MODAL);
primaryStage.show();

       

    }

        
           public static Commande getCom() {
        return ev;
    }

    public static void setCom(Commande commande) {
        QrController.ev = commande;
    }

    }    
    

