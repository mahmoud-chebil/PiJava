/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import models.Reclamation;
import services.ReclamationService;
import utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class BackReclamationController implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TableView<Reclamation> tvreclamation;
    @FXML
    private TableColumn<Reclamation, Integer> colid;
    @FXML
    private TableColumn<Reclamation, Integer> coluser;
    @FXML
    private TableColumn<Reclamation, Integer> coltype;
    @FXML
    private TableColumn<Reclamation, String> coltitre;
    @FXML
    private TableColumn<Reclamation, String> coldesription;
    @FXML
    private TableColumn<Reclamation, Date> coldate;
    @FXML
    private TableColumn<Reclamation, String> colreponse;
     ObservableList<String> option
            = FXCollections.observableArrayList(
                    "en cour",
                    "traite"
                    );
    @FXML
    private TableColumn<Reclamation, String> coletat;
    @FXML
    private ComboBox<String> etat;
    @FXML
    private TextArea reponse;
    int select;
    ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
    @FXML
    private Button btnreponse;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
           
            etat.setItems(option);

                   Connection cnx = DataSource.getInstance().getCnx();

            ResultSet rs1 = cnx.createStatement().executeQuery("SELECT * FROM `reclamation`");
            while (rs1.next()) {
                ReclamationList.add(new Reclamation(rs1.getInt(1), rs1.getInt(2), rs1.getInt(3), rs1.getString(4), rs1.getString(5), rs1.getDate(6), rs1.getString(7), rs1.getString(8)));
             }
            colid.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
           coluser.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("IdUser"));
            coltype.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("IdType"));
            coltitre.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("titre"));
            coldate.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("dateRec"));
            coldesription.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("descRec"));
            coletat.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("etat"));
            colreponse.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("reponse"));
            tvreclamation.setItems(ReclamationList);
        } catch (SQLException ex) {
            Logger.getLogger(TypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     int iditem;
    @FXML
      void Mouse_clicked(MouseEvent event) {
        System.out.println(tvreclamation.getSelectionModel().getSelectedItem().getId());
        etat.setValue(tvreclamation.getSelectionModel().getSelectedItem().getEtat());
        btnupdate.setDisable(false);
        btndelete.setDisable(false);
        btnreponse.setDisable(false);
        iditem = tvreclamation.getSelectionModel().getSelectedItem().getId();

        
    }

   
    
    @FXML
    private void updateEtat(javafx.event.ActionEvent event) throws SQLException {
        int index = select;
                System.out.println(tvreclamation.getSelectionModel().getSelectedItem().getId()+"");

        ReclamationService R = new ReclamationService();
        Reclamation rec = new Reclamation(etat.getValue().toString());
        rec.setEtat(etat.getValue());
        if (etat.getValue().length() != 0 ) {
            R.etatR(rec,iditem);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("success");

            alert.setContentText("reclamation modifier");
            alert.show();
            ReclamationList.clear();
            try {
                        Connection con = DataSource.getInstance().getCnx();

                ResultSet rs1 = con.createStatement().executeQuery("SELECT * FROM `reclamation`");
                 while (rs1.next()) {
                    rec = new Reclamation(rs1.getInt(1), rs1.getInt(2), rs1.getInt(3), rs1.getString(4), rs1.getString(5), rs1.getDate(6), rs1.getString(7), rs1.getString(8));
                    ReclamationList.add(rec);
                }
            colid.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
            coluser.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("IdUser"));
            coltype.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("IdType"));
            coltitre.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("titre"));
            coldate.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("dateRec"));
            coldesription.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("descRec"));
            coletat.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("etat"));
            colreponse.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("reponse"));
            tvreclamation.setItems(ReclamationList);

            } catch (SQLException ex) {
                Logger.getLogger(TypeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 

    }
    
   
    
    @FXML
    private void updateReponse() throws SQLException  {
        int index = iditem;
        System.out.println(tvreclamation.getSelectionModel().getSelectedItem().getId()+"");
        ReclamationService R = new ReclamationService();
        Reclamation rec = tvreclamation.getSelectionModel().getSelectedItem();
        rec.setReponse(reponse.getText());
        if (reponse.getText().length() != 0 ) {
            try {
                R.reponseR(tvreclamation.getSelectionModel().getSelectedItem().getId(),rec);
                System.out.println(rec.toString());
                R.send(R.getUserEmail(tvreclamation.getSelectionModel().getSelectedItem().getIdUser()));

            } catch (SQLException ex) {
                Logger.getLogger(BackReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("success");

            alert.setContentText("reponse ajouter");
            alert.show();
            ReclamationList.clear();
            try {
                Connection con = DataSource.getInstance().getCnx();

                ResultSet rs1 = con.createStatement().executeQuery("SELECT * FROM `reclamation`");
                 while (rs1.next()) {
                    rec = new Reclamation(rs1.getInt(1), rs1.getInt(2), rs1.getInt(3), rs1.getString(4), rs1.getString(5), rs1.getDate(6), rs1.getString(7), rs1.getString(8));
                    ReclamationList.add(rec);
                }
            colid.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
            coluser.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("IdUser"));
            coltype.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("IdType"));
            coltitre.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("titre"));
            coldate.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("dateRec"));
            coldesription.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("descRec"));
            coletat.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("etat"));
            colreponse.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("reponse"));
            tvreclamation.setItems(ReclamationList);

            } catch (SQLException ex) {
                Logger.getLogger(TypeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
    }
    
    

    
    Connection con;
    Statement s;
    PreparedStatement pst;
    int myIndex;
    int id;
   
    @FXML
    private void deleteRec(javafx.event.ActionEvent event) throws IOException {
        ReclamationService T = new ReclamationService();

        System.out.println(tvreclamation.getSelectionModel().getSelectedItem().getId() + "");
        try {
            T.deleteReclamation(tvreclamation.getSelectionModel().getSelectedItem().getId());

            JOptionPane.showMessageDialog(null, "suppression avec succée!");
            Reclamation selectedItem = tvreclamation.getSelectionModel().getSelectedItem();
            tvreclamation.getItems().remove(selectedItem);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.getMessage());

        }
    }
        
      
    private Stage stage;
    private Scene scene;
    private Parent root;
    Statement stm;
    @FXML
    private void backAcceuil(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/ReclamationAcceuil.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void search(KeyEvent event) {
        ReclamationService rs = new ReclamationService();
        tvreclamation.setItems((ObservableList<Reclamation>) rs.search(search.getText()));
    }

      

    
}
