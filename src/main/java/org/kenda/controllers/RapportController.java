package org.kenda.controllers;

import org.kenda.models.Rapports.Rapport;
import org.kenda.models.agents.Agent;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Path("/rapports")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RapportController {

    @GET
    @Path("all/{idPartenaire}/{date}")
    public List<Rapport> listAll(@PathParam("id") Long idPartenaire, @PathParam("date") String date) {
        List<Rapport> rapports = Rapport.find("idPartenaire",idPartenaire).list();
        List<Rapport> rapps = rapports.stream().filter((r)->{
            String[] d1 = date.split("-");
            String[] d2 = r.date.split("-");

            return (d1[0] == d2[0]) && (d1[1] == d2[1]);

        }).collect(Collectors.toList());
        return rapps;
    }

    @GET
    @Path("rapportAgent/{idPartenaire}/{date}/{idAgent}")
    public List<Rapport> listAllAgent(@PathParam("idPartenaire") Long idPartenaire, @PathParam("date") String date
            , @PathParam("idAgent") Long idAgent) {
        List<Rapport> rapports = Rapport.find("idPartenaire",idPartenaire).list();

        System.out.println("longueur: "+rapports.size()+" idPartenaire: "+idPartenaire);
        List<Rapport> rapps = new LinkedList<>();
        for (Rapport r : rapports){
            String[] d1 = date.split("-");
            String[] d2 = r.date.split("-");
            System.out.println("d1: "+date+" d2: "+r.date);
            System.out.println("v1 "+""+d1[0]+"="+d2[0]+"--"+(d1[0].equals(d2[0])));
            System.out.println("v2: "+""+d1[1]+"="+d2[1]+"--"+(d1[1].equals(d2[1])));
            System.out.println("v3: "+(r.idAgent.equals(idAgent)));
            if((d1[0].equals(d2[0])) && (d1[1].equals(d2[1])) && (r.idAgent.equals(idAgent))){
                rapps.add(r);
            }
        }
        return rapps;
    }

    @GET
    @Path("/{id}")
    public Rapport get(@PathParam("id") Long id) {
        return Rapport.findById(id);
    }


    @POST
    @Transactional
    public Response create(Rapport rapport) {
        rapport.persist();
        return Response.created(URI.create("/persons/" + rapport.id)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Agent agent = Agent.findById(id);
        if(agent == null) {
            throw new NotFoundException();
        }
        agent.delete();
    }

}
