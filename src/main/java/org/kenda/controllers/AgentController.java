package org.kenda.controllers;

import org.kenda.models.agents.Agent;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Path("/agents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AgentController {

    @GET
    @Path("all/{id}")
    public List<Agent> listAll(@PathParam("id") Long idPartenaire) {
        return Agent.findAgents(idPartenaire);
    }

    @GET
    @Path("/{id}")
    public Agent get(@PathParam("id") Long id) {
        return Agent.findById(id);
    }

    @GET
    @Path("/login/{numero}/{password}")
    public Response login(@PathParam("numero") String numero,
                       @PathParam("password") String password) {
        Predicate<Agent> p = a -> a.password.equals(password) && a.numero.equals(numero);
        List<Agent> agents = Agent.listAll();
        Agent agent = agents.stream().filter(p).findFirst().get();
        try{
            return Response.ok(agent).build();
        }catch (Exception ex){
            return Response.ok(null).build();
        }
        //return Agent.findById(id);
    }

    @POST
    @Transactional
    public Response create(Agent agent) {
        agent.persist();
        return Response.created(URI.create("/persons/" + agent.id)).build();
    }

    @PUT
    @Path("/{id}")
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
