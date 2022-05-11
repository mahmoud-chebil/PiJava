/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reservation;
import Entities.singletont;
import services.EvenementServiceR;
import services.ReservationService;
import utils.DataSource;
import utils.SendEmail;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import static java.util.Collections.list;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;

import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import test.FXMLTEST;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLListerReservationController implements Initializable {

  
    @FXML
    private TableView<modele> treserv;
    @FXML
    private TableColumn<modele, Integer> col_id;
    @FXML
    private TableColumn<modele, String> col_username;
    @FXML
    private TableColumn<modele, String> col_even;
    @FXML
    private TableColumn<modele, Date> col_deteres;
    @FXML
    private TableColumn<modele, Integer> col_nbpers;
    @FXML
    private TableColumn<modele, Integer> col_nbplaceres;
    @FXML
    private TableColumn<modele, Integer> col_etat;
  
 
    @FXML
    private ImageView flecheprec;
    @FXML
    private AnchorPane btnvalidation;
    Stage stage ;
    ObservableList<Reservation> listeres = FXCollections.observableArrayList();    
    ReservationService rs=new ReservationService();
    EvenementServiceR es=new EvenementServiceR();
    private Button btreservations;
    @FXML
    private Button btnsupprimer;
   Reservation r;
     Connection cnx =DataSource.getInstance().getCnx();
       Integer nbplacesrest;
       String  desceven;
    @FXML
    private Button btnDevis;
    @FXML
    private TextField search;
    
 
     ObservableList<modele> listeMOD = FXCollections.observableArrayList(); 
    @FXML
    private ImageView pfd;
     @FXML
     public void precedent() throws IOException{
    
     flecheprec.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLback.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    private void Allreservations(ActionEvent event) throws IOException{
       
        try{ 
            listeres = rs.afficher();
            if(listeres.isEmpty())
            {
                 btreservations.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLnoreservations.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
            else
            {              
               btreservations.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLListerReservation.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
                 }   catch (SQLException ex) { 
            Logger.getLogger(FXMLListerReservationController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
       ResultSet rs = cnx.createStatement().executeQuery("SELECT r.id,u.username,e.description_evenement,r.dateres,r.nb_personne,e.nbre_places,r.etat from evenement e, reservation r ,user u where r.even_id=e.id and r.user_id=u.id");
         ObservableList<modele> modele =FXCollections.observableArrayList();
        while(rs.next()){
            modele r = new modele(rs.getInt(1),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(2),rs.getString(3),rs.getDate(4));
            modele.add(r);            
        }
        
         listeMOD=modele;
        col_id.setCellValueFactory(new PropertyValueFactory<>("idres"));    
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_even.setCellValueFactory(new PropertyValueFactory<>("desceven"));
        col_deteres.setCellValueFactory(new PropertyValueFactory<>("dateres"));
        col_nbpers.setCellValueFactory(new PropertyValueFactory<>("nbrepers"));
        col_nbplaceres.setCellValueFactory(new PropertyValueFactory<>("nombreplacerest"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        treserv.getItems().setAll(listeMOD);
     
        
        FilteredList<modele> filteredData = new FilteredList<>(listeMOD, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(mod -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (mod.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                else if (mod.getDesceven().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                else if (String.valueOf(mod.getDateres()).indexOf(lowerCaseFilter)!=-1) {
					return true;
				} 
                                  else if (String.valueOf(mod.getEtat()).indexOf(lowerCaseFilter)!=-1) {
					return true;}
				     else  
				    	 return false; 
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<modele> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(treserv.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		treserv.setItems(sortedData);
//           
    }   catch (SQLException ex) { 
            Logger.getLogger(FXMLListerReservationController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    

    
    @FXML
    private void validerReservation(ActionEvent event) throws IOException, Exception{
       modele selectedRes=treserv.getSelectionModel().getSelectedItem();
      if(treserv.getSelectionModel().getSelectedIndex()==-1)
                   JOptionPane.showMessageDialog(null, "veuillez choisir une réservation à valider");
             
       else if (selectedRes.getEtat()==1)
       { Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Echec");
           alert.setContentText("la réservation est déjà valide");
           alert.show();
       }
      
      else
       {

       try{
           
           desceven=selectedRes.getDesceven();
            ResultSet rst = cnx.createStatement().executeQuery("SELECT nbre_places from `evenement` where description_evenement='"+desceven+"'");
             if (rst.next())
                nbplacesrest=rst.getInt(1);
   
           if(nbplacesrest<selectedRes.getNbrepers())
           {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Echec");
           alert.setContentText("Validation impossible: Le nombre de places restantes pour cet évènement est "+nbplacesrest);
           alert.show();
           }
           else
           {
           rs.validerReservation(selectedRes.getIdres());
           //send email
              SendEmail email = new SendEmail();
              email.mail("asma.marrouki@esprit.tn");
           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Succès");
           Integer nbres=nbplacesrest-selectedRes.getNbrepers();
           alert.setContentText("Réservation validée \n Il ne reste que "+nbres+" places pour cet évènement");

           Optional<ButtonType> option = alert.showAndWait();

                   if (option.get() == ButtonType.OK) 
                   {
                      btnsupprimer.getScene().getWindow().hide();
                      Parent root = FXMLLoader.load(getClass().getResource("FXMLListerReservation.fxml"));
                      Stage mainStage = new Stage();
                      Scene scene = new Scene(root);
                      mainStage.setScene(scene);
                      mainStage.show();}
               }        

          }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
                                       }
    
        }
    }
    


    @FXML
    private void AfficherDevis(ActionEvent event) throws SQLException {
     
        modele selectedRes=treserv.getSelectionModel().getSelectedItem();
         if(treserv.getSelectionModel().getSelectedIndex()==-1)
                   JOptionPane.showMessageDialog(null, "veuillez choisir une réservation");

         else{
        Node node = (Node) event.getSource();
  Stage stage = (Stage) node.getScene().getWindow();
  stage.close();
  try {
    Parent root = FXMLLoader.load(getClass().getResource("FXMLDevisReservSelct.fxml"));
    // Step 2
    singletont holder=singletont.getInstance();
    // Step 3
    int idreserv=selectedRes.getIdres();
   ResultSet rst = cnx.createStatement().executeQuery("SELECT * from `reservation`where id='"+idreserv+"'");
   if (rst.next())
    r = new Reservation(rst.getInt(1),rst.getInt(5),rst.getDate(6),rst.getInt(7),rst.getInt(2),rst.getInt(3),rst.getInt(4));
    holder.setRes(r);
    // Step 4
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  } catch (IOException e) {
    System.err.println(String.format("Error: %s", e.getMessage()));
  }}
    }

    }


 
      