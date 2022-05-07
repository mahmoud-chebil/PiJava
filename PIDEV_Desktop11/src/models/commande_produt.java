/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author poste 1
 */
public class commande_produt {
   private int commande_id;
   private int product_id;

    public commande_produt() {
    }

    public commande_produt(int commande_id) {
        this.commande_id = commande_id;
    }


    public commande_produt(int commande_id, int product_id) {
        this.commande_id = commande_id;
        this.product_id = product_id;
    }

    public int getCommande_id() {
        return commande_id;
    }
                                                                                               
    public int getProduct_id() {
        return product_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "commande_produt{" + "commande_id=" + commande_id + ", product_id=" + product_id + '}';
    }
   
   
   
}
