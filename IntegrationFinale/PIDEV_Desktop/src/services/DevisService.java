/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Devis;
import Entities.Evenement;
import utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lenovo
 */
public class DevisService implements IserviceE<Devis>{
   // Connection con;
    Statement stm;
 Connection con = DataSource.getInstance().getCnx();
    public DevisService() {
            Connection cnx = DataSource.getInstance().getCnx();
    }

    
     @Override
    public ObservableList<Devis> afficherDevis() throws SQLException {
    
        String req = "SELECT * from `devis`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        ObservableList<Devis> devis =FXCollections.observableArrayList();
        while(rst.next()){
            Devis d = new Devis(rst.getInt(1),rst.getFloat(3),rst.getFloat(4),rst.getInt(2));
            devis.add(d);            
        }
        return devis;
           
    }

    @Override
    public void ajouter(Devis t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Devis> afficher() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public List<Devis> afficherByIdUser(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validerReservation(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Devis t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
