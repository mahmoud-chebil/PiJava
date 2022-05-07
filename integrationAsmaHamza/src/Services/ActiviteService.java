/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activite;
import Entities.Evenement;
import Utils.MyDB;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI
 */
public class ActiviteService {
    Connection cnx = MyDB.getInstance().getCon();
    
    public ObservableList<Activite> afficherActivite() {
        //List<Activite> list = new ArrayList<>();
        ObservableList<Activite> list =FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM activite";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               list.add(new Activite(rs.getInt("id"),rs.getString("nom_activite"),rs.getString("type_activite"),rs.getInt("prix_activite")));
             
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
        
    
}
    private Statement ste;
    private PreparedStatement pst;
    
    public void ajouterActivite(Activite act) throws  UnsupportedEncodingException, IOException {
          try {
                       
            String req = "INSERT INTO activite (nom_activite,type_activite,prix_activite) values (?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setString(1, act.getNom_activite());
            pre.setString(2, act.getType_activite());
            pre.setInt(3, act.getPrix_activite());
            pre.executeUpdate();
            System.out.println("Activité Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    
    }
    public void modifierActivite(Activite act) throws SQLException{
        try{
            String req = "UPDATE `activite` SET nom_activite=?,type_activite=?,prix_activite=? WHERE `id`=?";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setString(1, act.getNom_activite());
            pre.setString(2, act.getType_activite());
            pre.setInt(3, act.getPrix_activite());
            pre.setInt(4, act.getId());
            
            
            
            pre.executeUpdate();
            System.out.println("Activite Modifié !");
        }
        catch(SQLException ex){
            
            System.err.println(ex.getMessage());
            
        }
    }
    public int SupprimerActivite(int id) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE FROM `activite` WHERE id="+id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
    
    public List getActiviteNometdId(){
            List<String> res=new ArrayList<>();
           try {
            String requete = "SELECT id,nom_activite FROM activite";
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
    
        

        
        public List getNomActivite(){
            List<String> res=new ArrayList<>();
           try {
            String requete = "SELECT nom_activite FROM activite";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                res.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           return res;

    }
}
