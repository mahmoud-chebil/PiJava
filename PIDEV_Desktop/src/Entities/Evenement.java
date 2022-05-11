/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author MSI
 */
public class Evenement {
    private int id;
    private int prix_evenement;
    private String description_evenement;
    private String titre_evenement;
    private int nbre_places;
    private Date date_debut;
    private Date date_fin;
    private int tel;
    private String email;
    private int id_activite;

    public Evenement() {
    }

    public Evenement(int prix_evenement, String decription_evenement, String titre_evenement, int nbre_places, Date date_debut, Date date_fin, int tel, String email) {
        this.prix_evenement = prix_evenement;
        this.description_evenement = decription_evenement;
        this.titre_evenement = titre_evenement;
        this.nbre_places = nbre_places;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tel = tel;
        this.email = email;
    }

    public Evenement(int id, int prix_evenement, String decription_evenement, String titre_evenement, int nbre_places, Date date_debut, Date date_fin, int tel, String email) {
        this.id = id;
        this.prix_evenement = prix_evenement;
        this.description_evenement = decription_evenement;
        this.titre_evenement = titre_evenement;
        this.nbre_places = nbre_places;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tel = tel;
        this.email = email;
    }

    public Evenement(int id, int prix_evenement, String decription_evenement, String titre_evenement, int nbre_places, Date date_debut, Date date_fin, int tel, String email, int id_activite) {
        this.id = id;
        this.prix_evenement = prix_evenement;
        this.description_evenement = decription_evenement;
        this.titre_evenement = titre_evenement;
        this.nbre_places = nbre_places;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tel = tel;
        this.email = email;
        this.id_activite = id_activite;
    }

    public Evenement(int prix_evenement, String decription_evenement, String titre_evenement, int nbre_places, Date date_debut, Date date_fin, int tel, String email, int id_activite) {
        this.prix_evenement = prix_evenement;
        this.description_evenement = decription_evenement;
        this.titre_evenement = titre_evenement;
        this.nbre_places = nbre_places;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tel = tel;
        this.email = email;
        this.id_activite = id_activite;
    }

    public int getId_activite() {
        return id_activite;
    }

    public void setId_activite(int id_activite) {
        this.id_activite = id_activite;
    }

   

    public Evenement(int id, String text, String text0, String text1, int parseInt, int i, int i0, int parseInt0, String text2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix_evenement() {
        return prix_evenement;
    }

    public void setPrix_evenement(int prix_evenement) {
        this.prix_evenement = prix_evenement;
    }

    public String getDecription_evenement() {
        return description_evenement;
    }

    public void setDecription_evenement(String decription_evenement) {
        this.description_evenement = decription_evenement;
    }

    public String getTitre_evenement() {
        return titre_evenement;
    }

    public void setTitre_evenement(String titre_evenement) {
        this.titre_evenement = titre_evenement;
    }

    public int getNbre_places() {
        return nbre_places;
    }

    public void setNbre_places(int nbre_places) {
        this.nbre_places = nbre_places;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", prix_evenement=" + prix_evenement + ", decription_evenement=" + description_evenement + ", titre_evenement=" + titre_evenement + ", nbre_places=" + nbre_places + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", tel=" + tel + ", email=" + email + ", id_activite=" + id_activite + '}';
    }



 

    
}
