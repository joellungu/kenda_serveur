package org.kenda.models.paiement;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Paiement extends PanacheEntity {
    public String merchant;
    public int status;

    public String date;
    public String reference;
    public String phone;
    public double amount;
    public String currency;
    public String callbackurl;
}
