package org.kenda.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.kenda.metiers.CourseMetier;
import org.kenda.models.agents.Agent;
import org.kenda.models.courses.Course;
import org.kenda.models.transon.Tronson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseController {

    @Inject
    CourseMetier courseMetier;
    //


    @GET
    @Path("/all")
    public Response getAll() {
        //List<Course>
        return Response.ok(Course.listAll()).build();
    }

    @GET
    @Path("/recherche/{lieudepart}/{lieuarrive}/")
    public Response getRecherche(String lieudepart, String lieuarrive) {
        List<Course> all = Course.listAll();
        List<Course> resultat = new LinkedList<>();
        all.forEach((c)->{
            if(c.lieuArrive.toLowerCase().equals(lieuarrive.toLowerCase()) &&
                    c.lieuDepart.toLowerCase().equals(lieudepart.toLowerCase())){
                resultat.add(c);
            }
        });
        return Response.ok(resultat).build();
    }

    @GET
    @Path("/all/{idPartenaire}")
    public Response getAllCourses(@PathParam("idPartenaire") Long idPartenaire) {
        List<Course> l = Course.find("idPartenaire", idPartenaire).list();
        return Response.ok(l).build();
    }

    @POST
    @Transactional
    public Response creerCourse(Course course){
        //JsonMapper jsonMapper = new JsonMapper();
        try {
            course.troncons.forEach((t)->{
                System.out.println(t.prix);
                System.out.println(t.active);
                System.out.println(t.arretArrive.get("nom"));
                System.out.println(t.arretDepart.get("nom"));
            });
            course.persist();
            return Response.ok("Cool").build();
            //
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.ok(ex.getMessage()).build();
        }
        //
    }
}
