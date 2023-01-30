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


    public HashMap arretArrive;

    public HashMap arretDepart;
    public double prix;
    public boolean active;

    public HashMap getArretArrive() {
        return arretArrive;
    }

    public void setArretArrive(HashMap arretArrive) {
        this.arretArrive = arretArrive;
    }

    public HashMap getArretDepart() {
        return arretDepart;
    }

    public void setArretDepart(HashMap arretDepart) {
        this.arretDepart = arretDepart;
    }

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
