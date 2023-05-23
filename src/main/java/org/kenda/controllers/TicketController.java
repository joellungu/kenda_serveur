package org.kenda.controllers;

import org.kenda.models.bus.Bus;
import org.kenda.models.tickets.Ticket;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Console;
import java.net.URI;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketController {

    //,

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
    @GET
    @Path("horaire/{idPartenaire}/{dateDepart}/{status}")
    public Response getHoraire(@PathParam("idPartenaire") Long idPartenaire,
                             @PathParam("dateDepart") String dateDepart,
                               @PathParam("status") int status) {
        //
        Map<String, Object> params = new HashMap<>();
        params.put("idBoutique", idPartenaire);
        params.put("dateDepart", dateDepart);
        params.put("status", status);
        System.out.println("le id: "+idPartenaire+" la date: "+dateDepart+" status: "+status);
        //
        try{
            List<Ticket> l = Ticket.list("idBoutique =:idBoutique and dateDepart =: dateDepart and status =: status",params);
            System.out.println("le longueur: "+l.size()+" ---- ");
            if(!l.isEmpty()){
                System.out.println(l.size());
                return Response.ok(l).build();
            }else{
                List<Ticket> ll = new LinkedList<>();
                System.out.println("La liste est vide...");
                return Response.ok(ll).build();
            }
        }catch (Exception ex){
            List<Ticket> ll = new LinkedList<>();

            System.out.println("Erreur du: "+ex.getMessage());
            return Response.ok(ll).build();
        }

    }

    @GET
    @Path("details/{idPartenaire}/{unique}")
    public Response getDetails(@PathParam("idPartenaire") Long idPartenaire,
                               @PathParam("unique") String unique_code) {
        //
        Map<String, Object> params = new HashMap<>();
        params.put("idBoutique", idPartenaire);
        params.put("unique_code", unique_code);
        System.out.println("le id: "+idPartenaire+" la unique_code: "+unique_code+"");
        //
        Ticket ticket = Ticket.find("idBoutique =:idBoutique and unique_code =: unique_code",params).firstResult();
        return Response.ok(ticket).build();
    }

    @POST
    @Transactional
    public Response create(List<Ticket> tickets) {
        tickets.forEach((ticket -> ticket.persist()));
        return Response.ok(tickets).build();
    }

    @PUT
    @Transactional
    public Response update(Ticket ticket) {
        Ticket ticket1 = Ticket.findById(ticket.id);
        if(ticket1 == null) {
            return Response.serverError().build();
        }
        //
        ticket1.status = ticket.status;
        ticket1.idAgent = ticket.idAgent;
        //entity.update(ticket);
        return Response.ok(ticket1).build();
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
