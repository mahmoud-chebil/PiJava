/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author poste 1
 */
public interface IserviceE<T> {
      void ajouter(T t) throws SQLException;
    
     List<T> afficher() throws SQLException;
     List<T> afficherDevis() throws SQLException;

     List<T> afficherByIdUser(int id) throws SQLException;

     void Delete(int id) throws SQLException ;
     void validerReservation(int id) throws SQLException;
     void modifier(T t, int id) throws SQLException ;
    
}
