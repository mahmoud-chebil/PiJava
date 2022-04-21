/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author azizm
 */
public class ServiceCartt implements IServiceCart<Cart>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Cart t) {
          try {
            String requete = "INSERT INTO cart (produit_id ,name) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
          pst.setInt(1, t.getProduit_id());
            pst.setString(2, t.getName());
            pst.executeUpdate();
            System.out.println("Produit Ajoutée a la Cart !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


        public int deleteCart(int id) throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM cart WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCartt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
public int deleteCartf() throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM cart";
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCartt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }


    @Override
    public List<Cart> afficher() {
          List<Cart> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM cart";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Cart(rs.getInt(1),rs.getInt(3),rs.getString(4))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void Delete(int id ) throws SQLException {
            try{
        String requete = "DELETE FROM cart WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Cart Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
            }

    }

    @Override
    public void modifier(Cart t, int id) throws SQLException {
        
      try {
            String requete = "UPDATE cart SET quantity=?, WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getQuantity());
            pst.executeUpdate();
            System.out.println("Cart Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   
 

    }

