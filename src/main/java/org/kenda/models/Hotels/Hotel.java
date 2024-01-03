package org.kenda.models.Hotels;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Hotel extends PanacheEntity {
    //
    public String nom;
    public String postnom;
    public String prenom;
    public String sexe;
    public String email;
    public String telephone;
    public String denomination;
    public String adresseEtablissement;
    public String rccm;
    public String idnat;
    public String numeroImpot;
    public String provinceSiege;
    public String typeEtablissement;
    public byte[] photo;
    public int status;
    public String code;
    public String token;
    public int etage;

}
