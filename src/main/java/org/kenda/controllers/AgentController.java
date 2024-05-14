package org.kenda.controllers;

import org.kenda.models.agents.Agent;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Path("/agent")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AgentController {

    @GET
    @Path("all/{id}")
    public List<Agent> listAll(@PathParam("id") Long idPartenaire) {
        return Agent.find("idPartenaire",idPartenaire).list();
    }

    @GET
    @Path("/{id}")
    public Agent get(@PathParam("id") Long id) {
        return Agent.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{numero}/{password}")
    public Response login(@PathParam("numero") String numero,
                       @PathParam("password") String password) {
        System.out.println("numero: "+numero+" | password: "+password);
        HashMap params = new HashMap();
        params.put("numero",numero);
        params.put("password",password);

        //Predicate<Agent> p = a -> a.password.equals(password) && a.numero.equals(numero);
        Agent agent = (Agent) Agent.find("numero =: numero and password =: password",params).firstResult();
        //Agent agent = agents.stream().filter(p).findFirst().get();
        if (agent != null) {
            return Response.ok(agent).build();
        } else {
            return Response.status(404).build();
        }
        //return Agent.findById(id);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Agent agent) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("numero",agent.numero);

        Agent utilisater = Agent.find("numero =:numero ",params).firstResult();
        if(utilisater == null){
            agent.persist();
            return Response.ok(agent).build();
        }else{
            return Response.status(405).entity("Ce numéro exist déjà veuillez-vous connecter.").build();
        }

        //agent.persist();
        //return Response.created(URI.create("/persons/" + agent.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Agent update(@PathParam("id") Long id, Agent agent) {
        Agent entity = Agent.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

         //Long idPartenaire;
         entity.nom = agent.nom;
         entity.postnom = agent.postnom;
         entity.prenom = agent.prenom;
         entity.numero = agent.numero;
         entity.email = agent.email;
         entity.adresse = agent.adresse;
         entity.password = agent.password;
         entity.role = agent.role;
         entity.roletitre = agent.roletitre;
         entity.actif = agent.actif;
         //
         // map all fields from the person parameter to the existing entity
         //entity.name = person.name;
         //entity.update(agent);
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Agent agent = Agent.findById(id);
        if(agent == null) {
            throw new NotFoundException();
        }
        agent.delete();
    }

}
