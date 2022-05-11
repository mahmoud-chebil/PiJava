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
 * @author mariem
 */
public interface IReclamation<R> {
    
    
   void deleteReclamation(int id)  throws SQLException;
   List<R> afficherR() throws SQLException;
   void ajouterR(R r)  throws SQLException;
   List<R> afficherId(int id) throws SQLException;
   void modifierR(R r,int id) throws SQLException;
   void etatR(R r,int id) throws SQLException;
   void reponseR(int id,R r) throws SQLException;

   

  
    
}

