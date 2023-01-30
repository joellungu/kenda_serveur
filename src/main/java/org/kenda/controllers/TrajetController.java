package org.kenda.controllers;

import org.kenda.models.tickets.Ticket;
import org.kenda.models.trajets.TrajetEvolution;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/trajets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrajetController {


    @GET
    @Path("all/{id}")
    public List<TrajetEvolution> listAll(@PathParam("id") Long idCourse) {
        return TrajetEvolution.findTrajetEvolutions(idCourse);
    }

    @GET
    @Path("/{id}")
    public TrajetEvolution get(Long id) {
        return TrajetEvolution.findById(id);
    }

    @POST
    @Transactional
    public Response create(TrajetEvolution trajetEvolution) {
        trajetEvolution.persist();
        return Response.created(URI.create("/persons/" + trajetEvolution.id)).build();
    }

}
