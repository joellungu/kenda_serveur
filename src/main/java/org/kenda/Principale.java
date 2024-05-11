package org.kenda;

import org.kenda.models.Connexion;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class Principale {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Connexion.seConnecter();
        return "Hello from RESTEasy Reactive";
    }
}