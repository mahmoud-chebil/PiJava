/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author mariem
 */
public class Reclamation {

    private int id;
    private String titre, descRec, reponse, etat;
    private Date dateRec;
    private int IdType;
    private int IdUser;

    public Reclamation() {
    }

    public Reclamation(int id,int IdUser, int IdType,String titre, String descRec, Date dateRec) {
        this.id = id;
        this.titre = titre;
        this.descRec = descRec;
        this.dateRec = dateRec;
        this.IdType = IdType;
        this.IdUser = IdUser;
    }

    public Reclamation(int id, int IdUser,int IdType,String titre, String descRec, Date dateRec,String reponse, String etat) {
        this.id = id;
        this.titre = titre;
        this.descRec = descRec;
        this.reponse = reponse;
        this.etat = etat;
        this.dateRec = dateRec;
        this.IdType = IdType;
        this.IdUser = IdUser;
    }

    public Reclamation(String titre, String descRec, String reponse, String etat, Date dateRec, int IdType, int IdUser) {
        this.titre = titre;
        this.descRec = descRec;
        this.reponse = reponse;
        this.etat = etat;
        this.dateRec = dateRec;
        this.IdType = IdType;
        this.IdUser = IdUser;
    }

    public Reclamation(String titre, String descRec, String reponse, String etat, Date dateRec) {
        this.titre = titre;
        this.descRec = descRec;
        this.reponse = reponse;
        this.etat = etat;
        this.dateRec = dateRec;
    }



    public void setIdType(int IdType) {
        this.IdType = IdType;
    }

    public int getIdType() {
        return IdType;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public int getIdUser() {
        return IdUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescRec() {
        return descRec;
    }

    public void setDescRec(String descRec) {
        this.descRec = descRec;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateRec() {
        return dateRec;
    }

    public void setDateRec(Date dateRec) {
        this.dateRec = dateRec;
    }

    public String idtoString() {
        return String.valueOf(id);

    }

    public String idtypetoString() {
        return String.valueOf(IdType);

    }

    public String idusertoString() {
        return String.valueOf(IdUser);

    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", titre=" + titre + ", descRec=" + descRec + ", reponse=" + reponse + ", etat=" + etat + ", dateRec=" + dateRec + ", IdType=" + IdType + ", IdUser=" + IdUser + '}';
    }

    public Reclamation(int IdType, String titre, String descRec) {
        this.IdType = IdType;
        this.titre = titre;
        this.descRec = descRec;
    }

    public Reclamation(int IdUser, int IdType, String titre, String descRec, Date dateRec) {
        this.IdUser = IdUser;
        this.IdType = IdType;
        this.titre = titre;
        this.descRec = descRec;
        this.dateRec = dateRec;
    }

    public Reclamation(int IdType, String titre, String descRec, Date dateRec) {
        this.IdType = IdType;
        this.titre = titre;
        this.descRec = descRec;
        this.dateRec = dateRec;
    }

    public Reclamation(int id, String reponse) {
        this.id = id;
        this.reponse = reponse;
    }

       public Reclamation(String etat,int id ) {
        this.id = id;
        this.etat = etat;
    }

    public Reclamation(String titre, String descRec, int IdType) {
        this.titre = titre;
        this.descRec = descRec;
        this.IdType = IdType;
    }

    public Reclamation(int id, int IdUser ,int IdType,String titre, String descRec ) {
        this.id = id;
        this.titre = titre;
        this.descRec = descRec;
        this.IdType = IdType;
        this.IdUser = IdUser;
    }

    public Reclamation(String titre,String descRec) {
        this.titre = titre;
        this.descRec = descRec;
    }


    public Reclamation(String reponse) {
        this.reponse = reponse;
    }

  
    
    

}
