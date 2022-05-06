/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Utils.Print.printNode;

import Entities.Reservation;
import Entities.singletont;
import static Gui.FXMLDevisReservSelctController.numeroPDF;
import Utils.MyDB;
import static Utils.Print.printNode;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLDevisUserFrontController implements Initializable {

    @FXML
    private AnchorPane scene;
    @FXML
    private ImageView flecheprec;
    @FXML
    private Label idres;
    @FXML
    private Label user;
    @FXML
    private Label email;
    @FXML
    private Label dateres;
    @FXML
    private Label descevent;
    @FXML
    private Label datedeb;
    @FXML
    private Label datefin;
    @FXML
    private Label nbpers;
    @FXML
    private Label pu;
    @FXML
    private Label total;
      Connection cnx =MyDB.getInstance().getCon();
    @FXML
    private ImageView imprimer;
    @FXML
    private ImageView pdf;
      public static int numeroPDF = 0;
    Document doc = new Document();
    @FXML
    private Label remis;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void precedent(MouseEvent event) throws IOException{
             flecheprec.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLlistereservUser.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }


    @FXML
    private void reserver(MouseEvent event) throws IOException {
    
        try {
            
           singletont holder = singletont.getInstance();
           Reservation rs = holder.getRes();  
           Integer id=rs.getId();
           Date dares=rs.getDateres();
           Integer nbpersonne = rs.getNbPersonne();
           Integer userid = rs.getUser();
           Integer evenid=rs.getEven();
           Integer devisid=rs.getDevis();
            idres.setText(id.toString());
            dateres.setText(dares.toString());
            nbpers.setText(nbpersonne.toString());
                                  
           ResultSet rsuser = cnx.createStatement().executeQuery("SELECT * From user where id="+userid);
           if(rsuser.next())
           {
           user.setText(rsuser.getString("username"));
           email.setText(rsuser.getString("email"));
           }
            ResultSet rseven = cnx.createStatement().executeQuery("SELECT * From evenement where id="+evenid);
           if(rseven.next())
           {
           descevent.setText(rseven.getString("decription_evenement"));
           datedeb.setText(rseven.getString("date_debut"));
           datefin.setText(rseven.getString("date_fin"));
           pu.setText(rseven.getString("prix_evenement"));
          
           }
//             Integer t=rseven.getInt(2)*nbpersonne;
//             total.setText(t.toString());
           ResultSet rd = cnx.createStatement().executeQuery("SELECT * From devis where id="+devisid);
           if(rd.next())
           {
           remis.setText(rd.getString("remise"));
             total.setText(rd.getString("prix_tot"));
     
           }
             
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDevisReservSelctController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void imprimerDevis(MouseEvent event) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        

           printNode(scene);

        
    }

    @FXML
    private void fnpdf(MouseEvent event) {
  

        numeroPDF = numeroPDF + 1;
        String nom = "Devis " + numeroPDF + ".pdf";
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat Heure = new SimpleDateFormat("hh:mm:ss");

            WritableImage wimg = scene.snapshot(new SnapshotParameters(), null);
            File file = new File("devis.png");
            
            ImageIO.write(SwingFXUtils.fromFXImage(wimg, null), "png", file);

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\" + nom));
            doc.open();
            Image img = Image.getInstance("devis.png");
            doc.add(img);
            doc.close();
            Desktop.getDesktop().open(new File(System.getProperty("user.home") + "\\Desktop\\" + nom));
            writer.close();

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
    }
     
}

