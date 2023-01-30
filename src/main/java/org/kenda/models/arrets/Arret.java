package org.kenda.models.arrets;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.kenda.models.bus.Bus;

import javax.persistence.Entity;

@Entity
public class Arret extends PanacheEntity {
    public Arret(){}
    public Arret(String nom, String province, double latitude, double longitude, double rayon){
        this.nom = nom;
        this.province = province;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rayon = rayon;
    }
    //public Long id;
    public String nom;
    public String province;
    public double longitude;
    public double latitude;
    public double rayon;

    public void update(Arret arret) {
        if(arret.isPersistent()){
            Arret oldBus = Arret.findById(arret.id);
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
                oldBus.update(arret);
            }
        }
    }

}
