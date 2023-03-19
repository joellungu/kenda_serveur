package org.kenda.models.emplacement;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Emplacement extends PanacheEntity {
    public Long idCourse;
    public String place;
    public boolean prix;
}
