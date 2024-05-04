package org.kenda.models.Proprietaire;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Proprietaire extends PanacheEntity {

    public Long idPartenaire;

    public String nom;

    public String postnom;

    public String prenom;

    public String email;
    public String telephone;
    public String password;

}
