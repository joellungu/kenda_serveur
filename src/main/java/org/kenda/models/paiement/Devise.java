package org.kenda.models.paiement;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Devise extends PanacheEntity {
    public String devise;
    public double taux;
}
