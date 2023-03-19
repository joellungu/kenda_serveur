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
    public Long idPartenaire;
    public Long idTronson;
    //Arret Arriver
    public String aaProvince;
    public String aaLieu;
    //Arret Depart
    public String adProvince;
    public String adLieu;
    public String datePaiement;

    public String emplacement;
    public Boolean consomer;
    public double prix;
    //
    public String codePostal;
    public String phone;
    public int codeRecuperation;
    public Boolean valider;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp dateDepart;
    //////////////////////////////////////////////////////////////////////////////////////////

    public static List<Ticket> findTicket(Long idPartenaire){
        return list("idPartenaire",idPartenaire);
    }
    //
    public Response update(Ticket ticket) {
        if(ticket.isPersistent()){
            Ticket oldTicket = Ticket.findById(ticket.id);
            if(oldTicket == null){
                //oldTicket.update(ticket);
                return Response.serverError().build();
            }

            oldTicket.idTronson = ticket.idTronson;
            //Arret Arriver
             oldTicket.aaProvince = ticket.aaProvince;
             oldTicket.aaLieu = ticket.aaLieu;
            //Arret Depart
             oldTicket.adProvince = ticket.adProvince;
             oldTicket.adLieu = ticket.adLieu;
             //
             oldTicket.emplacement = ticket.emplacement;
             oldTicket.consomer = ticket.consomer;
             oldTicket.prix = ticket.prix;
             oldTicket.datePaiement = ticket.datePaiement;
             oldTicket.dateDepart = ticket.dateDepart;
             //
             oldTicket.codePostal = ticket.codePostal;
             oldTicket.phone = ticket.phone;
             oldTicket.codeRecuperation = ticket.codeRecuperation;
             oldTicket.valider = ticket.valider;
             //
            return Response.ok(oldTicket).build();
        }else{
            return Response.serverError().build();
        }
        //Agent.update("name = 'Mortal' where status = ?1", Status.Alive);
        //update("",{});
    }
}
