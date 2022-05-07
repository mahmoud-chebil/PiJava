
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
public class formController implements Initializable {
    
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
    private TextField tfPrice;
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
       nomerror.setVisible(false);
       prenomerror.setVisible(false);
        adresseerror.setVisible(false);
       telephoneerror.setVisible(false);
        emailerror.setVisible(false);

        if (commande != null) {
           
//            ltitre.setText("Modifier une commande");
            tfNom.setText(commande.getNom());
            tfprenom.setText(commande.getPrenom());
            tfadressecomplet.setText(commande.getAdressecomplet());
            tftelephone.setText(commande.getTelephone());
            tfemail.setText(commande.getEmail());
         
           
           
        }
    }

    @FXML
    private void SaveCom(ActionEvent event) throws IOException {

  if (this.checkFields()) {
            if (commande != null) {
                ServiceComImpl sp = new ServiceComImpl();
                Commande p = new Commande(commande.getId(), tfNom.getText(), tfprenom.getText(), tfadressecomplet.getText(), tftelephone.getText(),tfemail.getText());
                 
                sp.modifierf(p, getCom().getId());
               
                JOptionPane.showMessageDialog(null, "Commande modifiée !");
              
            } else {
                ServiceComImpl sp = new ServiceComImpl();
 Commande p = new Commande(tfNom.getText(), tfprenom.getText(), tfadressecomplet.getText(), tftelephone.getText(),tfemail.getText());           
                sp.ajouter(p);
                JOptionPane.showMessageDialog(null, "commande ajoutée !");
            }

            Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/index.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
        }}
    

  
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
        formController.commande = commande;
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
