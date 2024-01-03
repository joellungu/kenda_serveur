package org.kenda.controllers;

import org.kenda.models.agents.Agent;
import org.kenda.models.agents.AgentHotel;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

@Path("/agenthotel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AgentHotellController {
    //

    @GET
    @Path("all/{id}")
    public List<AgentHotel> listAll(@PathParam("id") Long idPartenaire) {
        return AgentHotel.find("idPartenaire",idPartenaire).list();
    }

    @GET
    @Path("/{id}")
    public AgentHotel get(@PathParam("id") Long id) {
        return AgentHotel.findById(id);
    }

    @GET
    @Path("/login/{numero}/{password}")
    public Response login(@PathParam("numero") String numero,
                          @PathParam("password") String password) {
        System.out.println("numero: "+numero+" | password: "+password);
        Predicate<AgentHotel> p = a -> a.password.equals(password) && a.numero.equals(numero);
        List<AgentHotel> agents = AgentHotel.listAll();
        AgentHotel agent = agents.stream().filter(p).findFirst().get();
        try{
            return Response.ok(agent).build();
        }catch (Exception ex){
            return Response.ok(null).build();
        }
        //return Agent.findById(id);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AgentHotel agent) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("numero",agent.numero);

        AgentHotel utilisater = AgentHotel.find("numero =:numero ",params).firstResult();
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
    @Transactional
    public AgentHotel update(@PathParam("id") Long id, Agent agent) {
        AgentHotel entity = AgentHotel.findById(id);
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
        AgentHotel agent = AgentHotel.findById(id);
        if(agent == null) {
            throw new NotFoundException();
        }
        agent.delete();
    }

}
