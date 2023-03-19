package org.kenda.models.Rapports;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Rapport extends PanacheEntity {
    public Long idPartenaire;
    public String date;
    public String object;
    public String titre;
    public String contenue;
    public Long idAgent;

}
