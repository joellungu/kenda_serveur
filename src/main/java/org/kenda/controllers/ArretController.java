package org.kenda.controllers;

import org.kenda.models.arrets.Arret;
import org.kenda.models.bus.Bus;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/arrets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArretController {

    @GET
    public List<Arret> list() {
        return Arret.listAll();
    }

    @GET
    @Path("all")
    public List<Arret> getAllList() {
        return Arret.listAll();
    }

    @GET
    @Path("/{id}")
    public Arret getRoute(@PathParam("id") Long id) {
        return Arret.findById(id);
    }

    @GET
    @Path("/route/{route}")
    public List<Arret> getAllRouteBy(@PathParam("route") String route) {
        return Arret.list("route",route);
    }

    @POST
    @Transactional
    public Response create(Arret arret) {
        arret.persist();
        return Response.created(URI.create("/persons/" + arret.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Arret update(@PathParam("id") Long id, Arret arret) {
        Arret entity = Arret.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;
        //entity.update(arret);
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Arret arret = Arret.findById(id);
        if(arret == null) {
            throw new NotFoundException();
        }
        arret.delete();
    }
}
