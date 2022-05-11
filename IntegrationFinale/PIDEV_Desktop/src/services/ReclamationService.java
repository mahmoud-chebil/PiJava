/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Reclamation;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;


/**
 *
 * @author mariem
 */
public class ReclamationService implements IReclamation<Reclamation>{
      
    
  //  Connection cnx;
    Statement stm;
  Connection cnx = DataSource.getInstance().getCnx();
    public ReclamationService() {
           Connection cnx = DataSource.getInstance().getCnx();

    }

    
    
    @Override
    public List<Reclamation> afficherR() throws SQLException {
        String req = "Select * from `reclamation`";
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Reclamation> rec = new ArrayList<Reclamation>();
        while(rst.next()){
            Reclamation p = new Reclamation(rst.getInt(1),rst.getInt(2),rst.getInt(3),rst.getString(4),rst.getString(5),rst.getDate(6),rst.getString(7),rst.getString(8));
    
        }        

        return rec;
        
    }
    
    
    
    @Override
	    public void ajouterR(Reclamation t) throws SQLException {
	          String req = "INSERT INTO `reclamation` (`id_user_id`,`id_type_id`, `titre`,`desc_rec`,`date_rec`) VALUES ( '"
	                + t.getIdUser()+ "', '" + t.getIdType()+ "','" + t.getTitre()+ "', '" + t.getDescRec()+ "','" + t.getDateRec()+ "') ";
//             String req = "INSERT INTO `reclamation` (`titre`,`desc_rec`) VALUES ( "+ t.getTitre()+ ", " + t.getDescRec()+ ") ";
	             
              
             stm = cnx.createStatement();
	        stm.executeUpdate(req);
	    }
	
    
    
@Override
    public void modifierR(Reclamation r, int id) throws SQLException {
         String req = "UPDATE reclamation SET  titre =?,desc_rec=? where id=?" ;
        PreparedStatement pre;

            pre = cnx.prepareStatement(req);
       
     
       pre.setString(1, r.getTitre()+"");
       pre.setString(2, r.getDescRec()+"");
       pre.executeUpdate();
        
    }  
    
        
    
     @Override
    public void deleteReclamation(int id) throws SQLException {
            PreparedStatement pst;
            String sql = "DELETE FROM `reclamation` WHERE id ="+id;
            pst = cnx.prepareStatement(sql);

            pst.executeUpdate();      
    }
    

    @Override
    public List<Reclamation> afficherId(int id) throws SQLException {
   String req = "Select * from `reclamation` where id_user_id="+id;
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Reclamation> rec = new ArrayList<Reclamation>();
        while(rst.next()){
            Reclamation p = new Reclamation(rst.getInt(1),rst.getInt(2),rst.getInt(3),rst.getString(4),rst.getString(5),rst.getDate(6),rst.getString(7),rst.getString(8));
            rec.add(p);
    
        }        

        return rec;
    }

@Override
    public void etatR(Reclamation r, int id ) throws SQLException {
        
        
        String req="UPDATE reclamation SET etat=? WHERE id=?";
        
             PreparedStatement pstmt = cnx.prepareStatement(req);
            
            pstmt.setString(1,r.getEtat());
            pstmt.setInt(2,id);
            pstmt.executeUpdate();

    }
    
    
    @Override
    public void reponseR( int id , Reclamation r) throws SQLException {
        
        
        String req="UPDATE reclamation SET reponse=? WHERE id=?";
        
             PreparedStatement pstmt = cnx.prepareStatement(req);
             
            pstmt.setString(1,r.getReponse());
            pstmt.setInt(2,id);
            pstmt.executeUpdate();

    }
    
    public List<Reclamation> search(String input) {
		 ObservableList <Reclamation> recs = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM reclamation where id_user_id LIKE ? or date_rec LIKE ? or id_type_id LIKE ? or titre LIKE ? or desc_rec LIKE ? or reponse LIKE ? or etat like ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, "%"+input+"%");
            pst.setString(2, "%"+input+"%");
            pst.setString(3, "%"+input+"%");
            pst.setString(4, "%"+input+"%");
            pst.setString(5, "%"+input+"%");
            pst.setString(6, "%"+input+"%");
            pst.setString(7, "%"+input+"%");
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
            recs.add(new Reclamation(rst.getInt(1),rst.getInt(2),rst.getInt(3),rst.getString(4),rst.getString(5),rst.getDate(6),rst.getString(7),rst.getString(8)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return recs;
	}
    
    
    
    
    
    public List<Reclamation> search1(String input) {
		 ObservableList <Reclamation> recs = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM reclamation where id_user_id LIKE ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, "%"+input+"%");
       
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
            recs.add(new Reclamation(rst.getInt(1),rst.getInt(2),rst.getInt(3),rst.getString(4),rst.getString(5),rst.getDate(6),rst.getString(7),rst.getString(8)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return recs;
	}
    
    
    
    
    
    public String getUserEmail(int id) throws SQLException {
   String req = "Select email from user where id="+69;
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        
        while(rst.next()){
            return rst.getString("email");
        }        

        return "";
    }
    
    public void send(String email) {
        final String username = "d1a137431dee0c";
        final String password = "af01311b6aa223";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "2525");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("admin@adm.in"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email+", "+email)
            );
            message.setSubject("Reclamation Traite");
            message.setText(" Reclamation Traite! \n ");

            Transport.send(message);
            Notifications n = Notifications.create()
                                                     .title("Reponse Reclamation")
                                                     .text("  Reponse Ajouté")
                                                     .graphic(new ImageView())
                                                     .position(Pos.TOP_CENTER)
                                                      .hideAfter(Duration.seconds(3));
                                    n.darkStyle();
                                    n.show();
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    } 
    
}
