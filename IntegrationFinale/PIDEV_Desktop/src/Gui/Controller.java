/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import models.User;
import services.ServiceUser;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tests.MainGUI;

/**
 * FXML Controller class
 *
 * @author Fares
 */
public class Controller implements Initializable {

    @FXML
    private Button btnOverview;
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

     ServiceUser userService = new ServiceUser();;
     ArrayList<User> user =new ArrayList<>();
    @FXML
    private Button btnStock;
    @FXML
    private Button logout;
    User Current_user=new User();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoggedUser holder = LoggedUser.get_instace(); 
        Current_user=holder.getUser();
        user= (ArrayList<User>) userService.getAll();
        for (int i = 0; i < user.size(); i++) {
            try {
                //System.out.println(user.get(i));
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Item.fxml"));
                HBox hbox= fxmlloader.load();
                ItemController itemcontroller = fxmlloader.getController();
                itemcontroller.Setdata(user.get(i));
                itemcontroller.getbtn().setOnAction(new EventHandler() {
                     @Override
                    public void handle(Event event) {
                        itemcontroller.Delete();
                        
                    }
                });
                 itemcontroller.getbann().setOnAction(new EventHandler() {
                     @Override
                    public void handle(Event event) {
                        itemcontroller.bann();
                    }
                });
                 itemcontroller.getunbann().setOnAction(new EventHandler() {
                     @Override
                    public void handle(Event event) {
                        itemcontroller.unbann();
                    }
                });
                
                
                
                
                pnItems.getChildren().add(hbox);
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void Stock(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("HomeStock.fxml"));
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setWidth(1050);
                stage.setHeight(576);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
          AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setWidth(1050);
                stage.setHeight(576);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
    }
          @FXML
    private void showpanel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLback.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }

}
    

