package org.kenda.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.kenda.metiers.CourseMetier;
import org.kenda.models.agents.Agent;
import org.kenda.models.bus.Bus;
import org.kenda.models.courses.Course;
import org.kenda.models.transon.Tronson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.Period;
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
    //
    @GET
    @Path("/recherche/{troncons}")
    public Response rechercheAll(@PathParam("troncons") String troncons) {
        //
        List<Course> list = Course.list("troncons",troncons);
        return Response.ok(list).build();
    }

    @GET
    @Path("/recherche/{lieudepart}/{lieuarrive}/{date}/")
    public Response getRecherche(@PathParam("idPartenaire") String lieudepart,
                                 @PathParam("idPartenaire") String lieuarrive,
                                 @PathParam("idPartenaire") String date) {
        List<Course> all = Course.listAll();
        System.out.println("La taille: "+all.size());
        List<Course> resultat = new LinkedList<>();
        LocalDate compare = LocalDate.now();
        LocalDate dt1 = LocalDate.parse(date);
        //LocalDate dt2 = LocalDate.parse("2019-11-27");
        Period period = Period.between(compare, dt1);
        if(compare.isBefore(dt1) && (period.getMonths() <= 2)){

        }else{

        }
        all.forEach((c)->{
            if(c.lieuArrive.toLowerCase().equals(lieuarrive.toLowerCase()) &&
                    c.lieuDepart.toLowerCase().equals(lieudepart.toLowerCase()) && (c.jourDepart == dt1.getDayOfWeek().getValue())){
                resultat.add(c);
            }
        });
        return Response.ok(resultat).build();
    }

    @GET
    @Path("/all/{idPartenaire}/{jour}")
    public Response getAllCourses(@PathParam("idPartenaire") Long idPartenaire,
                                  @PathParam("jour") int jour) {
        List<Course> l2 = Course.find("idPartenaire", idPartenaire).list();
        List<Course> l = new LinkedList<>();
        l2.forEach((c)->{
           if(!c.terminer && c.jourDepart == jour){
               l.add(c);
           }
        });
        return Response.ok(l).build();
    }

    @GET
    @Path("/all/{idPartenaire}/{idAgent}")
    public Response getCoursesAgent(@PathParam("idPartenaire") Long idPartenaire,
                                    @PathParam("idAgent") Long idAgent) {
        List<Course> l2 = Course.find("idPartenaire", idPartenaire).list();
        List<Course> l = new LinkedList<>();
        Course course = new Course();
        for (Course c : l2){
            if(!c.terminer && (c.chauffeur.id.equals(idAgent) ||
                    c.receveur.id.equals(idAgent) || c.embarqueur.id.equals(idAgent)
            )){
                course = c;
                break;
            }
        }
        return Response.ok(course).build();
    }

    @GET
    @Path("/historique/{idPartenaire}/{dates}")
    public Response getAllCourses(@PathParam("idPartenaire") Long idPartenaire,
                                  @PathParam("dates") String dates) {
        List<Course> l2 = Course.find("idPartenaire", idPartenaire).list();
        List<Course> l = new LinkedList<>();
        System.out.println("la date: "+dates);
        l2.forEach((c)->{
            if((c.terminer == true) && c.dates.contains(dates)){
                l.add(c);
            }
        });
        return Response.ok(l).build();
    }

    @POST
    @Transactional
    public Response creerCourse(Course course){
        //JsonMapper jsonMapper = new JsonMapper();
        try {
            /*
            course.troncons.forEach((t)->{
                System.out.println(t.prix);
                System.out.println(t.active);
                System.out.println(t.arretArrive.get("nom"));
                System.out.println(t.arretDepart.get("nom"));
            });
            */
            LocalDate d = LocalDate.now();
            course.dates = d.getDayOfMonth()+"-"+d.getMonthValue()+"-"+d.getYear();
            course.persist();
            return Response.ok("Cool").build();
            //
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.ok(ex.getMessage()).build();
        }
        //
    }
    //
    @PUT
    @Path("/lieu/{id}")
    @Transactional
    public Course updateLieuActuel(@PathParam("id") Long id, String lieu) {
        Course entity = Course.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;
        entity.lieuActuel = lieu;
        return entity;
    }
    //
    @PUT
    @Path("/suspendre/{id}")
    @Transactional
    public Course updateStatus(@PathParam("id") Long id, @QueryParam("status") int status) {
        Course entity = Course.findById(id);
        System.out.println("Le status vaut: "+status);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;
        entity.status = status;
        return entity;
    }
    //
    @DELETE
    @Path("/{id}")
    @Transactional
    public Boolean updateTerminer(@PathParam("id") Long id) {

        return Course.deleteById(id);
    }
    //

}
