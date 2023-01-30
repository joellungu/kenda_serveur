package org.kenda.controllers;

import org.kenda.models.agents.Agent;
import org.kenda.models.arrets.Arret;
import org.kenda.models.itinerance.Itinerance;
import org.kenda.models.trajets.TrajetEvolution;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

@Path("/itinerances")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItineranceController {

    @Inject
    Itinerance itinerance;

    @GET
    @Path("all/{depart}/{arrive}")
    public List<HashMap<String, Object>> listAll(@PathParam("depart") String depart,
                                                @PathParam("arrive") String arrive) {
        if(depart.equals("Kinshasa")){
            return itinerance.getListeDesArrets1(arrive);
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else if(depart.equals("")){
            return itinerance.listeKinshasaGoma();
        }else {
            return itinerance.listeKinshasaGoma();
        }

    }

    @GET
    @Transactional
    public void addRoute() {
        itinerance.addArretKnKc();
    }


}
