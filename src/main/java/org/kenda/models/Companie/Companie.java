package org.kenda.models.Companie;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Companie extends PanacheEntity {
    //public Long id;
    public String denomination;
    public String adresseEtablissement;
    public String rccm;
    public String idnat;
    public String numeroImpot;
    public String provinceSiege;
    //public String typeEtablissement;
    public byte[] photo;
    public int status;
    public String code;
    public String token;

}
