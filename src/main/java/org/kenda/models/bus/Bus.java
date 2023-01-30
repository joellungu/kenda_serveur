package org.kenda.models.bus;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.kenda.models.agents.Agent;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Bus extends PanacheEntity {
    //public Long id;//
    //
    public Long idPartenaire;
    public String nom;
    public String marque;
    public String type;
    public String numeroChassis;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Timestamp dateAchat;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Timestamp dateMiseenservice;
    public int capacite;
    public String caracteristiques;
    public int kilometrage;
    public Boolean climatisation;

    //////////////////////////////////////////////////////////////////////////////////////////////
    public static List<Bus> findAllBus(){
        return listAll();
    }

    public static List<Bus> findBus(Long idPartenaire){
        return list("idPartenaire",idPartenaire);
    }

    public void update(Bus bus) {
        if(bus.isPersistent()){
            Bus oldBus = Bus.findById(bus.id);
            if(oldBus != null){
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
                oldBus.update(bus);
            }
        }
        //Agent.update("name = 'Mortal' where status = ?1", Status.Alive);
        //update("",{});
    }

    public static void deleteBus(Long id){
        deleteById(id);
    }
}
