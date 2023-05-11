package org.kenda.models.bus;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.kenda.models.agents.Agent;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Bus extends PanacheEntity {
    //public Long id;//
    //
    public Long idPartenaire;
    public String nom;
    public String marque;
    public String type;
    public String numeroChassis;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Timestamp dateAchat;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Timestamp dateMiseenservice;
    public int capacite;
    public String caracteristiques;
    public int kilometrage;
    public Boolean climatisation;
    public byte[] logo;

    //////////////////////////////////////////////////////////////////////////////////////////////

}
