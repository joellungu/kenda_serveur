package org.kenda.models.utilisateurs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Utilisateur extends PanacheEntity {
    public String noms;
    public String genre;

    public String telephone;

    public String fbToken;

}
