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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import models.Product;
import services.PanierService;
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
            for (int i = 0; i < service.afficher().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../views/product/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(service.afficher().get(i));

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
                            PanierService panierService = new PanierService();
                            try {
                                panierService.addProduct( p.getId());
                            } catch (SQLException ex) {
                                
                            }
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
    private void ShowCommande(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/index.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
    }

    @FXML
    private void ShowShop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/commande/panier.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
    }
}
