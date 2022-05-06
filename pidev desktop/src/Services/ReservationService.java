/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Devis;
import Entities.Reservation;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author xDrais
 */
public class ReservationService implements IService<Reservation>{
    
     Connection con;
    Statement stm,stm2,stm3;
           float prixunitaire,prixtot;
           int iddevis,idreservation,etat,idevent,nbpers;
           String desceven,datedebut,datefin;
 
      

     public ReservationService() {
        con = MyDB.getInstance().getCon();
    }
    
     //ajout d'une réservation et un devis 
     
     
    @Override
    public void ajouter(Reservation res) throws SQLException {

        try {
      
            String req="INSERT INTO `reservation`(`even_id`, `user_id`, `nb_personne`, `dateres`, `etat`) VALUES ("+ res.getEven() +","+ res.getUser()+","+res.getNbPersonne() +",'"+ res.getDateres()+"','"+ res.getEtat()+"')";
            stm = con.createStatement();
            stm.executeUpdate(req); 
            
            //récupération de l'id de la dernière réservation insérée (clé étrangère dans devis)
            String req1 = "SELECT max(id) from `reservation` ";
            stm2 = con.createStatement();
            ResultSet rst1 = stm2.executeQuery(req1);
            
            //récupération du prix unitaire de l'évènement
             String req2 = "SELECT * from `evenement` where id="+res.getEven();
            stm3 = con.createStatement();
            ResultSet rst2 = stm3.executeQuery(req2);
            
            
             if (rst1.next())
                idreservation=rst1.getInt(1);
                       
             if (rst2.next())
             {
                prixunitaire=rst2.getInt(2);
                desceven=rst2.getString(3);
                datedebut=rst2.getString(6);
                datefin=rst2.getString(6);
             }
           
            prixtot=rst2.getInt(2)*res.getNbPersonne();
            String req3="INSERT INTO `devis`(`reservation_id`, `prix_tot`, `remise`) VALUES ("+ idreservation +","+ prixtot +","+0+")";
            stm = con.createStatement();
            stm.executeUpdate(req3);
            
         //récupération de l'id du dernier devis inséré
            String req4 = "SELECT max(id) from `devis` ";
            stm = con.createStatement();
            ResultSet rst4 = stm.executeQuery(req4);
             if (rst4.next())
                iddevis=rst4.getInt(1);
             
           //ajouter le id devis dans la reservation
           
             String requp = "UPDATE  reservation SET devis_id='"+iddevis+"' where id='"+idreservation+"'";
  
                 stm = con.createStatement();
                stm.executeUpdate(requp);


//            System.out.println(rst1.toString());
          
            System.out.println("Réservation et devis ajoutés avec succès");
            System.out.println("*********************Détails de la réservation**********************");
            System.out.println("Devis N°"+iddevis+"\n Date de réservation:"+res.getDateres()+"\n Evènement choisi:"+desceven+
                    "\n Date de début de l'évènement: "+datedebut+"\n Date de fin de l'évènement: "+ datefin+"\n");
            System.out.println("---------------------------\n");
            System.out.println("Prix unitaire:       "+prixunitaire+"\n                  * \nNombre de personnes:     "+res.getNbPersonne()+
                    "\n              _____________\n Prix total :       = "+prixtot );
            

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }    
    }
    
@Override
    public ObservableList<Reservation> afficherByIdUser(int id) throws SQLException {
        
        String req = "SELECT * from `reservation` where user_id="+id;
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        ObservableList<Reservation> reservations =FXCollections.observableArrayList();
        while(rst.next()){
            Reservation r = new Reservation(rst.getInt(1),rst.getInt(5),rst.getDate(6),rst.getInt(7),rst.getInt(2),rst.getInt(3),rst.getInt(4));
            reservations.add(r);            
        }
        return reservations;
    }
    
        @Override
    public void Delete(int idres) throws SQLException {
        
        String req = "SELECT * from `reservation` where id="+idres;
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        if (rst.next())
                etat=rst.getInt(7);
        if(etat==1)
                System.out.println("votre réservation est validée vous ne pouvez pas l'annuler");
        else
          try {
            String req2="DELETE FROM reservation WHERE `id`='"+idres+"'";
            stm = con.createStatement();
            stm.executeUpdate(req2); 
            System.out.println("réservation supprimée");
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());  
            
        } 
    }
    
   
        
    @Override
    public ObservableList<Reservation> afficher() throws SQLException {
    
        String req = "SELECT * from `reservation`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        ObservableList<Reservation> reservations =FXCollections.observableArrayList();
        while(rst.next()){
            Reservation r = new Reservation(rst.getInt(1),rst.getInt(5),rst.getDate(6),rst.getInt(7),rst.getInt(2),rst.getInt(3),rst.getInt(4));
            reservations.add(r);            
        }
        return reservations;
           
    }
    


    @Override
    public void validerReservation(int id) throws SQLException {
      // mettre l'état à 1
        try 
        {
          String requp = "UPDATE  reservation SET etat='"+1+"' where id='"+id+"'";
          stm = con.createStatement();
          stm.executeUpdate(requp);
        System.out.println("réservation validée");

          }catch (SQLException ex) {
            System.out.println(ex.getMessage()); }
        
        //mise à jour du nombre de places rstantes de l'évènement sélectionné
        String req1 = "SELECT * from `reservation` where id="+id;
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req1);
        if (rst.next())
        {idevent=rst.getInt(2);
        nbpers=rst.getInt(5);
        }
        
         String requp2 = "UPDATE  evenement SET nbre_places=nbre_places-'"+nbpers+"' where id='"+idevent+"'";
         stm = con.createStatement();
         stm.executeUpdate(requp2);

          
    }

  

    @Override
    public void modifier(Reservation t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> afficherDevis() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    

}
