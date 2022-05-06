/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Lenovo
 */
public class Devis {
    private int id;
    private float prixTot;
    private float remise;
    private int reservation_id;


    public Devis() {
    }

    public Devis(float prixTot, float remise) {
        this.prixTot = prixTot;
        this.remise = remise;
    }
    
    public Devis(int id, float prixTot, float remise) {
        this.id = id;
        this.prixTot = prixTot;
        this.remise = remise;
    }
       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(float prixTot) {
        this.prixTot = prixTot;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    @Override
    public String toString() {
        return "Devis{" + "id=" + id + ", prixTot=" + prixTot + ", remise=" + remise + ", reservation_id=" + reservation_id + '}'+"\n";
    }

    public Devis(int id, float prixTot, float remise, int reservation_id) {
        this.id = id;
        this.prixTot = prixTot;
        this.remise = remise;
        this.reservation_id = reservation_id;
    }

     

             


    
}
