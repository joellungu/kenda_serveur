package org.kenda.models.partenaires;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.kenda.models.agents.Agent;
import org.kenda.models.bus.Bus;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Partenaire extends PanacheEntity {
    //public Long id;
    public String nom;
    public String adresse;
    public String phone1;
    public String phone2;
    public String email;
    public String password;
    public int taille;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    public static List<Partenaire> findPartenaires(){
        return listAll();
    }

    public Partenaire getAgent(String password, String email){
        Partenaire partenaire = null;
        for(PanacheEntityBase pa : listAll()){
            Partenaire p = (Partenaire) pa;
            if(p.password.equals(password) && p.email.equals(email)){
                partenaire = p;
                break;
            }
        }
        return partenaire != null ? partenaire : null;
    }

    public void update(Partenaire partenaire) {
        if(partenaire.isPersistent()){
            Partenaire oldPartenaire = Partenaire.findById(partenaire.id);
            if(oldPartenaire != null){
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
                oldPartenaire.update(partenaire);
            }
        }
        //Agent.update("name = 'Mortal' where status = ?1", Status.Alive);
        //update("",{});
    }

    public static void deleteBus(Long id){
        deleteById(id);
    }
}
