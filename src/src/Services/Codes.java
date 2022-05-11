/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.User;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javafixauth.JavaFixAuth;
import static javafixauth.JavaFixAuth.RestpasswordID;
import static javafixauth.JavaFixAuth.loggedInID;

/**
 *
 * @author Fares
 */
public class Codes {
  
    Connection cnx = DataSource.getInstance().getCnx();
    
    
    public String envoyerCode(int id){
        Random r = new Random();
        return ""+r.nextInt(100)+id+r.nextInt(100);
         
       //return ;
    }
     public boolean update(String pwd,int id) throws SQLException {
        Statement st = cnx.createStatement();
        String qry = "UPDATE user SET password = '"+pwd+"' WHERE id = "+JavaFixAuth.loggedInID;
         
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
        return false;
    }
      public boolean getUserBy(String email) {
        String requete = "SELECT id,password FROM user"
                + " WHERE ( email = ? ) ";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                // (aa.hashagePWD(pwdId).equals(pwdBD)) 
               
                    int idUser = rs.getInt(1);
                    JavaFixAuth.RestpasswordID = idUser;
                    return true;
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
      public boolean codemail(String code,String email) {
          PreparedStatement stmt;
        
        try {
            
           String sql = "UPDATE  user SET mailcode= '"+code+"' WHERE ( email = ? ) ";
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, email);
           
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
     public User getByI() {
        String req = "select * from user where id =" + loggedInID;
        User p = new User();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setId(rs.getInt("id"));
            p.setUsername(rs.getString("username"));
            p.setEmail(rs.getString("email"));
            p.setPassword(rs.getString("password"));
            p.setRoles(rs.getString("roles"));
            p.setTelephone(rs.getString("telephone"));
            p.setImage(rs.getString("image"));
            p.setIs_verified(rs.getBoolean("is_verified"));
            p.setIs_expired(rs.getBoolean("is_expired"));
            p.setMailcode(rs.getString("mailcode"));

            //}  
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }
       public boolean Nullcodemail() {
          PreparedStatement stmt;
        
        try {
            
           String sql = "UPDATE  user SET mailcode= 'NULL' WHERE ( id = ? ) ";
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, ""+RestpasswordID);
           
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
       public boolean Verif() {
          PreparedStatement stmt;
        
        try {
            
           String sql = "UPDATE  user SET is_verifIed=" +true +" WHERE ( id = ? ) ";
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1,""+RestpasswordID );
           //""+RestpasswordID
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
}  

        
    
      

