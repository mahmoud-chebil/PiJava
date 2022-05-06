/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.Reservation;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lenovo
 */
public class EvenementService implements IService<Evenement>{
    Connection con;
    Statement stm;
    
    
    
     public EvenementService() {
        con = MyDB.getInstance().getCon();
    }
    public void ajouter(Evenement t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Evenement> afficher() throws SQLException {
        
         String req = "SELECT * from `evenement`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        ObservableList<Evenement> evenements =FXCollections.observableArrayList();
        while(rst.next()){
            Evenement even = new Evenement(rst.getInt(1),rst.getFloat(2),rst.getString(3),rst.getString(4),rst.getInt(5),rst.getString(6),rst.getString(7));
            evenements.add(even);            
        }
        return evenements;
    }
       

    @Override
    public void Delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Evenement t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evenement> afficherByIdUser(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validerReservation(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evenement> afficherDevis() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
