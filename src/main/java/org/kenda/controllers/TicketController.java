package org.kenda.controllers;

import org.kenda.models.bus.Bus;
import org.kenda.models.tickets.Ticket;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketController {


    @GET
    @Path("all/{id}")
    public List<Ticket> listAll(@PathParam("id") Long idPartenaire) {
        return Ticket.findTicket(idPartenaire);
    }

    @GET
    @Path("/{id}")
    public Ticket get(Long id) {
        return Ticket.findById(id);
    }

    @POST
    @Transactional
    public Response create(Ticket ticket) {
        ticket.persist();
        return Response.created(URI.create("/persons/" + ticket.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Ticket update(@PathParam("id") Long id, Ticket ticket) {
        Ticket entity = Ticket.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        //
        entity.update(ticket);
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Ticket ticket = Ticket.findById(id);
        if(ticket == null) {
            throw new NotFoundException();
        }
        ticket.delete();
    }
}
