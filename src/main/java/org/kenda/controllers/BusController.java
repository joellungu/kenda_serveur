package org.kenda.controllers;

import org.kenda.models.agents.Agent;
import org.kenda.models.bus.Bus;
import org.kenda.models.partenaires.Partenaire;

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

        return Bus.list("idPartenaire",idPartenaire);
    }

    @GET
    @Path("/{id}")
    public Bus get(Long id) {
        return Bus.findById(id);
    }

    @GET
    @Path("via/{id}")
    public Bus getBusVia(Long id) {
        return Bus.find("idPartenaire",id).firstResult();
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
        Bus bus1 = Bus.findById(id);
        if(bus1 == null) {
            throw new NotFoundException();
        }
        bus1.nom = bus.nom;
        bus1.marque = bus.marque;
        bus1.type = bus.type;
        bus1.numeroChassis = bus.numeroChassis;
        bus1.dateAchat = bus.dateAchat;
        bus1.dateMiseenservice = bus.dateMiseenservice;
        bus1.capacite = bus.capacite;
        bus1.caracteristiques = bus.caracteristiques;
        bus1.kilometrage = bus.kilometrage;
        bus1.climatisation = bus.climatisation;
        bus1.logo = bus.logo;


        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;

        return bus1;
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

    @GET
    @Path("bus.png")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getBackground(@QueryParam("id") long id){
        Bus bus = Bus.findById(id);
        return bus.logo;
    }
}
