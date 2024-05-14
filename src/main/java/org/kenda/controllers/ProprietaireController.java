package org.kenda.controllers;

import org.kenda.models.Proprietaire.Proprietaire;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

@Path("proprietaire")
public class ProprietaireController {
    @GET
    @Path("all/{id}")
    public List<Proprietaire> listAll(@PathParam("id") Long idPartenaire) {
        return Proprietaire.find("idPartenaire",idPartenaire).list();
    }

    @GET
    @Path("/{id}")
    public Proprietaire get(@PathParam("id") Long id) {
        return Proprietaire.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{telephone}/{password}")
    public Response login(@PathParam("telephone") String telephone,
                          @PathParam("password") String password) {
        System.out.println("telephone: "+telephone+" | password: "+password);
        HashMap params = new HashMap();
        params.put("telephone",telephone);
        params.put("password",password);

        //Predicate<Agent> p = a -> a.password.equals(password) && a.numero.equals(numero);
        Proprietaire proprietaire = (Proprietaire) Proprietaire.find("telephone =: telephone and password =: password",params).firstResult();
        //Agent agent = agents.stream().filter(p).findFirst().get();
        if (proprietaire != null) {
            return Response.ok(proprietaire).build();
        } else {
            return Response.status(404).build();
        }
        //return Agent.findById(id);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Proprietaire proprietaire) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("telephone",proprietaire.telephone);

        Proprietaire proprietaire1 = Proprietaire.find("telephone =:telephone ",params).firstResult();
        if(proprietaire1 == null){
            proprietaire.persist();
            return Response.ok(proprietaire).build();
        }else{
            return Response.status(405).entity("Ce numéro exist déjà veuillez-vous connecter.").build();
        }

        //agent.persist();
        //return Response.created(URI.create("/persons/" + agent.id)).build();
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response mettreajour(Proprietaire proprietaire) {

        //HashMap<String, Object> params = new HashMap<>();
        //params.put("telephone",proprietaire.telephone);

        Proprietaire proprietaire1 = Proprietaire.findById(proprietaire.id);
        if(proprietaire1 == null){
            return Response.status(405).entity("Ce numéro exist déjà veuillez-vous connecter.").build();
        }

        //
        proprietaire1.nom = proprietaire.nom;
        proprietaire1.postnom = proprietaire.postnom;
        proprietaire1.prenom = proprietaire.prenom;
        proprietaire1.email = proprietaire.email;
        proprietaire1.telephone = proprietaire.telephone;
        proprietaire1.password = proprietaire.password;
        //
        return Response.ok().build();

        //agent.persist();
        //return Response.created(URI.create("/persons/" + agent.id)).build();
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Proprietaire proprietaire = Proprietaire.findById(id);
        if(proprietaire == null) {
            throw new NotFoundException();
        }
        proprietaire.delete();
    }
}
