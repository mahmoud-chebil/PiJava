/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Stock;
import Models.User;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fares
 */
public class ServiceStock implements Sservice<Stock> { 
    
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Stock t) {
        try {
            String req = "INSERT INTO `Stock` (`Name`, `quantity`,`rest_q`) VALUES ('" +t.getName() + "', '" 
                    + t.getQuantity()+"', '" + t.getRest_q()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    

    @Override
    public List<Stock> getAll() {
        List<Stock> list = new ArrayList<>();
     try {
         String req = "Select * from Stock";
         Statement st = cnx.createStatement();
         ResultSet rs = st.executeQuery(req);
         while(rs.next()){
             Stock u = new Stock (rs.getInt("id"),
                     rs.getInt("Rest_q"),rs.getInt("Quantity"),rs.getString("Name"));
             list.add(u);
         }
     } catch (SQLException ex) {
         System.err.println(ex.getMessage());
     }
     return list;
    }

    @Override
    public void modifier(Stock t) {
        PreparedStatement stmt;
	try {

	    String sql = "UPDATE Stock SET name=? ,Quantity=?,Rest_q=? WHERE id=?";
	    stmt = cnx.prepareStatement(sql);
	    stmt.setString(1, t.getName());
	    stmt.setInt(2, t.getQuantity());
	    stmt.setInt(3, t.getRest_q());
            stmt.setInt(4, t.getId());
	    stmt.executeUpdate();
              
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
     public Stock getById(int id) {
         String req="select * from Stock where id ="+id;
        Stock p=new Stock();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setRest_q(rs.getInt("Rest_q"));
               
                //}  
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }

    @Override
    public void supprimer(Stock t) {
        
        String req="delete from Stock where id="+t.getId();
        Stock user =getById(t.getId());
       
          if(user!=null)
              try {
             Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
                  System.out.println(ex);
        }else System.out.println("n'existe pas");
    }
    
    }

   
    

