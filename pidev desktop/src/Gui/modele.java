/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.sql.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lenovo
 */
public class modele {
     private Integer idres,nbrepers,nombreplacerest,etat;
      private String username,desceven;
      private Date dateres;

    public Integer getIdres() {
        return idres;
    }

    public void setIdres(Integer idres) {
        this.idres = idres;
    }

    public Integer getNbrepers() {
        return nbrepers;
    }

    public void setNbrepers(Integer nbrepers) {
        this.nbrepers = nbrepers;
    }

    public Integer getNombreplacerest() {
        return nombreplacerest;
    }

    public void setNombreplacerest(Integer nombreplacerest) {
        this.nombreplacerest = nombreplacerest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesceven() {
        return desceven;
    }

    public void setDesceven(String desceven) {
        this.desceven = desceven;
    }

    public Date getDateres() {
        return dateres;
    }

    public void setDateres(Date dateres) {
        this.dateres = dateres;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public modele(Integer idres, Integer nbrepers, Integer nombreplacerest, Integer etat, String username, String desceven, Date dateres) {
        this.idres = idres;
        this.nbrepers = nbrepers;
        this.nombreplacerest = nombreplacerest;
        this.etat = etat;
        this.username = username;
        this.desceven = desceven;
        this.dateres = dateres;
    }


      




     


    
}
