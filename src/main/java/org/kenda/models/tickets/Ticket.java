package org.kenda.models.tickets;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.kenda.models.agents.Agent;
import org.kenda.models.bus.Bus;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Ticket extends PanacheEntity {
    public Long idPartenaire;

    public Long idCourse;
    public String emplacement;
    public Boolean consomer;
    public Long idArretDepart;
    public String arretDepart;
    public Long idArretArrive;
    public String arretArrive;
    public double prix;
    public double prixTotal;
    public char devise;
    public short codePostal;
    public String phone;
    public int codeRecuperation;
    public Boolean valider;
    public short nombrePlace;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp dateDepart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp dateArrive;
    //////////////////////////////////////////////////////////////////////////////////////////

    public static List<Ticket> findTicket(Long idPartenaire){
        return list("id",idPartenaire);
    }
    //
    public void update(Ticket ticket) {
        if(ticket.isPersistent()){
            Ticket oldTicket = Ticket.findById(ticket.id);
            if(oldTicket != null){
                /*
                oldAgent.nom = agent.nom;
                oldAgent.postnom = agent.postnom;
                oldAgent.prenom = agent.prenom;
                oldAgent.numero = agent.numero;
                oldAgent.email = agent.email;
                oldAgent.adresse = agent.adresse;
                oldAgent.password = agent.password;
                oldAgent.roletitre = agent.roletitre;
                oldAgent.role = agent.role;
                */
                oldTicket.update(ticket);
            }
        }
        //Agent.update("name = 'Mortal' where status = ?1", Status.Alive);
        //update("",{});
    }
}
