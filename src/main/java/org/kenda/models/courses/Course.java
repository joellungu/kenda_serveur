package org.kenda.models.courses;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.kenda.models.agents.Agent;
import org.kenda.models.bus.Bus;
import org.kenda.models.transon.Tronson;

import javax.persistence.*;
//import javax.persistence
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Entity
public class Course extends PanacheEntity {
    //public Long id;
    public Long idPartenaire;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public String dates;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public String heureDepart;
    @Column(columnDefinition = "integer default 1")
    public double prix;

    public Long idArretDepart;
    public Long idArretArrive;

    public String heureArrive;
    public String lieuDepart;
    public String provinceDepart;
    public String lieuArrive;
    public String provinceArrive;
    public String arretEnCour;
    public int jourDepart;
    public int nombreJours;


    @OneToOne(cascade = CascadeType.MERGE)
    //@JoinColumn(name = "address_id", referencedColumnName = "id")
    public Agent chauffeur;
    @OneToOne(cascade = CascadeType.MERGE)
    public Agent receveur;
    @OneToOne(cascade = CascadeType.MERGE)
    public Agent embarqueur;
    @OneToOne(cascade = CascadeType.MERGE)
    public Bus bus;

    //@OneToMany(cascade = CascadeType.ALL)
    public String troncons;
    public int placeDisponible;
    public String reference;
    public short status;
    public String lieuActuel;
    public Boolean terminer;
    //
}
