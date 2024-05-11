package org.kenda.controllers;

import org.kenda.models.courses.Course;
import org.kenda.models.itinerance.Itinerances;
import org.kenda.models.Companie.Companie;
import org.kenda.models.transon.Tronson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/itinerances")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItineranceController {

    @Inject
    Itinerances itinerance;

    @GET
    @Path("all/{depart}/{arrive}")
    public List<HashMap<String, Object>> listAll(@PathParam("depart") String depart,
                                                @PathParam("arrive") String arrive) {
        if(depart.toLowerCase().equals("Kinshasa".toLowerCase()) ){
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

    @POST
    @Transactional
    public Response saveIt(List<Tronson> tronsons) {
        try{
            tronsons.forEach((t)->{
                t.persist();
            });
            return Response.ok(true).build();
        }catch (Exception ex){
            System.out.println("Erreur du à: "+ex.getMessage());
            return Response.serverError().build();
        }
    }

    @PUT
    @Transactional
    public Response updateIt(List<Tronson> tronsons) {
        try{
            tronsons.forEach((t)->{
                //t.persist();
                try{
                    Tronson tronson = (Tronson) Tronson.findById(t.id);
                    if(tronson != null) {
                        tronson.idPartenaire = t.idPartenaire;
                        tronson.nom = t.nom;
                        tronson.aaProvince = t.aaProvince;
                        tronson.aaLieu = t.aaLieu;
                        tronson.aaLatitude = t.aaLatitude;
                        tronson.aaLongitude = t.aaLongitude;
                        tronson.adProvince = t.adProvince;
                        tronson.adLieu = t.adLieu;
                        tronson.adLatitude = t.adLatitude;
                        tronson.adLongitude = t.adLongitude;
                        tronson.prix = t.prix;
                        tronson.active = t.active;
                    }
                }catch (Exception ex){
                    System.out.println("Du à: "+ex.getMessage());
                }
            });
            return Response.ok(true).build();
        }catch (Exception ex){
            System.out.println("Erreur du à: "+ex.getMessage());
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("delete")
    @Transactional
    public Response supprimerIt(List<Tronson> tronsons) {
        try{
            //
            tronsons.forEach((t)->{
                //t.persist();
                try{
                    Tronson.deleteById(t.id);
                }catch (Exception ex){
                    System.out.println("Du à: "+ex.getMessage());
                }
            });
            return Response.ok(true).build();
        }catch (Exception ex){
            System.out.println("Erreur du à: "+ex.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("allsave/{idPartenaire}")
    public List<Tronson> getTronsons(@PathParam("idPartenaire") Long idPartenaire){
        List<Tronson> liste = Tronson.find("idPartenaire",idPartenaire).list();
        return liste;
    }

    @GET
    @Path("course/{idPartenaire}/{nom}")
    public List<Tronson> getTronsonsCourse(@PathParam("idPartenaire") Long idPartenaire,
                                           @PathParam("nom") String nom){
        List<Tronson> liste = Tronson.find("idPartenaire",idPartenaire).list();
        List<Tronson> l = liste.stream().filter((t)-> t.nom.equals(nom)).collect(Collectors.toList());
        return l;
    }

    @GET
    @Path("tronson/{adLieu}/{aaLieu}/{jour}")
    public List<Course> getTronsonsCourse(
                                           @PathParam("adLieu") String adLieu,
                                           @PathParam("aaLieu") String aaLieu,
                                           @PathParam("jour") int jour){
        Map<String, Object> params = new HashMap<>();
        params.put("adLieu",adLieu);
        params.put("aaLieu",aaLieu);
        //
        System.out.println("adLieu : "+adLieu);//adresse départ
        System.out.println("aaLieu : "+aaLieu);//adresse arrivée

        List<Tronson> tronsons = Tronson.find("adLieu = :adLieu and aaLieu = :aaLieu",params).list();

        System.out.println("tronsons : "+tronsons.size());//tronsons


        List<Course> l = new LinkedList<>();
        //Ticket//yyyy-MM-dd
        //
        for (Tronson t : tronsons){
            System.out.println("tronson : "+t.nom);//
            Map<String, Object> params2 = new HashMap<>();
            params2.put("troncons",t.nom);
            params2.put("jourDepart",jour);
            params2.put("idPartenaire",t.idPartenaire);//Le tronson de chaque entreprise
            params2.put("status", 0);

            List<Course> courses = Course.find("troncons = :troncons and jourDepart = :jourDepart and idPartenaire = :idPartenaire and status = :status", params2).list();
            //.firstResult()
            for (Course course : courses) {
                if (course != null) {
                    course.prix = t.prix;
                    course.bus.logo = new byte[0];
                    Companie partenaire = Companie.findById(course.idPartenaire);
                    if (partenaire != null && partenaire.status == 1) {
                        Companie companie = Companie.findById(course.idPartenaire);
                        course.nomPartenaire = companie.denomination;
                        l.add(course);
                    }
                }
            }
            //
        }
        //
        return l;
    }

    @GET
    @Path("tronson/{adLieu}/{aaLieu}/{jour}/{idPartenaire}")
    public List<Course> getTronsonsCourseCompanie(
            @PathParam("adLieu") String adLieu,
            @PathParam("aaLieu") String aaLieu,
            @PathParam("jour") int jour,
            @PathParam("idPartenaire") long idPartenaire){
        Map<String, Object> params = new HashMap<>();
        params.put("adLieu",adLieu);
        params.put("aaLieu",aaLieu);
        //
        System.out.println("adLieu : "+adLieu);
        System.out.println("aaLieu : "+aaLieu);

        List<Tronson> tronsons = Tronson.find("adLieu = :adLieu and aaLieu = :aaLieu",params).list();

        List<Course> l = new LinkedList<>();
        //Ticket//yyyy-MM-dd
        //
        for (Tronson t : tronsons){
            Map<String, Object> params2 = new HashMap<>();
            params2.put("troncons",t.nom);
            params2.put("jourDepart",jour);
            params2.put("idPartenaire", idPartenaire);


            List<Course> courses = Course.find("troncons = :troncons and jourDepart = :jourDepart and idPartenaire = :idPartenaire", params2).list();
            for (Course course : courses) {
                if(course != null) {
                    course.prix = t.prix;
                    course.bus.logo = new byte[0];
                    Companie partenaire = Companie.findById(course.idPartenaire);
                    if (partenaire != null && partenaire.status == 1) {
                        l.add(course);
                    }
                }
            }
        }
        //
        return l;
    }

    @GET
    @Transactional
    public void addRoute() {
        itinerance.addArretKnKc();
        itinerance.addArretKnBdd();
        itinerance.addArretBddKsi();
        itinerance.addArretKsiKtn();
    }

}
