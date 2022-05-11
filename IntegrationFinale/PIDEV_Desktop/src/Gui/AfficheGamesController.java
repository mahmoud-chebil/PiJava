/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.CardProduitController;
import models.*;
import services.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author month
 */
public class AfficheGamesController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    public static String boardid ;

     private List<Product> data;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
              data = new ArrayList();
              
              ServiceProductImpl produits = new ServiceProductImpl();
              
              produits.afficher().forEach((c) -> {
                  
                  data.add(c);
                  
              });
              int column=0;
              int row=0;
              try {
                  for(int i=0; i<data.size();i++){
                      
                      
                      
                      FXMLLoader fxmlLoader = new FXMLLoader();
                      
                      fxmlLoader.setLocation(getClass().getResource("CardProduit.fxml"));
                      AnchorPane anchorPane=fxmlLoader.load();
                      CardProduitController cardController= fxmlLoader.getController();
                      
                      
                      column++;
                      
                      cardController.setDataProduit(data.get(i));
                      if (column==4)
                      {column=1;
                      row++;
                      }
                      
                      grid.add(anchorPane,column,row);
                      
                      GridPane.setMargin(anchorPane,new Insets(2));
                      
                  }
                  
              } catch (IOException ex) {
                  //Logger.getLogger(AfficherCategorieAideFrontController.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              
              
              
         
       
        
        
        
    }    
    
}
