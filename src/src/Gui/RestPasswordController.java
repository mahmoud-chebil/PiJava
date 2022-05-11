/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.Codes;
import Services.ServiceUser;
import java.io.IOException;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafixauth.JavaFixAuth.RestpasswordID;
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
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Fares
 */
public class RestPasswordController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button onclock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onclick(ActionEvent event) throws IOException {
        sendEmail();
        AnchorPane root = FXMLLoader.load(getClass().getResource("Check.fxml"));
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setWidth(1050);
                stage.setHeight(576);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
    }
    
    public  void sendEmail() {
        String to = email.getText();
        String from = "fares.mekni.jr@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "fares.mekni.jr@gmail.com";
        final String password = "123456789hama";
        
        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(username, password);
            }
        });

        try{
            ServiceUser serviceuser = new ServiceUser();
            Codes code= new Codes();
            code.getUserBy(to);
            
           String verification=code.envoyerCode(RestpasswordID);
           code.codemail(verification, to);
          // code.update(verification, RestpasswordID);
            System.out.println(RestpasswordID);
            System.out.println(verification);
            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("Rest Password");
            m.setText("To rest youre password check entre this code:  "+verification);

           

            Transport.send(m);
            
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
//        } catch (SQLException ex) {
//            Logger.getLogger(RestPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
