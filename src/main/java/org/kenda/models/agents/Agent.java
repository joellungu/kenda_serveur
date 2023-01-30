package org.kenda.models.agents;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Agent extends PanacheEntity {
    //public Long id;
    public Long idPartenaire;
    public String nom;
    public String postnom;
    public String prenom;
    public String numero;
    public String email;
    public String adresse;
    public String password;
    public int role;
    public String roletitre;
    public boolean actif;

    public static List<Agent> findAllAgents(){
        return listAll();
    }

    public static List<Agent> findAgents(Long idPartenaire){
        return list("idPartenaire",idPartenaire);
    }

    public Agent getAgent(String password){
        Agent agent = null;
        for(PanacheEntityBase ag : listAll()){
            Agent a = (Agent)ag;
            if(a.password.equals(password)){
                agent = a;
                break;
            }
        }
        return agent != null ? agent : null;
    }

    public void update(Agent agent) {
        if(agent.isPersistent()){
            Agent oldAgent = Agent.findById(agent.id);
            if(oldAgent != null){
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
                oldAgent.update(agent);
            }
        }
        //Agent.update("name = 'Mortal' where status = ?1", Status.Alive);
        //update("",{});
    }

    public static void deleteAgent(Long id){
        deleteById(id);
    }
}
