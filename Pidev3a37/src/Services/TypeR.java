/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TypeReclamation;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mariem
 */
public class TypeR implements IType<TypeReclamation> {

    Connection con;
    Statement stm;

    public TypeR() {
        con = MyDB.getInstance().getCon();

    }

    @Override
    public List<TypeReclamation> afficherT() throws SQLException {

        String req = "Select * from `type_reclamation`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<TypeReclamation> types = new ArrayList<TypeReclamation>();
        while (rst.next()) {

            TypeReclamation p = new TypeReclamation(rst.getInt(1), rst.getString("description"));
            types.add(p);

        }

        return types;

    }

    @Override
    public void ajouterType(TypeReclamation t) throws SQLException {
        PreparedStatement pst;
        int id;
        String sql = "INSERT INTO type_reclamation (description)VALUES(?) ";
        pst = con.prepareStatement(sql);

        pst.setString(1, t.getDescription());
        pst.executeUpdate();

    }

    @Override
    public void deleteType(int id) throws SQLException {
        PreparedStatement pst;
        String sql = "DELETE FROM `type_reclamation` WHERE id =" + id;
        pst = con.prepareStatement(sql);

        pst.executeUpdate();

    }

    @Override
    public void modifier(TypeReclamation t, int id) throws SQLException {
        String req = "UPDATE type_reclamation SET  description = ? where id= " + id;
        PreparedStatement pre;

        pre = con.prepareStatement(req);

        pre.setString(1, t.getDescription());

        pre.executeUpdate();

    }

    public List<TypeReclamation> search(String x) {
        
        ObservableList<TypeReclamation> ListType = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM type_reclamation where description like ? ";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, "%" + x + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ListType.add(new TypeReclamation(rs.getString("description")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return ListType;
    }
    
    

}
