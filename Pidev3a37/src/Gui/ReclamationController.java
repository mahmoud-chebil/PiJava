/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reclamation;
import Entities.TypeReclamation;
import Entities.User;
import Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class ReclamationController implements Initializable {

    @FXML
    private ComboBox<User> user;
    @FXML
    private ComboBox<TypeReclamation> type;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
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
    private TableColumn<Reclamation, Date> coldate;
    @FXML
    private TableColumn<Reclamation, String> coldesription;
    @FXML
    private TableColumn<Reclamation, String> coletat;
    @FXML
    private TableColumn<Reclamation, String> colreponse;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    private Button btndelete;
    
    private Reclamation reclamation;
    Connection con;
    String username;
    /**
     * Initializes the controller class.
     */
    ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
    @FXML
    private Button back;
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<TypeReclamation> options = FXCollections.observableArrayList();
        ObservableList<User> options1 = FXCollections.observableArrayList();

        Connection conn = getConnection();
//        String query = "SELECT * FROM reclamation";

        Statement st;
        ResultSet rs;
        try {
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT * from `reclamation` ");

            
            ResultSet s = conn.createStatement().executeQuery("SELECT * from `type_reclamation` ");
            while (s.next()) {
                type.getItems().add(new TypeReclamation(s.getInt("id"), s.getString("description")));

            }
             //s2 = user.getSelectionModel().getSelectedItem().toString();
            ResultSet s1 = conn.createStatement().executeQuery("SELECT * from `user` ");
            while (s1.next()) {
                user.getItems().add(new User(s1.getInt("id"), s1.getString("username"), "", "", "", true, true));
                
                 }
            
            ResultSet rs2 = conn.createStatement().executeQuery("SELECT id from user where username='"+options1+"'");
            int id;
            if (rs2.next())
                id=rs2.getInt(1);

//            st = conn.createStatement();
//            rs = st.executeQuery(query);
            Reclamation rec;
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

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    @FXML
    private void insertReclamation() throws SQLException {
        ReclamationService r = new ReclamationService();
       
//       username = user.getSelectionModel().getSelectedItem().toString(); 
//        String req2 = "Select id from `user` where username="+username;
//        stm = con.createStatement();

           
        
//        ResultSet rst = stm.executeQuery(req2);
        Reclamation tr = new Reclamation(user.getValue().getId(),type.getValue().getId(),titre.getText().toString(),description.getText().toString(),Date.valueOf(LocalDate.now()));

        if (!description.getText().equals("") && !titre.getText().equals("")) {
            r.ajouterR(tr);
            Alert alertA = new Alert(Alert.AlertType.NONE, "ajout sucees", ButtonType.APPLY);
            alertA.show();
            ReclamationList.clear();
            Connection conn = getConnection();
            String query = "SELECT * FROM reclamation";
            Statement st;
            ResultSet rs;
            try {
                ResultSet rs1 = conn.createStatement().executeQuery("SELECT * from `reclamation` ");

                st = conn.createStatement();
                rs = st.executeQuery(query);
                 Reclamation rec;
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

            
            
        } else {
            Alert alertA = new Alert(Alert.AlertType.NONE, "champ vide", ButtonType.APPLY);
            alertA.show();
        }

    }
    int iditem;
    String iduser;
    String idtype;

    private void Clicked_item(javafx.scene.input.MouseEvent event) {
        System.out.println(tvreclamation.getSelectionModel().getSelectedItem().getId());
        iditem = tvreclamation.getSelectionModel().getSelectedItem().getId();
        iduser = user.getSelectionModel().getSelectedItem().toString();
        int iduser1 = Integer.parseInt(iduser);
        idtype = type.getSelectionModel().getSelectedItem().toString();
        int idtype1 = Integer.parseInt(idtype);
        // tfid.setText(tvreclamation.getSelectionModel().getSelectedItem().getId() + "");
        titre.setText(tvreclamation.getSelectionModel().getSelectedItem().getTitre());
        description.setText(tvreclamation.getSelectionModel().getSelectedItem().getDescRec());
        btnupdate.setDisable(false);
        btndelete.setDisable(false);
    }

    @FXML
    private void updateReclamation() throws SQLException, IOException {
        ReclamationService r = new ReclamationService();

        if (!description.getText().equals("") || !titre.getText().equals("")) {
            Reclamation tr = new Reclamation(description.getText().toString(), titre.getText().toString());
            r.modifierR(tr, reclamation.getId());
            Alert alertA = new Alert(Alert.AlertType.NONE, "modifier avec sucees", ButtonType.APPLY);
            alertA.show();
            ReclamationList.clear();
            Connection conn = getConnection();
            String query = "SELECT * FROM reclamation";
            Statement st;
            ResultSet rs;
                       AnchorPane parent = FXMLLoader.load(getClass().getResource("/Gui/Reclamation.fxml"));
                       ap.getChildren().setAll(parent);

          try {
                ResultSet rs1 = conn.createStatement().executeQuery("SELECT * from `reclamation` ");
                    
                st = conn.createStatement();
                rs = st.executeQuery(query);
                Reclamation rec;
                while (rs.next()) {
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
            
        } else {
            Alert alertA = new Alert(Alert.AlertType.NONE, "champ vide", ButtonType.APPLY);
            alertA.show();
        }

    }
    
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    Statement stm;
    @FXML
       private void backRec(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Gui/ReclamationAcceuil.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void click(MouseEvent event) {
        reclamation = tvreclamation.getSelectionModel().getSelectedItem();
        
    }

   @FXML
    private void search(KeyEvent event) {
        ReclamationService rs = new ReclamationService();
        tvreclamation.setItems((ObservableList<Reclamation>) rs.search1(search.getText()));
    }
    }

    


