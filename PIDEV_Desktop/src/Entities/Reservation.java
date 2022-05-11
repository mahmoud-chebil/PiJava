/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author xDrais
 */
public class Reservation {
    
    private int id;
    private int nbPersonne;
    private Date dateres;
    private int etat;
    private int even;
    private int user;
    private int devis;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public String idtoString ()
    {
        return String.valueOf(id);
    }
      public String nbpertoString ()
    {
        return String.valueOf(nbPersonne);
    }
    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public Date getDateres() {
        return dateres;
    }

    public void setDateres(Date dateres) {
        this.dateres = dateres;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getEven() {
        return even;
    }

    public void setEven(int even) {
        this.even = even;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getDevis() {
        return devis;
    }

    public void setDevis(int devis) {
        this.devis = devis;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", nbPersonne=" + nbPersonne + ", dateres=" + dateres + ", etat=" + etat + ", even=" + even + ", user=" + user + ", devis=" + devis + '}'+"\n";
    }

   
    

    public Reservation( int nbPersonne, Date dateres, int etat) {
        this.nbPersonne = nbPersonne;
        this.dateres = dateres;
        this.etat = etat;
    }

    public Reservation() {}

    public Reservation(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public Reservation(int id, int nbPersonne, Date dateres, int etat, int even, int user, int devis) {
        this.id = id;
        this.nbPersonne = nbPersonne;
        this.dateres = dateres;
        this.etat = etat;
        this.even = even;
        this.user = user;
        this.devis = devis;
    }


    

    


    
}
