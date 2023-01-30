package org.kenda.controllers;

import org.kenda.models.agents.Agent;
import org.kenda.models.bus.Bus;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/bus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BusController {


    @GET
    @Path("all/{id}")
    public List<Bus> listAll(@PathParam("id") Long idPartenaire) {
        return Bus.findBus(idPartenaire);
    }

    @GET
    @Path("/{id}")
    public Bus get(Long id) {
        return Bus.findById(id);
    }

    @POST
    @Transactional
    public Response create(Bus bus) {
        bus.persist();
        return Response.created(URI.create("/persons/" + bus.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Bus update(@PathParam("id") Long id, Bus bus) {
        Bus entity = Bus.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;
        entity.update(bus);
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Bus bus = Bus.findById(id);
        if(bus == null) {
            throw new NotFoundException();
        }
        bus.delete();
    }
}
