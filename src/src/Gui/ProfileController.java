/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.User;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafixauth.JavaFixAuth.loggedInID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author Fares
 */
public class ProfileController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private ImageView pic;
    private ServiceUser userService;
    @FXML
    private Button logout;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField cpassword;
    @FXML
    private Button save;
    @FXML
    private TextField userna;
    @FXML
    private Button refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService = new ServiceUser();
        User user = userService.getById();
//        String path = "/Image/" + userService.getById().getImage();
//     Image image = new Image(getClass().getResourceAsStream(path));
//    
//    pic.setImage(image);
        username.setText(userService.getById().getUsername());
        email.setText(user.getEmail());
        phone.setText(user.getTelephone());
        userna.setText(user.getUsername());
        System.out.println(loggedInID);
        System.out.println(loggedInID);
        System.out.println("yo");

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(580);
        stage.setHeight(490);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    private void save(ActionEvent event) {
        String e = email.getText();
        String ph = phone.getText();
        String u = userna.getText();
        String cp = cpassword.getText();
        String p = password.getText();
        if (cp.equals(p)) {
            String password = DigestUtils.md5Hex(p);
            String path =  userService.getById().getImage();
            User use = new User(loggedInID, u, ph, e, password,path );
            userService.modifier(use);
            succes();
           
            System.out.println("done");
        } else {
            Passaleart();
        }
    }

    public void setdata() {

    }

    private void Passaleart() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERR");
        alert.setHeaderText("Results:");
        alert.setContentText("pass not the same");

        alert.showAndWait();
    }
     private void succes() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Done");
        alert.setHeaderText("Results:");
        alert.setContentText("update succes");

        alert.showAndWait();
    }

    @FXML
    private void refresh(ActionEvent event) {
         userService = new ServiceUser();
        User user = userService.getById();
        //String path = "/Image/fil.png" + userService.getById().getImage();
      //  Image image = new Image(getClass().getResourceAsStream(path));
       // pic.setImage(image);
        username.setText(userService.getById().getUsername());
        email.setText(user.getEmail());
        phone.setText(user.getTelephone());
        userna.setText(user.getUsername());
        System.out.println(loggedInID);
        System.out.println(loggedInID);
        System.out.println("yo");
        
    }

}
