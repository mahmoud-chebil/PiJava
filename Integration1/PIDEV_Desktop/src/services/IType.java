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
public interface IType<T>{
     void ajouterType(T t) throws SQLException;
     void deleteType(int id) throws SQLException;
     void modifier(T t,int id) throws SQLException;
    List<T> afficherT() throws SQLException;
}
