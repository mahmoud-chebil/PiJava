/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;
import utils.DataSource;

/**
 *
 * @author Mohamed
 */
public class PanierService {

Connection cnx = DataSource.getInstance().getCnx();
    public void addProduct( int productId) throws SQLException {
       
        
            String req = "insert into panier (produit_id, quantite) values(?, ?);";
            try {
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, productId);          
                pst.setInt(3, 1);
                pst.executeUpdate();
                System.out.println("produit ajoutée au panier!");
            } catch (SQLException ex) {
            }
       /* } else {
            String req = "update panier set quantite = ? and produit_id = ?";
            try {
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, getQtOfProduct(userId, productId) + 1);

                pst.setInt(3, productId);
                pst.executeUpdate();
                System.out.println("qt mise up");
            } catch (SQLException ex) {
            }
        }*/

    }

    public boolean testDuplication(int userId, int produitId) throws SQLException {
        String req = "select * from panier where produit_id = ?";
        PreparedStatement pst = cnx.prepareStatement(req);

        pst.setInt(2, produitId);
        ResultSet rs = pst.executeQuery();
        System.out.println("############");
        if (rs.next()) {
            return false;
        }
        return true;
    }

    public int getQtOfProduct(int userId, int produitId) throws SQLException {
        int qt = 0;
        String req = "select quantite from panier where produit_id = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(2, produitId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            qt = rs.getInt(1);

        }
        return qt;
    }

   
    

    public void removeProductFromBasket(int basketId) {
        String req = "delete from panier where id = ?;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, basketId);
            pst.executeUpdate();
            System.out.println("produit supprimée du panier!");
        } catch (SQLException ex) {
        }
    }

    public void incQt(int basketId) {
        System.out.println("inc qt");
        String req = "update panier set quantite = ? where id = ?;";
        try {
            System.out.println("0");
            PreparedStatement pst = cnx.prepareStatement(req);
            System.out.println("1");
            pst.setInt(1, getQtOfProduct2(basketId) + 1);
            pst.setInt(2, basketId);
            System.out.println("2");
            pst.executeUpdate();
            System.out.println("3");
            System.out.println("qt ++");
            System.out.println("4");
        } catch (SQLException ex) {
        }
    }

    public void decQt(int basketId) throws SQLException {

        if (getQtOfProduct2(basketId) == 1) {
            removeProductFromBasket(basketId);
        } else {
            String req = "update panier set quantite = ? where id = ?;";
            try {
                System.out.println("0");
                PreparedStatement pst = cnx.prepareStatement(req);
                System.out.println("1");
                pst.setInt(1, getQtOfProduct2(basketId) - 1);
                pst.setInt(2, basketId);
                System.out.println("2");
                pst.executeUpdate();
                System.out.println("3");
                System.out.println("qt ++");
                System.out.println("4");
            } catch (SQLException ex) {
            }
        }

    }

    public int getQtOfProduct2(int basketId) throws SQLException {
        int qt = 0;
        System.out.println("00");
        String req = "select quantite from panier where id = ?";
        System.out.println("11");
        PreparedStatement pst = cnx.prepareStatement(req);
        System.out.println("22");
        pst.setInt(1, basketId);
        System.out.println("33:" + basketId);
        ResultSet rs = pst.executeQuery();
        System.out.println("44");
        while (rs.next()) {
            System.out.println("55");
            qt = rs.getInt(1);
        }
        System.out.println("66");
        return qt;
    }
}
