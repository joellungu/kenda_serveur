package org.kenda.models.utilisateurs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Utilisateur extends PanacheEntity {
    public Long id;
    public String nom;
    public String numero;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp datenaissance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Timestamp getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Timestamp datenaissance) {
        this.datenaissance = datenaissance;
    }
}
