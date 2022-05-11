/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
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
import javafx.fxml.FXML;
import javax.swing.JOptionPane;
import models.Cart;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.mail.*;
import models.Commande;
import models.Mail;
import utils.DataSource;


/**
 *
 * @author poste 1
 */

   
public class ServiceComImpl implements IServicegh<Commande> {
 String title = "";
 float total=0;
    Connection cnx = DataSource.getInstance().getCnx();
int x=5;
    @Override
    public void ajouter(Commande t) {
        
       String s= getProductName();
       float to = gettot();
       
     try {
         settot(to, t.getId());
     } catch (SQLException ex) {
         Logger.getLogger(ServiceComImpl.class.getName()).log(Level.SEVERE, null, ex);
     }
     try {
         setpr(s,t.getId());
     } catch (SQLException ex) {
         Logger.getLogger(ServiceComImpl.class.getName()).log(Level.SEVERE, null, ex);
     }
     t.setTotal(to);
     t.setDetailspro(s);
        try {

            String requete = "INSERT INTO commande (nom, prenom,adressecomplet,telephone,total,email,produits) values ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdressecomplet()+"','"+t.getTelephone()+"','"+t.getTotal()+"','"+t.getEmail()+"','"+t.getDetailspro()+"')";
              
           Statement st;
               st = cnx.createStatement();
               st.executeUpdate(requete);
            System.out.println("Commande ajoutée !");

           
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
   public  void supprimer(int id) 
              {
                     try {
               cnx.createStatement().execute("Delete from commande where id="+id+";");
               
        } catch (SQLException ex) {
            System.err.println("Error d'suppression"+ex);
        }
              
              }

    @Override
    public void modifierf(Commande t,int idf) {
        try {
   Statement statement= cnx.createStatement();
  
            String requete = "UPDATE commande SET nom='"+t.getNom()+"' ,  prenom='"+t.getPrenom()+"'  , adressecomplet='"+t.getAdressecomplet()+"' , telephone='"+t.getTelephone()+"' ,  email='"+t.getEmail()+"' ,  etat='"+t.getEtat()+"' where id='"+idf+"' ";
            statement.executeUpdate(requete);
         //   String requete = "UPDATE commande SET `nom`=?, `prenom`=? , `adressecomplet`=?, `telephone`=?, `email`=? WHERE `id`=?";
          //  PreparedStatement pst = cnx.prepareStatement(requete);
              
          /*  pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getAdressecomplet());
            pst.setString(5, t.getTelephone());
            pst.setString(6, t.getEmail());
          
            pst.executeUpdate();*/
          
            
            System.out.print("Updated !!");
 
            /*    String requete2 = "INSERT INTO commande_product (commande_id,product_id) VALUES (?,?)";
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                pst2.setInt(1, t.getId());
                pst2.setInt(2, t.getIdpro());

                pst2.executeUpdate();
            */

            System.out.println("commande modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
       
 @Override
    public void modifierr(Commande t,int idf) {
        try {
   Statement statement= cnx.createStatement();
  
            String requete = "UPDATE commande SET etat='"+t.getEtat()+"' where id='"+idf+"' ";
            statement.executeUpdate(requete);
         //   String requete = "UPDATE commande SET `nom`=?, `prenom`=? , `adressecomplet`=?, `telephone`=?, `email`=? WHERE `id`=?";
          //  PreparedStatement pst = cnx.prepareStatement(requete);
              
          /*  pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getAdressecomplet());
            pst.setString(5, t.getTelephone());
            pst.setString(6, t.getEmail());
          
            pst.executeUpdate();*/
          
            
            System.out.print("Updated !!");
 
            
            
            /*    String requete2 = "INSERT INTO commande_product (commande_id,product_id) VALUES (?,?)";
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                pst2.setInt(1, t.getId());
                pst2.setInt(2, t.getIdpro());

                pst2.executeUpdate();
            */

            System.out.println("commande modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
Statement st;
    
 @Override
    public List<Commande>  afficher(){
        List<Commande> ListR = new ArrayList();
        
        try {
            String req = "Select * from `commande`";
            st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
             
            while(rst.next()){
                 
                 Commande p = new Commande(rst.getInt("id"),rst.getString("nom"),rst.getString("prenom"),rst.getString("adressecomplet"),rst.getString("telephone"),rst.getFloat("total"),rst.getString("email"),rst.getString("produits"));
                 ListR.add(p);
            }
            
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
          return ListR ;
    }


    public List<Commande>  afficherad(){
        List<Commande> ListR = new ArrayList();
        
        try {
            String req = "Select * from `commande`";
            st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
             
            while(rst.next()){
                 
                 Commande p = new Commande(rst.getInt("id"),rst.getString("nom"),rst.getString("prenom"),rst.getString("adressecomplet"),rst.getString("telephone"),rst.getString("email"),rst.getInt("etat"));
                 ListR.add(p);
                 
            }
            
            
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
          return ListR ;
    }

    
   /* @Override
    public List<Commande> afficher() {
        List<Commande> list = new ArrayList<>();    
        try {
            String requete = "SELECT * FROM commande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Commande(rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(10),rs.getInt(1),rs.getString("produits"),rs.getFloat(7)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }*/

    public List<Commande> recherche(String crt) {
        List<Commande> list = new ArrayList<>();

        try {
            crt = "'%" + crt + "%'";
            String requete = "select * from commande WHERE nom LIKE " + crt + " OR prenom LIKE " + crt + " OR adressecomplet LIKE " + crt;
            PreparedStatement pst = cnx.prepareStatement(requete);
            cnx.prepareStatement(requete).executeQuery();
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                list.add(new Commande(rst.getInt("id"),rst.getString("nom"),rst.getString("prenom"),rst.getString("adressecomplet"),rst.getString("telephone"),rst.getFloat("total"),rst.getString("email"),rst.getString("produits")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public String getProductName() {
       
        try {
            String requete = "SELECT produits FROM cart";
            PreparedStatement pst = cnx.prepareStatement(requete);
          //  pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                title = rs.getString("produits");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("les pr" +title);
        return title;

    }
    
      @FXML
    private void setpr(String s,int id)  throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();
             try {
            int id_c = 0;
            
      
            String sql ="update commande set produits= '"+s+"' where id=?";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst= cnx.prepareStatement(sql);
            pst.execute();
             pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               id_c  = rs.getInt(1);
            }
                 System.out.println("liste ajoutées");
            
           
            
            
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }}
    
        
    
    
    
    public float gettot() {
       
        try {
            String requete = "SELECT total FROM cart";
            PreparedStatement pst = cnx.prepareStatement(requete);
          //  pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                total = rs.getFloat("total");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("TOT" +total);
        return total;

    }
    
      @FXML
    private void settot(float s,int id)  throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();
             try {
            int id_c = 0;
            
      
            String sql ="update commande set total= '"+s+"' where id=?";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst= cnx.prepareStatement(sql);
            pst.execute();
             pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               id_c  = rs.getInt(1);
            }
                 System.out.println("total ajoutées");
            
           
            
            
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }}
    
    

   /* public String getFileName(int id) {
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
    }*/
Connection Conn;
    public void qrCode(){
         Connection cnx = DataSource.getInstance().getCnx();
          try{
            String query = "select nom,prenom,adressecomplet,produits,total from commande";
            Statement stmt = null;
            stmt = Conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            
            	ServiceComImpl.generate_qr(rs.getString("nom"),rs.getString("prenom"));
            }
		} catch (Exception e) {
			// TODO: handle exception
		}}
        public static void generate_qr(String image_name,String qrCodeData) {
        try {
            String filePath = "C:\\wamp64\\www\\QRCode\\"+image_name+".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
          //  MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
            //    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
        
    
 public void TriCom(List<Commande> com){
        
        com.stream().sorted((o1, o2)->o1.getNom().
                                                                compareTo(o2.getNom())).
                                                                collect(Collectors.toList()).forEach(t-> System.out.println(t));
        
    }
      public Set<Commande> triparetat( List<Commande> u){
       
        Set<Commande> ensEmp2 = u.stream().collect(Collectors.toCollection(()->new TreeSet<Commande>((e1,e2)->String.valueOf(e1.getEtat()).compareTo(String.valueOf(e2.getEtat())))));
        return ensEmp2;
    }   
 


    @Override
    public void supprimer(Commande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Commande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commande> afficherf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}

