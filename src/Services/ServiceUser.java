/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.User;
import Utils.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafixauth.JavaFixAuth;
import static javafixauth.JavaFixAuth.RestpasswordID;
import sun.applet.Main;
import java.net.MalformedURLException;
import java.io.IOException;
/**
 *
 * @author Fares
 */

public class ServiceUser implements Iservice<User> {
    
    
    private Statement st ;
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(User t) {
        try {
            String req = "INSERT INTO `user` (`username`, `email`,`password`,`roles`,`is_verified`,`is_expired`,`mailcode`,`telephone`,`image`) VALUES ('" + t.getUsername() + "', '"
                    + t.getEmail() + "', '" + t.getPassword() + "','" + t.getRoles() + "','" + t.getIsVerified()+"','" + t.getIsExpired()+"','" + t.getMailcode()+"', '" + t.getTelephone()+"', '"+t.getImage() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public User getById() {
        String req = "select * from `user` where id =" + JavaFixAuth.loggedInID;
        
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

            //}  
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }

    public User login(String email, String password) {
        String req = "SELECT * FROM `user` WHERE email = " + "email" + " AND password = " + "password";
        User p = new User();
        //System.out.println(p);
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs = st.executeQuery(req);

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

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("telephone"),
                        rs.getString("email"), rs.getString("password"), rs.getString("image"),
                        rs.getString("roles"), rs.getBoolean("is_verified"), rs.getBoolean("is_expired"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void modifier(User t) {
        PreparedStatement stmt;
        try {

            String sql = "UPDATE user SET username=? ,telephone=?,email=?,password=?,image=? WHERE id=?";
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, t.getUsername());
            stmt.setString(2, t.getTelephone());
            stmt.setString(3, t.getEmail());
            stmt.setString(4, t.getPassword());
            stmt.setString(5, t.getImage());
            stmt.setInt(6, t.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void Bann(User t) {
        PreparedStatement stmt;
        try {

            String sql = "UPDATE user SET is_expired=?  WHERE id=?";
            stmt = cnx.prepareStatement(sql);
            stmt.setBoolean(1, t.isIs_expired());
            stmt.setInt(2, t.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public User getByI(int id) {
        String req = "select * from user where id =" + id;
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

            //}  
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }

    @Override
    public void supprimer(User u) {

        String req = "delete from user where id=" + u.getId();
        User user = getByI(u.getId());

        if (user != null) {
            try {
                Statement st = cnx.createStatement();
                st.executeUpdate(req);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    public void supprimer1(int id) {

        String req = "delete from user where id=?";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Personne suprimée");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }

    }
    
//     String req = "delete from personne where id_personne=?";
//        PreparedStatement pst;
//        try {
//            pst = cnxx.prepareStatement(req);
//            pst.setInt(1, id_personne);
//            pst.executeUpdate();
//            System.out.println("Personne suprimée");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }

    
    
    

    public boolean getUserBy(String email, String pwdId) {
        String requete = "SELECT id,password FROM user"
                + " WHERE ( email = ? ) ";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String pwdBD = rs.getString(2);
                // (aa.hashagePWD(pwdId).equals(pwdBD)) 
                if (pwdId.equals(pwdBD)) {
                    int idUser = rs.getInt(1);
                    JavaFixAuth.loggedInID = idUser;
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean Password(String password) {
          PreparedStatement stmt;
        
        try {
            
           String sql = "UPDATE  user SET password= '"+password+"' WHERE ( id = ? ) ";
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, ""+RestpasswordID);
           
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
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
   
private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(Main.class.getName());
  
    
    public String encrypt_password(String Username,String Email,String pwd)
        {
            
            BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try{
            URL url = new URL("http://127.0.0.1:8000/encrypt_pass?username="+Username+"&email="+Email+"&password="+pwd+"");
            conn = (HttpURLConnection) url.openConnection();
            
            // Request setup
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
            conn.setReadTimeout(5000);
            
            // Test if the response from the server is successful
            int status = conn.getResponseCode();
            
            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            log.info("response code: " + status);
            System.out.println(responseContent.toString());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            conn.disconnect();
                        return responseContent.toString();
        }
        }
      private static HttpURLConnection conn;
      
      
    public String loginrequest(String Email,String pwd)
        {
            
            BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try{
            URL url = new URL("http://127.0.0.1:8000/loginjava?email="+Email+"&password="+pwd+"");
            conn = (HttpURLConnection) url.openConnection();
            
            // Request setup
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
            conn.setReadTimeout(5000);
            
            // Test if the response from the server is successful
            int status = conn.getResponseCode();
            
            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            log.info("response code: " + status);
            System.out.println(responseContent.toString());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            conn.disconnect();
                        return responseContent.toString();
        }
        }
   
    
}
