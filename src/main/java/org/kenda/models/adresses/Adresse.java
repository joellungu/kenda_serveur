package org.kenda.models.adresses;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Adresse extends PanacheEntity {
    public Long id;
    public Long idarret;
    public Long idcourse;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp heurearrive;
    public String lieuarrive;

}
