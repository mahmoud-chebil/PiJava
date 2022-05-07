
package controllers.product;
import Controller.GestionProductsFXMLController;
import controllers.*;
import java.io.IOException;
import models.Product;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.xml.bind.DatatypeConverter;
import models.Cart;
import services.ServiceCartt;
import javafx.scene.control.cell.PropertyValueFactory;
import tests.MainGUI;
import utils.DataSource;

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
    private Label id;
      
 @FXML
    private TableColumn ajouterPanier;
 @FXML
    private TableView<Product> listeProduits;
   int id_selected;
    ObservableList <Product> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
       
        
    }   
    public void setData(Product p) {
        nameLabel.setText(p.getName());
        priceLable.setText(p.getPrice());
       id.setText(String.valueOf(p.getId()));
       
           
    }
    
      @FXML
    private void tableview_clicked(MouseEvent event) {
                 
                
        nameLabel.setText(listeProduits.getSelectionModel().getSelectedItem().getName());
            priceLable.setText(listeProduits.getSelectionModel().getSelectedItem().getPrice());
            id.setText(listeProduits.getSelectionModel().getSelectedItem().getId()+"");
 
             id_selected=listeProduits.getSelectionModel().getSelectedItem().getId();
           
            
    }
    
    
    
       @FXML
    private void ajouteaupanier(ActionEvent event) {
      
        try{
              
   Connection con = DataSource.getInstance().getCnx();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `product`");
        while(rs.next()){
        list.add(new Product(rs.getInt(1),rs.getString("name"),rs.getString("price")));
        }
        
        ServiceCartt gs = new ServiceCartt();
         String price= priceLable.getText();
         String namep = nameLabel.getText();
            Cart cart = new Cart(namep, price);
              gs.ajouter(cart);
        } catch (SQLException ex) {
            Logger.getLogger(GestionProductsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            
            
//            listeProduits.setItems(list);
   
 
           System.out.println("Produit ajouté au panier ");
      
         

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("product ajoutée");
         alert.show();
    
    }

  private Stage stage;
 private Scene scene;
 private Parent root;
    Statement stm;
    @FXML
    private void carte(ActionEvent event)throws IOException  {
                 Parent root = FXMLLoader.load(getClass().getResource("../../Gui/cartFXML.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 
    }
    
 private void commandev(ActionEvent event)throws IOException  {
                 Parent root = FXMLLoader.load(getClass().getResource("/views/commande/form.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 
    }
    
    
    
    @FXML
    private void ShowShop(ActionEvent event) throws IOException {
      
        Parent root = FXMLLoader.load(getClass().getResource("../../Gui/CarttFXML.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
    }
    
}




