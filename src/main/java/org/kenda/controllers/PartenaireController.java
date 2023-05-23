package org.kenda.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.kenda.metiers.PartenaireMetier;
import org.kenda.models.agents.Agent;
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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

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
        if(partenaire.isPersistent()){
            partenaire.idPartenaire = partenaire.id;
        }
        return Response.created(URI.create("/persons/" + partenaire.id)).build();
    }

    @PUT
    @Transactional
    public Partenaire update(Partenaire partenaire) {
        Partenaire entity = Partenaire.findById(partenaire.id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;
        entity.nom = partenaire.nom;
        entity.adresse = partenaire.adresse;
        entity.telephone = partenaire.telephone;
        entity.email = partenaire.email;
        entity.motdepasse = partenaire.motdepasse;
        entity.categorie = partenaire.categorie;
        entity.status = partenaire.status;
        entity.logo = partenaire.logo;
        //
        return entity;
    }


    @GET
    @Path("/login/{numero}/{password}")
    public Response login(@PathParam("numero") String numero,
                          @PathParam("password") String password) {
        HashMap params = new HashMap();
        //
        params.put("telephone",numero);
        params.put("motdepasse",password);
        //
        Predicate<Partenaire> p = a -> a.motdepasse.equals(password) && a.telephone.equals(numero);
        Optional agent = Partenaire.list("telephone =:telephone and motdepasse =:motdepasse ",params).stream().findFirst();
        //Partenaire agent = partenaires.get(0)
        try{
            return Response.ok(agent).build();
        }catch (Exception ex){
            return Response.serverError().build();
        }
        //return Agent.findById(id);
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

    @GET
    @Path("profil.png")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getBackground(@QueryParam("id") long id){
        Partenaire partenaire = Partenaire.findById(id);
        return partenaire.logo;
    }
}
