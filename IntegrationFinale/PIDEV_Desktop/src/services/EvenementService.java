/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Evenement;
import Entities.Activite;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author MSI
 */
public class EvenementService {
       Connection cnx = DataSource.getInstance().getCnx();
    
    //Affichage
    
     public ObservableList<Evenement> afficherEvenement() {
        ObservableList<Evenement> list =FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               list.add(new Evenement(rs.getInt("id"),rs.getInt("prix_evenement"),rs.getString("description_evenement"),rs.getString("titre_evenement"),rs.getInt("nbre_places"),
               rs.getDate("date_debut"),rs.getDate("date_fin"), rs.getInt("tel"), rs.getString("tel") , rs.getInt("id_activite")));
            
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
        
     }
     
    private Statement ste;
    private PreparedStatement pst;
    
    
        public List<Activite> affichertAct(int id) {
        List<Activite> list = new ArrayList<>();
        List<Evenement> list1 = new ArrayList<>();
        try {

            
            String requete1 = "SELECT id_activite FROM evenement  where id="+id+"";
            Statement st1 = cnx.createStatement();
            ResultSet rs1 = st1.executeQuery(requete1);
             while (rs1.next()) {
             int idt=rs1.getInt("id_activite");
          
            String requete2 = "SELECT * FROM activite  where id_activite="+idt+"";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(requete2);
            while (rs2.next()) {
              list.add(new Activite(rs2.getString("nom"),rs2.getString("type"),rs2.getInt("prix")));
            } }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
      }
        

    
    //Ajout
    
    
    public void ajouterEvenement(Evenement r) throws  UnsupportedEncodingException, IOException {
          try {
                       
            String req = "INSERT INTO evenement (id,prix_evenement,description_evenement,titre_evenement,nbre_places,date_debut,date_fin,tel,email,id_activite) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, r.getId());
            pre.setInt(2, r.getPrix_evenement());
            pre.setString(3, r.getDecription_evenement());
            pre.setString(4, r.getTitre_evenement());
            pre.setInt(5, r.getNbre_places());
            pre.setDate(6, r.getDate_debut());
            pre.setDate(7, r.getDate_fin());
            pre.setInt(8, r.getTel());
            pre.setString(9,r.getEmail());
            pre.setInt(10,r.getId_activite());

            
           
  
            pre.executeUpdate();
            System.out.println("Evenement Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    
}
    
    
    //supprimer 
    
    public int supprimerEvenement(int id) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE FROM `evenement` WHERE id="+id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
    
    
    
    //modifiers
    
    public void modifierEvenement(Evenement e) throws  UnsupportedEncodingException, IOException {
      try {
            String req = "UPDATE `evenement` SET prix_evenement=?,description_evenement=?,titre_evenement=?,nbre_places=?,date_debut=?,date_fin=?,tel=?,email=?,id_activite=? WHERE `id`=?";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, e.getPrix_evenement());
            pre.setString(2, e.getDecription_evenement());
            pre.setString(3, e.getTitre_evenement());
            pre.setInt(4, e.getNbre_places());
            pre.setDate(5, e.getDate_debut());
            pre.setDate(6, e.getDate_fin());
            pre.setInt(7, e.getTel());
            pre.setString(8, e.getEmail());
            pre.setInt(9,e.getId_activite());
            pre.setInt(10, e.getId());
            pre.executeUpdate();
            System.out.println("evenement Modifié !");
           } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
    
    
    public List<Evenement> rechercheEvenement(String titre_evenement ) {
    List<Evenement> list = new ArrayList<>();
    try {
    String requete = "SELECT * FROM evenement WHERE titre_evenement='"+titre_evenement+"' ";
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(requete);
    while (rs.next()) {
    list.add(new Evenement(rs.getInt("id"),rs.getInt("prix_evenement"),rs.getString("decription_evenement"),rs.getString("titre_evenement"),rs.getInt("nbre_places"),
    rs.getDate("date_debut"),rs.getDate("date_fin"), rs.getInt("tel"), rs.getString("tel")));
    
    }
    } catch(SQLException ex) {
    System.err.println(ex.getMessage());
    }
    
    return list;
    
    }
    public List<Evenement> displayAllAfterSearch(String s) {
           Connection cnx = DataSource.getInstance().getCnx();

        String req="select * from evenement where titre_evenement like '%"+s+"%'";
        List<Evenement> list=new ArrayList<>();       
        
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while(rst.next()){
                Evenement p=new Evenement();
                p.setId(rst.getInt("id"));
                p.setPrix_evenement(rst.getInt("prix_evenement"));
                p.setDecription_evenement(rst.getString("description"));
                p.setTitre_evenement(rst.getString("titre_evenement"));
                p.setNbre_places(rst.getInt("Nbre_places"));
                p.setDate_debut(rst.getDate("date_debut"));
                p.setDate_fin(rst.getDate("date_fin"));
                p.setTel(rst.getInt("tel"));
                p.setEmail(rst.getString("email"));
                p.setId_activite(rst.getInt("id_activite"));
               
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   /*     public void qr(Evenement r) throws WriterException, UnsupportedEncodingException, IOException{
         String qrCodeData = r.getTitre_evenement();
            String filePath = "C:\\Users\\MSI\\Desktop\\TuniJava\\TuniDiscoveryD\\src\\qr\\Evenementqr.png";
            String charset = "UTF-8"; 
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
            new String(qrCodeData.getBytes(charset), charset),
            BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
             .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
            
            
    }*/
        
}
