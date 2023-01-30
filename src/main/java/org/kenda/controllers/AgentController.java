package org.kenda.controllers;

import org.kenda.models.agents.Agent;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

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

        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;
        entity.update(agent);
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
