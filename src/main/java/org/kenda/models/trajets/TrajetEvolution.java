package org.kenda.models.trajets;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.kenda.models.agents.Agent;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class TrajetEvolution extends PanacheEntity {
    public Long idCourse;
    public Long idBus;
    public int placeDisponible;
    public int placeAssise;
    public int capacite;
    public Long idArretDepart;
    public Long idArretArrive;
    public Long longitude;
    public Long latitude;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp heureDate;
    //
    public static List<TrajetEvolution> findTrajetEvolutions(Long idCourse){
        return list("idCourse",idCourse);
    }

}
