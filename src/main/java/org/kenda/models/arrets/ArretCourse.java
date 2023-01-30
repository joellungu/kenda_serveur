package org.kenda.models.arrets;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class ArretCourse {
    public ArretCourse(){}
    public ArretCourse(Long arretarrive_id,String nom, String province, double latitude, double longitude, double rayon, double prix){
        this.arretarrive_id = arretarrive_id;
        this.nom = nom;
        this.province = province;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rayon = rayon;
        this.prix = prix;
    }
    public Long arretarrive_id;
    public String nom;
    public String province;
    public double longitude;
    public double latitude;
    public double rayon;
    public double prix;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
