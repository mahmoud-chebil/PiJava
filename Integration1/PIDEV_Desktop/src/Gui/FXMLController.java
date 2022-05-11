/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import services.Codes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.util.Random;
import javafx.scene.image.ImageView;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * FXML Controller class
 *
 * @author Fares
 */
public class FXMLController implements Initializable {

    @FXML
    private Button a;
    @FXML
    private ImageView img;
 


    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       

        //System.out.println(randomNumber);
    }

    @FXML
    private void a(ActionEvent event)  {
    
    }
   
  

}
