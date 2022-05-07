/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author aa */
public class Cart {
	
	
	private int id;
	private int quantity;
	private int produit_id;
       private int prix ;
    private String name  ;

    public Cart(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Cart() {
    }

    public Cart(int id, int produit_id, String name) {
        this.id = id;
        this.produit_id = produit_id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Cart(int id, int quantity, int produit_id, int prix) {
        this.id = id;
        this.quantity = quantity;
        this.produit_id = produit_id;
        this.prix = prix;
    }

    public Cart(int produit_id) {
        this.produit_id = produit_id;
    }

    public Cart(int quantity, int produit_id, int prix) {
        this.quantity = quantity;
        this.produit_id = produit_id;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public int getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }


    

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", quantity=" + quantity + ", productid=" + produit_id + '}';
    }
    
        
        
	

}

