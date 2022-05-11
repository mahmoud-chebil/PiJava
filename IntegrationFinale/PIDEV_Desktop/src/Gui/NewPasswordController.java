/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author Fares
 */
public class NewPasswordController implements Initializable {


    @FXML
    private PasswordField newpassword;

    @FXML
    private PasswordField cpassword;
    @FXML
    private Button valid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valid(ActionEvent event) throws IOException {
        ServiceUser serviceuser = new ServiceUser();
         Alert alert = new Alert(Alert.AlertType.ERROR);
        String newpass = newpassword.getText();
        String cpass = cpassword.getText();
        if((newpass.equals(cpass)) ){
         String password= DigestUtils.md5Hex(newpass);
         serviceuser.Password(password);
         serviceuser.Nullcodemail();
         System.out.println("DONE");
         AnchorPane root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setWidth(1050);
                stage.setHeight(576);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
                
        }
        else{
             
        alert.setTitle("PASSWORD WRONG");
        alert.setHeaderText("Please Correct invalid fields");
        }
    }
    
}
