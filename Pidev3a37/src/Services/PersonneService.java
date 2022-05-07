/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Personne;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macbook
 */
public class PersonneService implements IService<Personne> {

    Connection con;
    Statement stm;

    public PersonneService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void ajouter(Personne t) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`) VALUES ( '"
                + t.getNom() + "', '" + t.getPrenom() + "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public void ajouterr(Personne t) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`, `date`) VALUES (?,?,?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, t.getNom());
        pstm.setString(2, t.getPrenom());
        pstm.setDate(3, t.getDate());
        pstm.executeUpdate();
           
    }

    @Override
    public List<Personne> afficher() throws SQLException {
        String req = "Select * from `personne`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Personne> personnes = new ArrayList<Personne>();
        while(rst.next()){
            
            Personne p = new Personne(rst.getInt(1),rst.getString("nom"),rst.getString(3),rst.getDate("date"));
            personnes.add(p);
            
        
        }
        return personnes;
        
    }

}
