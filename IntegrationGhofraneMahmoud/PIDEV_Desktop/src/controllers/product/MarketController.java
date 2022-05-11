package controllers.product;

import services.ServiceProductImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Cart;
import models.Product;
import services.ServiceCartt;

import tests.MainGUI;

public class MarketController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private TextField tfSearch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceProductImpl service=new ServiceProductImpl();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < service.afficherf().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../views/product/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(service.afficherf().get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void Search(KeyEvent event) {
        grid.getChildren().clear();
        String rech=tfSearch.getText(); 
        ServiceProductImpl service=new ServiceProductImpl();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < service.recherche(rech).size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../views/product/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(service.recherche(rech).get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
         Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory= (param) -> {

            final TableCell<Product, String> cell = new TableCell<Product, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button test = new Button("Ajouter le produit");
                        test.setOnAction(event -> {
                            Product p = getTableView().getItems().get(getIndex());
                          //  PanierService panierService = new PanierService();
                            
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    }
                }

            };
            return cell; //To change body of generated lambdas, choose Tools | Templates.
        };
        
        
        
        
        
    }
    
       @FXML
    private TableView<Product> tvgames;
       @FXML
    private void ajouteaupanier(ActionEvent event) {
   
 ServiceCartt gs = new ServiceCartt();
 
            Cart cart = new Cart(tvgames.getSelectionModel().getSelectedItem().getId(),tvgames.getSelectionModel().getSelectedItem().getName());
            

        gs.ajouter(cart);
         

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
                 Parent root = FXMLLoader.load(getClass().getResource("../../Gui/carttFXML.fxml"));
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
    private void ShowCommande(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/index.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
    }

    @FXML
    private void ShowShop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Gui/carttFXML.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
    }
}
