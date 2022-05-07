/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import static java.lang.Boolean.FALSE;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Label;

/**
 *
 * @author aa */
public class Cart {
	
	List<Product>  panier=new ArrayList<>();
	private int id;
	private int quantity;
	private int produit_id;
       private String price ;
    private String name  ;
   public static Cart instance;
   private Product product;
private String produits;
   
    public Cart() {
    }
 public Cart(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public Cart(int quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }
 
 
 
    public Cart(int id, String name,int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
    }

    public Cart(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public Cart( String name, int quantity,String price,int id) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }
    
    
    
    

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void increaseQuantity(){
        this.quantity++;
    }
 
    public void decreaseQuantity(){
        if(this.quantity>0)
        this.quantity--;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Cart(int id, int quantity, int produit_id, String price) {
        this.id = id;
        this.quantity = quantity;
        this.produit_id = produit_id;
        this.price = price;
    }

    public Cart(int produit_id) {
        this.produit_id = produit_id;
    }

    public String getProduits() {
        return produits;
    }

    public Cart(String produits) {
        this.produits = produits;
    }

   
    
    

    public Cart(int id, int quantity, int produit_id) {
        this.id = id;
        this.quantity = quantity;
        this.produit_id = produit_id;
    }

    public Cart( String name,String price) {
        this.name = name;
        this.price = price;
        
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

    public String getPrice() {
        return price;
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

    public void setPrix(String prix) {
        this.price = prix;
    }


    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void ListP(Product p) {
         if (this.panier == null) {
           this.panier.add(p);}
         if  (panier.contains(p)==FALSE){
             this.panier.add(p);
         }
        }

    

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", quantity=" + quantity + ", productid=" + produit_id + '}';
    }
    
        
        
	

}

