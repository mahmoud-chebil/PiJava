/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Static;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class BD {
     public String url="jdbc:mysql://localhost:3306/tuni";
    public String user="root";
    public String pwd="";
    public static BD cn;
    private Connection cnx;
    public BD(){
        try {
            System.out.println("Connexion établie");
            cnx=DriverManager.getConnection(url, user, pwd);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static BD getInstance(){
        if(cn==null)
            cn= new BD();
            return cn;
      
    }

    public Connection getCnx() {
         
        return cnx;
    }
    
}
