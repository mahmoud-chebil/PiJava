/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.Stock;
import Models.User;
import Services.ServiceStock;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hazem
 */
public class HomeStock implements Initializable {

    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;
    @FXML
    private Button AddStock;
     ServiceStock StockService = new ServiceStock();;
     ArrayList<Stock> Stock =new ArrayList<>();
    @FXML
    private Button btnHome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stock= (ArrayList<Stock>) StockService.getAll();
//        System.out.println("hey");
//        System.out.println(Stock);
        for (int i = 0; i < Stock.size(); i++) {
            try {
//                System.out.println(Stock.get(i));
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Item.fxml"));
                HBox hbox= fxmlloader.load();
                ItemController itemcontroller = fxmlloader.getController();
                itemcontroller.SetdataStock((Stock.get(i)));
                  itemcontroller.getbtn().setOnAction(new EventHandler() {
                     @Override
                    public void handle(Event event) {
                        itemcontroller.DeleteStock();
                    }
                });
                
                pnItems.getChildren().add(hbox);
            
            }catch (IOException ex) {
                System.out.println(ex.getMessage());
            }   
        }
    
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        
    }

    @FXML
    private void AddStock(ActionEvent event) throws IOException  {
       
     AnchorPane root = FXMLLoader.load(getClass().getResource("StockAdd.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(588);
            stage.setHeight(493);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
         AnchorPane root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(1050);
            stage.setHeight(575);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
    }
        
    }
    

