package org.kenda.models.partenaires;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.kenda.models.agents.Agent;
import org.kenda.models.bus.Bus;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Partenaire extends PanacheEntity {
    //public Long id;
    public Long idPartenaire;
    public String nom;
    public String adresse;
    public String telephone;
    public String email;
    public String motdepasse;
    public int categorie;
    public int status;
    public byte[] logo;

}
