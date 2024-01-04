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
    @Path("all")
    public List<Ticket> getlistAll() {
        return Ticket.listAll();
    }

    @GET
    @Path("/{id}")
    public Ticket get(Long id) {
        return Ticket.findById(id);
    }
    @GET
    @Path("horaire/{idCourse}/{idPartenaire}/{dateDepart}/{status}")
    public Response getHoraire(@PathParam("idCourse") Long idCourse,
                               @PathParam("idPartenaire") Long idPartenaire,
                             @PathParam("dateDepart") String dateDepart,
                               @PathParam("status") int status) {
        //
        Map<String, Object> params = new HashMap<>();
        params.put("idCourse", idCourse);
        params.put("idBoutique", idPartenaire);
        params.put("dateDepart", dateDepart);
        //params.put("status", status);//and status =: status
        System.out.println("le id: "+idPartenaire+" la date: "+dateDepart+" status: "+status);
        //
        try{
            List<Ticket> l = Ticket.list("idBoutique =:idBoutique and dateDepart =: dateDepart and idCourse =: idCourse",params);
            System.out.println("le longueur: "+l.size()+" ---- ");
            if(!l.isEmpty()){
                List<Ticket> lFinal = new LinkedList<>();
                l.forEach((e)->{
                    if(e.status == 0 || e.status == 1){
                        lFinal.add(e);
                    }
                });
                System.out.println(l.size());
                System.out.println(lFinal.size());
                return Response.ok(lFinal).build();
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

    @GET
    @Path("ticketvendupar/{idAgentVendeur}/{datePaiement}")
    public Response getTicketVenduPar(@PathParam("idAgentVendeur") String idAgentVendeur,
                               @PathParam("datePaiement") String datePaiement) {
        //
        Map<String, Object> params = new HashMap<>();
        params.put("idAgentVendeur", idAgentVendeur);
        params.put("datePaiement", datePaiement);
        System.out.println("le idAgentVendeur: "+idAgentVendeur+" la datePaiement: "+datePaiement+"");
        //
        List<Ticket> tickets = Ticket.find("idAgentVendeur =:idAgentVendeur and datePaiement =: datePaiement",params).list();
        return Response.ok(tickets).build();
    }

    @GET
    @Path("recherche/{idPartenaire}/{phone}/{dateDepart}")
    public Response getRecherche(@PathParam("idPartenaire") Long idPartenaire,
                               @PathParam("phone") String phone,
                                 @PathParam("dateDepart") String dateDepart) {
        //
        System.out.println("Le id: "+idPartenaire+"  == le phone: "+phone);
        //
        Map<String, Object> params = new HashMap<>();
        params.put("idBoutique", idPartenaire);
        params.put("phone", phone);
        params.put("dateDepart", dateDepart);
        System.out.println("le id: "+idPartenaire+" la unique_code: "+phone+"");
        //
        List<Ticket> tickets = Ticket.find("idBoutique =:idBoutique and phone =: phone and dateDepart =: dateDepart",params).list();
        return Response.ok(tickets).build();
    }

    @GET
    @Path("course/{idPartenaire}/{date}")
    public Response getCourse(@PathParam("idPartenaire") Long idPartenaire,
                                 @PathParam("date") String dateDepart) {
        //
        System.out.println("Le id: "+idPartenaire+"  == le date: "+dateDepart);
        //
        Map<String, Object> params = new HashMap<>();
        params.put("idBoutique", idPartenaire);
        //params.put("dateDepart", dateDepart);
        //params.put("status", 1);//and status =: status
        System.out.println("le id: "+idPartenaire+" la unique_code: "+dateDepart+"");
        //
        //
        List<Ticket> ts = new LinkedList<>();
        List<Ticket> tickets = Ticket.find("idBoutique =:idBoutique",params).list();
        //
        tickets.forEach((c)->{
            //
            String[] d = c.dateDepart.split("-");
            String da = d[1]+"-"+d[2];
            System.out.println("la ida: "+dateDepart+" la da: "+da+"");
            if(da.equals(dateDepart)){
                ts.add(c);
            }
        });
        //
        return Response.ok(ts).build();
    }

    @GET
    @Path("courseday/{idPartenaire}/{date}")
    public Response getCourseOfDay(@PathParam("idPartenaire") Long idPartenaire,
                              @PathParam("date") String dateDepart) {
        //
        System.out.println("Le id: "+idPartenaire+"  == le date: "+dateDepart);
        //
        Map<String, Object> params = new HashMap<>();
        params.put("idBoutique", idPartenaire);
        //params.put("dateDepart", dateDepart);
        //params.put("status", 1);//and status =: status
        System.out.println("le id: "+idPartenaire+" la unique_code: "+dateDepart+"");
        //
        //
        List<Ticket> ts = new LinkedList<>();
        List<Ticket> tickets = Ticket.find("idBoutique =:idBoutique",params).list();
        //
        tickets.forEach((c)->{
            //
            String[] d = c.dateDepart.split("-");
            String da = d[1]+"-"+d[2];
            System.out.println("la ida: "+dateDepart+" la da: "+da+"");
            if(c.dateDepart.equals(dateDepart)){
                ts.add(c);
            }
        });
        //
        return Response.ok(ts).build();
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
