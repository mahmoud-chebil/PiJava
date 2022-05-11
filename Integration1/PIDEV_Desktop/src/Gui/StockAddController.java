/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import models.Stock;
import services.ServiceStock;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Fares
 */
public class StockAddController implements Initializable {
private ServiceStock ServiceStock;
    @FXML
    private TextField tfName;
    private TextField tfemail;
    @FXML
    private Button btnadd;
    @FXML
    private TextField tfQuantity;
    @FXML
    private Button btnReturn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServiceStock = new ServiceStock();
    }    

    @FXML
    private void btnadd(ActionEvent event) throws IOException {
        Stock stock = new Stock(tfName.getText(), Integer.parseInt(tfQuantity.getText()),Integer.parseInt(tfQuantity.getText()));
        if (tfName.getText().length() != 0 || tfQuantity.getText().length() != 0) {

            ServiceStock.ajouter(stock);
            AnchorPane root = FXMLLoader.load(getClass().getResource("HomeStock.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(800);
            stage.setHeight(400);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
    }
    
}

    @FXML
    private void btnReturn(ActionEvent event) throws IOException {
            AnchorPane root = FXMLLoader.load(getClass().getResource("HomeStock.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(256);
            stage.setHeight(576);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
    }
    
}
