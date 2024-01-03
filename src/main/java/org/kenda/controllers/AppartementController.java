package org.kenda.controllers;

import org.kenda.models.Hotels.Appartement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Path("appartement")
public class AppartementController {
    //

    @GET
    @Path("all/{idHotel}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appartement> getAllByHotelId(@PathParam("idHotel") Long idHotel) {
        //
        Predicate<Appartement> p = pp -> pp.idHotel == idHotel;
        //
        List<Appartement> appartements = Appartement.listAll();
        List<Appartement> apps = appartements.stream().filter(p).collect(Collectors.toList());
        //
        return apps;
    }
    //
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerApparte(Appartement appartement) {
        //
        appartement.persist();
        //
        return Response.ok().build();
    }
    //
    @PUT
    @Path("appartement/{idHotel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHotel(@PathParam("idHotel") Long idHotel, boolean status) {
        //
        Appartement appartement = Appartement.find("idHotel",idHotel).firstResult();
        //
        return Response.ok(appartement).build();
    }
    //
    @DELETE
    @Path("all/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deteleHotel(@PathParam("id") Long id) {
        //
        Appartement.deleteById(id);
        //
    }
}
