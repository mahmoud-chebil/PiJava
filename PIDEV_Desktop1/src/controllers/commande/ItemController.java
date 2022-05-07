
package controllers.commande;

import controllers.commande.*;
import models.Commande;
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
    private Label nameLabel;
    @FXML
    private Label prenomLable;
    @FXML
    private Label adresseLable;
        @FXML
    private Label telephoneLable;
            @FXML
    private Label emailLable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void setData(Commande p) {
        nameLabel.setText(p.getNom());
        prenomLable.setText(p.getPrenom());
        adresseLable.setText(p.getAdressecomplet());
        telephoneLable.setText(p.getTelephone());
        emailLable.setText(p.getEmail());
        
    }
    
}




