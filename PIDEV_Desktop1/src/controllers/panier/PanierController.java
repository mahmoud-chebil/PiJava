/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.panier;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Panier;
import services.*;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class PanierController implements Initializable {

    @FXML
    private TableView<Panier> listePanier;
    @FXML
    private TableColumn<Panier, Integer> nomProduits;
    @FXML
    private TableColumn<Panier, Integer> quantiteProduit;
    @FXML
    private TableColumn incQt;
    @FXML
    private TableColumn decQt;
    @FXML
    private TableColumn supprimerProduits;
    
    private ObservableList<Panier> list;
   
    @FXML
    private AnchorPane containerPanier;
    @FXML
    private Button validerCommande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("afficher panier");
//        populateTableView();
    }    
    
    private void populateTableView() {
        
        list = FXCollections.observableArrayList();
        PanierService panierService = new PanierService();
//        list = panierService.getUserBasket(SeConnecterController.idUtilisateur);
        
        nomProduits.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        quantiteProduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        Callback<TableColumn<Panier, String>, TableCell<Panier, String>> cellFactoryRemove = (param) -> {

            final TableCell<Panier, String> cell = new TableCell<Panier, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    
                        final Button test = new Button("Supprimer");
                        test.setOnAction(event -> {
                            Panier p = getTableView().getItems().get(getIndex());
                            PanierService panierService = new PanierService();

                            panierService.removeProductFromBasket(p.getId());
                            
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    }
                

            };
            return cell;
        };
        
        Callback<TableColumn<Panier, String>, TableCell<Panier, String>> cellFactoryInc = (param) -> {

            final TableCell<Panier, String> cell = new TableCell<Panier, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                   
                        final Button test = new Button("Inc");
                        test.setOnAction(event -> {

                            Panier p = getTableView().getItems().get(getIndex());
                            System.out.println("+++:" + p);
                            PanierService panierService = new PanierService();

                            panierService.incQt(p.getId());
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                       //     panierService.addProduct( p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    
                }

            };
            return cell;
        };
        
        Callback<TableColumn<Panier, String>, TableCell<Panier, String>> cellFactoryDec = (param) -> {

            final TableCell<Panier, String> cell = new TableCell<Panier, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button test = new Button("Dec");
                        test.setOnAction(event -> {

                            Panier p = getTableView().getItems().get(getIndex());
                            System.out.println("+++:" + p);
                            PanierService panierService = new PanierService();

                            try {
                                panierService.decQt(p.getId());
                            } catch (SQLException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    }
                }

            };
            return cell;
        };
        
        supprimerProduits.setCellFactory(cellFactoryRemove);
        incQt.setCellFactory(cellFactoryInc);
        decQt.setCellFactory(cellFactoryDec);
        listePanier.setItems(list);
    }
    
    public void refresh() throws IOException {
        System.out.println("afficher panier");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/commande/panier.fxml"));
        Parent root = loader.load();
        containerPanier.getChildren().setAll(root);
    }
    
    @FXML
    private void validerCommande(ActionEvent event) throws IOException{
        System.out.println("valider la commande");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/commande/form.fxml"));
        Parent root = loader.load();
        containerPanier.getChildren().setAll(root);
    }
}
