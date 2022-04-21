package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.CategoryProduct;
import utils.DataSource;

public class ServiceCategoryProductImpl implements IService<CategoryProduct>{
    Connection cnx = DataSource.getInstance().getCnx();

	@Override
	public void ajouter(CategoryProduct t) {
		try {
	        
            String requete = "INSERT INTO category_product (name) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getName());
            pst.executeUpdate();
            System.out.println("Categorie Produit ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
		
	}

	@Override
	public void supprimer(CategoryProduct t) {
		  try {
	            String requete = "DELETE FROM category_product WHERE id=" + t.getId();
	            Statement st = cnx.createStatement();
	            st.executeUpdate(requete);
	            System.out.println("Categorie Produit supprimé !");

	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }		
	}

	@Override
	public void modifier(CategoryProduct t) {
		 try {
	           

	            String requete = "UPDATE category_product SET name=? WHERE id=?";
	            PreparedStatement pst = cnx.prepareStatement(requete);
	            pst.setString(1, t.getName());
	            pst.setInt(2, t.getId());
	            pst.executeUpdate();

	            System.out.println("Categorie Produit  modifiée !");

	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }			
	}

	@Override
	public List<CategoryProduct> afficher() {
		 List<CategoryProduct> list = new ArrayList<>();

	        try {
	            String requete = "SELECT * FROM category_product";
	            PreparedStatement pst = cnx.prepareStatement(requete);
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	                list.add(new CategoryProduct(rs.getInt(1), rs.getString(2)));
	            }
	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }

	        return list;
	}
        
         public List getCategoryNameAndId(){
            List<String> res=new ArrayList<>();
           try {
            String requete = "SELECT id,name FROM category_product";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                res.add(rs.getString(1)+"-"+rs.getString(2));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           return res;

    }

}
