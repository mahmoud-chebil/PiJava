
package controllers.product;

import models.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

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

    /**
     * Initializes the controller class.
     */
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
    
}




