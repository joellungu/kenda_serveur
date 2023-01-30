package org.kenda.metiers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kenda.models.Connexion;
import org.kenda.models.partenaires.Partenaire;
import redis.clients.jedis.Jedis;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class PartenaireMetier {

    ObjectMapper obj = new ObjectMapper();
    Jedis jedis = Connexion.jedis;
    public String addPartenaire(Partenaire partenaire) throws JsonProcessingException {
        partenaire.id = UUID.randomUUID().node();
        String part = obj.writeValueAsString(partenaire);
        jedis.sadd("partenaires", partenaire.id+"");//Pour avoir la liste des partenaires plus tard.
        return jedis.set(partenaire.id+"", part);
    }

    public String upDatePartenaire(Partenaire partenaire) throws JsonProcessingException {
        //partenaire.id = UUID.randomUUID().node();

        //
        String part = jedis.get(partenaire.id+"");
        //
        JsonNode jsonNode = obj.readTree(part);
        Long id = Long.getLong(jsonNode.get("id").asText());
        Partenaire p = new Partenaire();
        if(id.equals(partenaire.id)){
            String pa = obj.writeValueAsString(partenaire);
            return jedis.set(partenaire.id+"", pa);
        }else{
            return "Pas d'enregistrement.";
        }
    }

    public Set<String> getAllPartenaire() {
        Set<String> partenaires = jedis.smembers("partenaires");
        return partenaires;
    }

    public Partenaire getPartenaire(String id) throws JsonProcessingException {
        String contenu = jedis.get(id);
        Partenaire partenaire = obj.readValue(contenu, Partenaire.class);
        return partenaire;
    }
}
