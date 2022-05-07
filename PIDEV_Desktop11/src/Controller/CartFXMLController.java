/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javafx.stage.Stage;
import models.*;

import services.ServiceCartt;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class CartFXMLController implements Initializable {

    @FXML
    private Button btnv;
    @FXML
    private TableView<Product> table_produit;
    @FXML
    private TableView<Cart> table_cart;
    @FXML
    private ComboBox<String> com1;
    @FXML
    private ComboBox<Integer> com2;
    @FXML
    private Button btns;
    @FXML
    private Button btnm;
    
        @FXML
    private TableColumn<Product, String> tprix;
    @FXML
    private TableColumn<String, String> tnom;
    ObservableList<Product> listS = FXCollections.observableArrayList();
    ObservableList<Cart> list1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Cart, Integer> col_id;
    @FXML
    private TableColumn<Cart, String> col_nom;
    @FXML
    private TableColumn<Cart, Integer> col_q;
    @FXML
    private TextField ftid;
    @FXML
    private Button back;
    private Stage stage;
     private Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ResultSet rs1 = null;
            
              
            Connection cnx = DataSource.getInstance().getCnx();
        try {
            rs1 = cnx.createStatement().executeQuery("SELECT name FROM product");
        } catch (SQLException ex) {
            Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs1.next()) {  try {
                // loop
                
                // Now add the comboBox addAll statement
                com1.getItems().addAll(rs1.getString("name"));
            } catch (SQLException ex) {
                Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            com2.getItems().addAll(1,2,3,4,5);
             
            Connection cnx1 = DataSource.getInstance().getCnx();
            ResultSet rs = null;
        try {
            rs = cnx1.createStatement().executeQuery("SELECT name,price FROM product");
        } catch (SQLException ex) {
            Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                listS.add(new Product(rs.getString(1),rs.getString(5)));
                
                table_produit.setItems(listS);
                table_produit.refresh();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Connection cnx1 = DataSource.getInstance().getCnx();*/
            ResultSet rs2 = null;
        try {
            rs2 = cnx.createStatement().executeQuery("SELECT * FROM cart");
        } catch (SQLException ex) {
            Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs2.next()){
          list1.add(new Cart(rs2.getInt(1),rs2.getString(5)));
         
          col_nom.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_q.setCellValueFactory(new PropertyValueFactory<>("name"));
         
          
                table_cart.setItems(list1);
                
                table_cart.refresh();
                
            }   } catch (SQLException ex) {    
            Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void PasserCommande(ActionEvent event) throws SQLException {
         Connection cnx = DataSource.getInstance().getCnx();
        
        int j =com2.getValue();
        String i =com1.getValue();
        ServiceCartt sr = new ServiceCartt();
        
       // sr.ajouter(new Cart(j,i));

        JOptionPane.showMessageDialog(null,"Produit Ajoutée a la Cart !");
        UpdateTable();
    }
 
         @FXML
    private void SupprimerCart(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Cart x = gettempCart(edittedcell);

        if (x != null) {

            int i = x.getId();
            ServiceCartt cat = new ServiceCartt();

            int s = cat.deleteCart(i);
            

            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Produit deleted");
                alert.showAndWait();
                table_cart.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
          UpdateTable();
    }
    public Cart gettempCart(TableColumn.CellEditEvent edittedCell) {
        Cart test = table_cart.getSelectionModel().getSelectedItem();
        return test;
    }
        
    

    @FXML
    private void ModifierQuantite()  throws SQLException {
             try {
            
            Connection cnx = DataSource.getInstance().getCnx();
            
            String value1 =com1.getValue();
            int value2 =com2.getValue();
            int value3 = Integer.parseInt(ftid.getText());
            String sql ="update cart set quantity= '"+value2+"' where id='"+value3+"' ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst= cnx.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Updated");
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
         UpdateTable(); 
        
    }
     private void afficherproduit(){
       
         
            tnom.setCellValueFactory(new PropertyValueFactory<>("name"));
            tprix.setCellValueFactory(new PropertyValueFactory<>("price"));

            table_produit.setItems(listS);
            
            
            
    }

   @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = table_cart.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    com2.setValue(col_q.getCellData(index));
    ftid.setText(col_id.getCellData(index).toString());
    
    
    
    }
        public void UpdateTable() throws SQLException{
            
        col_id.setCellValueFactory(new PropertyValueFactory<Cart,Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("produit_id"));
        col_q.setCellValueFactory(new PropertyValueFactory<>("quantity"));
      

         list1 = DataSource.getDataCart();
        
        table_cart.setItems(list1);
        }
    private Cart getIdCart(int id) {
        System.out.println(id);
   Cart cart = null;
    Connection cnx = DataSource.getInstance().getCnx();
    String query ="select * from cart where id ='"+id+"'";
    Statement st;
    ResultSet rs;
            try{
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            System.out.print(rs);
            
            while(rs.next()){
                  cart = new Cart(rs.getInt("id"), rs.getInt("quantity"), rs.getInt("produit_id"));
               
                          

            }    
        }catch(Exception ex){
            ex.printStackTrace();
        }
            System.out.println(cart);
    return cart;
    }

    @FXML
    private void backk(ActionEvent event) throws IOException  {
        
   Parent root = FXMLLoader.load(getClass().getResource("/Gui/GestionGamesFXML.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 
    }
    @FXML
    private void Passer(ActionEvent event) throws IOException {
         Connection cnx = DataSource.getInstance().getCnx();

      
        Parent root = FXMLLoader.load(getClass().getResource("/views/commande/form.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
   
 
        
}

