/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.User;
import Services.Codes;
import Services.ServiceUser;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author Fares
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tftelephone;
    @FXML
    private TextField tfpasswordvisible;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button btnadd;
    @FXML
    private CheckBox check_af;
    @FXML
    private Label lab_email;
    @FXML
    private Label lab_cin;
    @FXML
    private PasswordField tfcpassword;
    private ServiceUser userService;
    @FXML
    private Button Pic;
    File file;
    @FXML
    private ImageView img;
    @FXML
    private TextField captchainp;
    Random rn = new Random();
    int randomNumber = rn.nextInt(3) + 1;
    public static int loggedInID;
    public static int RestpasswordID;
    public static final String ACCOUNT_SID = "ACce4a6004da89e849c35cb572d52572fc";
    public static final String AUTH_TOKEN = "9af552f6d44fa27ce5f8b80d3d0efbcd";

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        userService = new ServiceUser();
       // Image im = new Image(this.getClass().getResourceAsStream("/captcha/" + "" + randomNumber + ".png"));
        //img.setFitHeight(150);
     //  img.setFitWidth(150);
       //img.setImage(im);
       // System.out.println(randomNumber);
    }

    @FXML
    private void btnadd(ActionEvent event) throws IOException {

       // String s = "/captcha/" + "" + randomNumber + ".png";

        System.out.println(randomNumber);
//       Tesseract tesseract = new Tesseract();

       
           // String path = "C:\\\\Program Files\\Tesseract-OCR";
           // tesseract.setDatapath(path);

           //String te = tesseract.doOCR(new File("C:\\Users\\MSI\\Documents\\NetBeansProjects\\JavaFixAuth\\src\\Tesseract-OCR" + s));
            //System.out.print(te);

            System.out.println("DONE");

//            String input = captchainp.getText();
          //String in = te.replaceAll("\\s+", "");
//            System.out.println("iput: " + input); 

            // hash password
            String password = DigestUtils.md5Hex(tfpassword.getText());

//            FileInputStream fl = new FileInputStream(file);

//            byte[] data = new byte[(int) file.length()];
            String fileName = file.getName();
            String image = fileName;
//            fl.read(data);
//            fl.close();
            User user = new User(tfusername.getText(), tftelephone.getText(), tfemail.getText(), password, image, false);
             user.setRoles("[\"ROLE_USER\"]");
         //  System.out.println(te);
          //  System.out.println(input.equals(in));
          //  System.out.println(te.length());
//            System.out.println(input.length());
          //  System.out.println(in.length());

            //int i = input.indexOf(te);
            //System.out.println(i);
           if (tfusername.getText().length()!=0 || tfpassword.getText().length()!=0) {
                userService.ajouter(user);
                sendEmail();
                System.out.println("mail  done");
                System.out.println(user);
              //  sendEmail();

                AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setWidth(600);
                stage.setHeight(400);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
            } else {
                //Aleart();
                System.out.println("wrong");
            }

        } 
    

    @FXML
    private File Pic(ActionEvent event) {
        Path to1 = null;
        String m = null;
        String path = "C:\\Users\\MSI\\Documents\\NetBeansProjects\\JavaFixAuth\\src\\Image";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file = chooser.getSelectedFile();
            String fileName = file.getName();
            System.out.println(fileName);
            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }

        System.out.println(file.getPath());
        return file;

    }

    public void sendEmail() {
        String to = tfemail.getText();
        System.out.println(tfemail.getText());
        String from = "mohamedyassine.ghazouani@esprit.tn";
        String host = "smtp.gmail.com";
        final String username = "mohamedyassine.ghazouani@esprit.tn";
        final String password = "213JFT3100";

        //setup mail server
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            ServiceUser serviceuser = new ServiceUser();
            Codes code = new Codes();
            code.getUserBy(to);
            String tel = code.getByI().getTelephone();
            System.out.println(tel);

            String verification = code.envoyerCode(RestpasswordID);
            code.codemail(verification, to);
            // code.update(verification, RestpasswordID);
            System.out.println(RestpasswordID);
            System.out.println(verification);
            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("Verifier your account");
            m.setText("To verifier youre account  entre this code:  " + verification);

            Transport.send(m);
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(new PhoneNumber("+21656140361"),
                    new PhoneNumber("+19127328233"),
                    "To Active youre account insert this code: " + verification).create();

            System.out.println(message.getSid());

            System.out.println("Message sent!");

        } catch (MessagingException e) {
            e.printStackTrace();
//        } catch (SQLException ex) {
//            Logger.getLogger(RestPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /* private void Aleart() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("CAPTCHA");
        alert.setHeaderText("Results:");
        alert.setContentText("WRONG CAPTCHA");

        alert.showAndWait();
        tfcpassword.setText("");
        tfpassword.setText("");
    }*/
}
