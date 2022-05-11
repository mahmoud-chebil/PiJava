/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Evenement2;
import Entities.Reservation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Lenovo
 */
public class EvenementServiceR implements IserviceE<Evenement2>{
    Connection con;
    Statement stm;
    
    
    
     public EvenementServiceR() {
    Connection cnx = DataSource.getInstance().getCnx();
    }
    public void ajouter(Evenement2 t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Evenement2> afficher() throws SQLException {
        
         String req = "SELECT * from `evenement`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        ObservableList<Evenement2> evenements =FXCollections.observableArrayList();
        while(rst.next()){
            Evenement2 even = new Evenement2(rst.getInt(1),rst.getFloat(2),rst.getString(3),rst.getString(4),rst.getInt(5),rst.getString(6),rst.getString(7));
            evenements.add(even);            
        }
        return evenements;
    }
       

    @Override
    public void Delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Evenement2 t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evenement2> afficherByIdUser(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validerReservation(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evenement2> afficherDevis() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
