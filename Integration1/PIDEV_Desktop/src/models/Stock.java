/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Fares
 */
public class Stock {
    private int id,rest_q,quantity;
    private String Name;

    public Stock() {
    }

    public Stock(String Name, int quantity) {
       
        this.Name = Name;
        this.quantity = quantity;
    }

    public Stock(String Name, int quantity,int rest_q ) {
        this.Name = Name;
        this.quantity = quantity;
        
        this.rest_q = rest_q;
    }
    
    

    public Stock(int id, int rest_q, int quantity, String Name) {
        this.id = id;
        this.rest_q = rest_q;
        this.quantity = quantity;
        this.Name = Name;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRest_q() {
        return rest_q;
    }

    public void setRest_q(int rest_q) {
        this.rest_q = rest_q;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", rest_q=" + rest_q + ", quantity=" + quantity + ", Name=" + Name + '}';
    }
    
    
}
