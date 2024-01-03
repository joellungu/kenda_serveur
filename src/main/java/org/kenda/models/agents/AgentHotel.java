package org.kenda.models.agents;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class AgentHotel extends PanacheEntity {
    public Long idPartenaire;
    public String nom;
    public String postnom;
    public String prenom;
    public String numero;
    public String email;
    public String adresse;
    public String password;
    public int role;
    public String roletitre;
    public boolean actif;

}
