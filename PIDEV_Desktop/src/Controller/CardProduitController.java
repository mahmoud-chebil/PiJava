/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import models.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author drwhoo
 */
public class CardProduitController implements Initializable {

    @FXML
    private Label espace;
    @FXML
    private Label titre;
    @FXML
    private Button b;
    @FXML
    private TextFlow description;
    @FXML
    private Label type;
    @FXML
    private Label prix;
    @FXML
    private ImageView image;
    Product dataProduit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setDataProduit(Product dataProduit) {
        this.dataProduit = dataProduit;
        titre.setText(dataProduit.getName());
      
                
        description.getChildren().add(new Text(dataProduit.getDescription()));
      
        prix.setText("          "+(dataProduit.getPrice())+ "TND");
        b.setId(Integer.toString(dataProduit.getId()));

    }
   
}
