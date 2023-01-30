package org.kenda.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.kenda.metiers.PartenaireMetier;
import org.kenda.models.bus.Bus;
import org.kenda.models.courses.Course;
import org.kenda.models.partenaires.Partenaire;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Path("/partenaires")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PartenaireController {


    @GET
    public List<Partenaire> list() {
        return Partenaire.listAll();
    }

    @GET
    @Path("/{id}")
    public Partenaire get(Long id) {
        return Partenaire.findById(id);
    }

    @POST
    @Transactional
    public Response create(Partenaire partenaire) {
        partenaire.persist();
        return Response.created(URI.create("/persons/" + partenaire.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Partenaire update(@PathParam("id") Long id, Partenaire partenaire) {
        Partenaire entity = Partenaire.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;
        entity.update(partenaire);
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Partenaire partenaire = Partenaire.findById(id);
        if(partenaire == null) {
            throw new NotFoundException();
        }
        partenaire.delete();
    }
}
