package org.kenda.models.Hotels;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Appartement extends PanacheEntity {
    public Long idHotel;
    public double prix;
    public String devise;
    public Boolean status;
    public int categorie;

    public String numero;
    public int piece;
    public String typeLocation;
    public boolean climatisation;
    public boolean internet;
    public boolean decodeur;

    public String description;

    public byte[] photo;

}
