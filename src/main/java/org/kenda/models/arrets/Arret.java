package org.kenda.models.arrets;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.kenda.models.bus.Bus;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Arret extends PanacheEntity {
    public Arret(){}
    public Arret(String nom, String route, List<String> routeSecondaire, boolean principale){
        this.nom = nom;
        this.route = route;
        this.routeSecondaire = routeSecondaire;
        this.principale = principale;
    }
    //public Long id;
    public String nom;
    //public String province;
    public String route;
    @ElementCollection
    public List<String> routeSecondaire;
    public Boolean principale;

}
