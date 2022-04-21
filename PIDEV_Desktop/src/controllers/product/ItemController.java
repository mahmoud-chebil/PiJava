
package controllers.product;
import controllers.*;
import java.io.IOException;
import models.Product;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.util.Callback;
import services.PanierService;
import tests.MainGUI;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ItemController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
 @FXML
    private TableColumn ajouterPanier;
 @FXML
    private TableView<Product> listeProduits;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
    }   
    public void setData(Product p) {
        nameLabel.setText(p.getName());
        priceLable.setText(p.getPrice());
        Image im = new Image("file:src/uploads/" + p.getFileName());
        img.setImage(im);
    }
    @FXML
    private void ShowShop(ActionEvent event) throws IOException {
      
        Parent root = FXMLLoader.load(getClass().getResource("../../Gui/CartFXML.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
    }
    
}




