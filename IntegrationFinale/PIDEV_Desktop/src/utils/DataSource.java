
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;
/**
 *
 * @author MSI
 */
public class DataSource {

    private static DataSource instance;
    private Connection cnx;

    private final String URL = "jdbc:mysql://localhost:3306/fin";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
 public static ObservableList<Cart> getDataCart(){
        Connection conn = DataSource.instance.getCnx();
        ObservableList<Cart> list1 = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from cart");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list1.add(new Cart( rs.getString("name"),rs.getInt("quantity"),rs.getString("price"),rs.getInt("id")));               
            }
        } catch (Exception e) {
        }
        return list1;
    }
    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
