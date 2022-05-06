/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.Cart;

/**
 *
 * @author MSI
 * @param <T>
 */
public interface IService<T> {
    public void ajouter(T t);
    public void supprimer(T t);
        public void supprimer(int id);
    public void modifier(T t);
     public void modifierf(T t,int id);
    public List<T> afficher();
    public void modifierr(T t,int id);
}
