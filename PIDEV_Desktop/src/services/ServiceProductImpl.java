package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Product;
import utils.DataSource;

public class ServiceProductImpl implements IService<Product> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Product t) {
        try {

            String requete = "INSERT INTO product (name,price,description,category_id) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, t.getName());
            pst.setString(2, t.getPrice());
            pst.setString(3, t.getDescription());
            pst.setInt(4, t.getCategory_id());
            pst.executeUpdate();
            ResultSet RSid = pst.getGeneratedKeys();
            RSid.next();
            int IdProduct = RSid.getInt(1);
            String requete1 = "INSERT INTO images (image_name) VALUES (?)";
            PreparedStatement pst1 = cnx.prepareStatement(requete1, Statement.RETURN_GENERATED_KEYS);
            pst1.setString(1, t.getFileName());
            pst1.executeUpdate();
            ResultSet RSid1 = pst1.getGeneratedKeys();
            RSid1.next();
            int IdImage = RSid1.getInt(1);
            String requete2 = "INSERT INTO product_images (product_id,images_id) VALUES (?,?)";
            PreparedStatement pst2 = cnx.prepareStatement(requete2);
            pst2.setInt(1, IdProduct);
            pst2.setInt(2, IdImage);

            pst2.executeUpdate();
            System.out.println("Produit ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Product t) {
        try {
            String requete = "DELETE FROM product WHERE id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Product t) {
        try {

            String requete = "UPDATE product SET name=?,price=?,description=?,category_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getName());
            pst.setString(2, t.getPrice());
            pst.setString(3, t.getDescription());
            pst.setInt(4, t.getCategory_id());
            pst.setInt(5, t.getId());
            pst.executeUpdate();
            if (!t.getFileName().equals("")) {
                System.out.println("Yes");
                String requete1 = "INSERT INTO images (image_name) VALUES (?)";
                PreparedStatement pst1 = cnx.prepareStatement(requete1, Statement.RETURN_GENERATED_KEYS);
                pst1.setString(1, t.getFileName());
                pst1.executeUpdate();
                ResultSet RSid1 = pst1.getGeneratedKeys();
                RSid1.next();
                int IdImage = RSid1.getInt(1);
                String requete2 = "INSERT INTO product_images (product_id,images_id) VALUES (?,?)";
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                pst2.setInt(1, t.getId());
                pst2.setInt(2, IdImage);

                pst2.executeUpdate();
            }

            System.out.println("Produit modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Product> afficher() {
        List<Product> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM product";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), this.getCategoryName(rs.getInt(2)),this.getFileName(rs.getInt(1))));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public List<Product> recherche(String crt) {
        List<Product> list = new ArrayList<>();

        try {
            crt = "'%" + crt + "%'";
            String requete = "select * from product WHERE name LIKE " + crt + " OR description LIKE " + crt + " OR price LIKE " + crt;
            PreparedStatement pst = cnx.prepareStatement(requete);
            cnx.prepareStatement(requete).executeQuery();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                   list.add(new Product(rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), this.getCategoryName(rs.getInt(2)),this.getFileName(rs.getInt(1))));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public String getCategoryName(int id) {
        String title = "";
        try {
            String requete = "SELECT name FROM category_product where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                title = rs.getString(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return title;

    }

    public String getFileName(int id) {
        String name = "";
        try {
            int image_id = 0;
            String requete = "SELECT images_id FROM product_images where product_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                image_id = rs.getInt(1);
            }

            String requete1 = "SELECT image_name FROM images where id=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setInt(1, image_id);
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next()) {
                name = rs1.getString(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return name;
    }

}
