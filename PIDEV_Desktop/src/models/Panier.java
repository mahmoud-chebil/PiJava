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
public class Panier {
   
    private int id;
    private int idProduit;
    private int quantite;

    public Panier(int id, int idProduit, int quantite) {
        this.id = id;
        this.idProduit = idProduit;
        
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", idProduit=" + idProduit + ", idClient="+ ", quantite=" + quantite + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
}
