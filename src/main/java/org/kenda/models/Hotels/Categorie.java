package org.kenda.models.Hotels;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Categorie extends PanacheEntity {
    //
    public Long idHotel;
    public int categorie;
    public String libele;
}
