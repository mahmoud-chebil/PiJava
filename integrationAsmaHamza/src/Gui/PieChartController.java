/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class PieChartController implements Initializable {

    @FXML
    private PieChart pieChart;
    
    ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
    Connection cnx = MyDB.getInstance().getCon();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            String requete = "SELECT titre_evenement, prix_evenement  FROM evenement  ORDER by prix_evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
           // ResultSet rs = DB.createStatement().executeQuery("SELECT nom_activite,id,COUNT(id) FROM activite  group by id");
            
            
            while(rs.next()){
                list.add(new PieChart.Data(rs.getString("titre_evenement"),rs.getInt(2)));
        }
        
        pieChart.setData(list);
        
    }
        catch(SQLException ex){
    
        // TODO
    }  
        
    }
    
}
