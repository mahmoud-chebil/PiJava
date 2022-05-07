/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import Entities.Reservation;
import Services.EvenementServiceR;
import Services.ReservationService;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;


/**
 *
 * @author macbook
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
 
        
        System.out.println(" ---------------------------- Affichage des évènements disponibles ---------------------------- ");
        EvenementServiceR even = new EvenementServiceR();
        try {
            System.out.println(even.afficher().toString());
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
          System.out.println(" ---------------------------- Réservation d'un évènement création d'une réservation et d'un devis ---------------------------- ");
        
          ReservationService res = new ReservationService();      
        //date du jour
//        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        Reservation r=new Reservation(0, 7, dtformat.format(LocalDateTime.now()), 0, 2, 1,0);
      Reservation r=new Reservation(0, 5, Date.valueOf(LocalDate.now()), 0, 2, 1,0);
        try {
            res.ajouter(r);  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

                 
        System.out.println(" ---------------------------- Affichage de toutes les réservations de l'utilisateur indiqué ---------------------------- ");
        ReservationService resuser = new ReservationService();
        try {
         
            System.out.println(resuser.afficherByIdUser(5).toString());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           System.out.println(" ---------------------------- Suppression d'une réservation si elle est en état d'attente(pas encore validée) ---------------------------- ");
               ReservationService res2 = new ReservationService();
           try {
         res2.Delete(96);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
             
           System.out.println(" ****************************** partie Back **************************** \n");
                   
           System.out.println(" ---------------------------- consultation de toutes les réservations---------------------------- ");
           
          ReservationService resall = new ReservationService();
        try {
         
            System.out.println(resall.afficher().toString()+"\n");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
               System.out.println(" ---------------------------- valider une réservation (changer son état et mettre à jour le nombre de places de l'évènement choisi)--------------------------- ");

           ReservationService resvalid = new ReservationService();
           try {
         resvalid.validerReservation(88);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
}
