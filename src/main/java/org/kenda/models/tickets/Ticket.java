package org.kenda.models.tickets;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.kenda.models.agents.Agent;
import org.kenda.models.bus.Bus;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Ticket extends PanacheEntity {
    public String itinerance;
    public long idAgent;
    public String datePaiement;
    public int emplacement;
    public int status;

    public double prix;
    public String devise;
    public String phone;
    public String reference;
    public String ref;
    public String unique_code;
    public Long idBoutique;
    public String dateDepart;
    public String heureDepart;
    //////////////////////////////////////////////////////////////////////////////////////////

    public static List<Ticket> findTicket(Long idPartenaire){
        return list("idPartenaire",idPartenaire);
    }
    //
}
