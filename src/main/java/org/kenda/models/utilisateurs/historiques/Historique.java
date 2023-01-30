package org.kenda.models.utilisateurs.historiques;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Historique extends PanacheEntity {
    public Long idUtilisateur;
    public Long idTicket;
}
