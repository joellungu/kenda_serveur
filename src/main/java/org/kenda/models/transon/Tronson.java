package org.kenda.models.transon;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.kenda.models.arrets.ArretCourse;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


@Entity
public class Tronson extends PanacheEntity {

    public Long idPartenaire;

    public String nom;

    public String aaProvince;
    public String aaLieu;
    @Column(columnDefinition = "integer default 1")
    public double aaLatitude;
    @Column(columnDefinition = "integer default 1")
    public double aaLongitude;

    public String adProvince;
    public String adLieu;

    @Column(columnDefinition = "float8 default 1")
    public double adLatitude;
    @Column(columnDefinition = "float8 default 1")
    public double adLongitude;

    @Column(columnDefinition = "integer default 1")
    public double prix;
    public boolean active;

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    /////////////////////////////////////////////////////////
}
