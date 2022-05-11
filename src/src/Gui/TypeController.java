/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.TypeReclamation;
import Services.TypeR;
import Utils.DataSource;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class TypeController implements Initializable {

    @FXML
    private TextField description;
    @FXML
    private TableView<TypeReclamation> tvtype;
    @FXML
    private TableColumn<TypeReclamation, Integer> colid;
    @FXML
    private TableColumn<TypeReclamation, String> coldescription;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    ObservableList<TypeReclamation> TypeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM type_reclamation";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            TypeReclamation type;
            while (rs.next()) {
                type = new TypeReclamation(rs.getInt("id"), rs.getString("description"));
                TypeList.add(type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colid.setCellValueFactory(new PropertyValueFactory<TypeReclamation, Integer>("id"));
        coldescription.setCellValueFactory(new PropertyValueFactory<TypeReclamation, String>("description"));
        tvtype.setItems(TypeList);
    }

    public Connection getConnection() {
        Connection cnx;
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            return cnx;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public void showType() throws SQLException {
        ObservableList<TypeReclamation> TypeList = FXCollections.observableArrayList();
        colid.setCellValueFactory(new PropertyValueFactory<TypeReclamation, Integer>("id"));
        coldescription.setCellValueFactory(new PropertyValueFactory<TypeReclamation, String>("description"));
        tvtype.setItems(TypeList);
    }

    @FXML
    private void insertType() throws SQLException {
        TypeR r = new TypeR();
        TypeReclamation tr = new TypeReclamation(description.getText().toString());
        if (!description.getText().equals("") ) {
            r.ajouterType(tr);
            Alert alertA = new Alert(Alert.AlertType.NONE, "ajout sucees", ButtonType.APPLY);
            alertA.show();
            TypeList.clear();
          Connection cnx = DataSource.getInstance().getCnx();
            String query = "SELECT * FROM type_reclamation";
            Statement st;
            ResultSet rs;
            try {
                st = cnx.createStatement();
                rs = st.executeQuery(query);
                TypeReclamation type;
                while (rs.next()) {
                    type = new TypeReclamation(rs.getInt("id"), rs.getString("description"));
                    TypeList.add(type);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TypeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            colid.setCellValueFactory(new PropertyValueFactory<TypeReclamation, Integer>("id"));
            coldescription.setCellValueFactory(new PropertyValueFactory<TypeReclamation, String>("description"));
            tvtype.setItems(TypeList);
        } else {
            Alert alertA = new Alert(Alert.AlertType.NONE, "champ vide", ButtonType.APPLY);
            alertA.show();
        }

    }
    
    
    int iditem;
    
    @FXML
    private void Clicked_item(javafx.scene.input.MouseEvent event) {
        System.out.println(tvtype.getSelectionModel().getSelectedItem().getId());
        iditem = tvtype.getSelectionModel().getSelectedItem().getId();
        
        description.setText(tvtype.getSelectionModel().getSelectedItem().getDescription());
        btnupdate.setDisable(false);
        btndelete.setDisable(false);
    }

    @FXML
    private void updateType() throws SQLException {
        TypeR r = new TypeR();

        if (!description.getText().equals("")) {
            TypeReclamation tr = new TypeReclamation(description.getText().toString());
            r.modifier(tr, iditem);
            Alert alertA = new Alert(Alert.AlertType.NONE, "modifier avec sucees", ButtonType.APPLY);
            alertA.show();
            TypeList.clear();
            Connection cnx = DataSource.getInstance().getCnx();
            String query = "SELECT * FROM type_reclamation";
            Statement st;
            ResultSet rs;
            try {
                st = cnx.createStatement();
                rs = st.executeQuery(query);
                TypeReclamation type;
                while (rs.next()) {
                    type = new TypeReclamation(rs.getInt("id"), rs.getString("description"));
                    TypeList.add(type);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TypeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            colid.setCellValueFactory(new PropertyValueFactory<TypeReclamation, Integer>("id"));
            coldescription.setCellValueFactory(new PropertyValueFactory<TypeReclamation, String>("description"));
            tvtype.setItems(TypeList);
        } else {
            Alert alertA = new Alert(Alert.AlertType.NONE, "champ vide", ButtonType.APPLY);
            alertA.show();
        }

    }

    @FXML
    private void deleteType() throws SQLException {
        TypeR r = new TypeR();
        r.deleteType(iditem);
        Alert alertA = new Alert(Alert.AlertType.NONE, "delete avec sucees", ButtonType.APPLY);
        alertA.show();
        TypeList.clear();
      Connection cnx = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM type_reclamation";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            TypeReclamation type;
            while (rs.next()) {
                type = new TypeReclamation(rs.getInt("id"), rs.getString("description"));
                TypeList.add(type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colid.setCellValueFactory(new PropertyValueFactory<TypeReclamation, Integer>("id"));
        coldescription.setCellValueFactory(new PropertyValueFactory<TypeReclamation, String>("description"));
        tvtype.setItems(TypeList);
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

//    private void search(KeyEvent event) {
//        TypeR r = new TypeR();
//        tvtype.setItems((ObservableList<TypeReclamation>) r.search(tf_search.getText()));
//    }
//
//    @FXML
//    private void search(ActionEvent event) {
//    }
    
    
}
