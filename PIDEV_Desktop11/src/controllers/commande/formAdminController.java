
package controllers.commande;


import controllers.commande.*;
import models.Product;
import models.*;
import services.ServiceCategoryProductImpl;
import services.ServiceProductImpl;
import tests.MainGUI;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceComImpl;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class formAdminController implements Initializable {
    
    private TableView<Commande> commandes;
  private TableView<Commande> listeq;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfprenom;
     @FXML
    private TextField tfadressecomplet;
      @FXML
    private TextField tftelephone;
       @FXML
    private TextField tfemail;
    @FXML
    private Label ltitre;
    @FXML
    private Label nomerror;
    @FXML
    private Label prenomerror;
    @FXML
    private Label adresseerror;
    @FXML
    private Label telephoneerror;
    @FXML
    private Label emailerror;
    @FXML
    private Label titreerror;
    @FXML
    private Label descriptionerror;
    @FXML
    private Label imageerror;
    String filename = "";
    private static Commande commande;
    @FXML
    private TextField etat;
    @FXML
    private ComboBox<String> cbProduct;
    @FXML
    private Label categoryerror;
    @FXML
    private Label priceerror;
    private Boolean toEditImage = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceComImpl service = new ServiceComImpl();
     
        if (commande != null) {
           
//            ltitre.setText("Modifier une commande");
            etat.setText(String.valueOf(commande.getEtat()));
            
         
           
           
        }
    }

    @FXML
    private void SaveCom(ActionEvent event) throws IOException {
   int x =Integer.parseInt(etat.getText());
      
                ServiceComImpl sp = new ServiceComImpl();
             System.out.println(x);
                Commande p = new Commande(x);
                 p.setEtat(x);
                sp.modifierr(p,commande.getId());
               
                JOptionPane.showMessageDialog(null, "Commande traitée !");
               
                     Mail ma = new Mail();
                     String ml=commande.getEmail();
                     System.out.println(commande.getEmail());
                     if(p.getEtat()==1){
        Mail.send(ml,"Commande Traitée","Votre commande est traitée avec succées\n" +
"\n" +
"Nous voulons vous informer que votre commande est traitée.\n"
                + "Vous trouverez ci-joint la liste de nos produitss\n"
                +"WWW.TuniDiscovery.TN\n"
                + " N'hésitez pas de visiter notre site pour plus d'information.\n "
                  + "Merci pour votre comprehension.","tunidiscovery2@gmail.com","ghofghof((");
                     }
              
     /*     } else {
                ServiceComImpl sp = new ServiceComImpl();
          Commande p = new Commande(commande.getId(), commande.getNom(), commande.getPrenom(), commande.getAdressecomplet(), commande.getTelephone(),commande.getEmail(),x);
                sp.ajouter(p);
                JOptionPane.showMessageDialog(null, "commande ajoutée !");
            */

            Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/index_1.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
        }
    

  
 /*   private void OpenFileMan(ActionEvent event) throws IOException {
        toEditImage = true;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            filename = "pro-" + UUID.randomUUID().toString() + ".png";

            Imagepath.setText(file.getAbsolutePath());
            Path source = Paths.get(Imagepath.getText());
            Path dest = Paths.get("src/uploads/" + filename);
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
            Image im = new Image("http://localhost/uploads/" + filename);
            ImagePub.setImage(im);
        }
    }
*/
    public static Commande getCom() {
        return commande;
    }

    public static void setCom(Commande commande) {
        formAdminController.commande = commande;
    }

    public boolean checkFields() {
     
        nomerror.setVisible(false);
        prenomerror.setVisible(false);
       adresseerror.setVisible(false);
        telephoneerror.setVisible(false);
        emailerror.setVisible(false);
        Boolean isvalid = true;
        if (tfNom.getText().isEmpty()) {
            nomerror.setVisible(true);
            isvalid = false;
        }
        if (tfprenom.getText().isEmpty()) {
            prenomerror.setVisible(true);
            isvalid = false;
        }
        if (tfadressecomplet.getText().isEmpty()) {
            adresseerror.setVisible(true);
            isvalid = false;
        }
      if (tftelephone.getText().isEmpty()) {
            telephoneerror.setVisible(true);
            isvalid = false;
        }
       if (tfemail.getText().isEmpty()) {
            emailerror.setVisible(true);
            isvalid = false;
        }
        return isvalid;
    }
     
    @FXML
    private void showProductCategories(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../commande/productcategory/index.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }

    @FXML
    private void showCommande(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/index.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }

   

  
}
