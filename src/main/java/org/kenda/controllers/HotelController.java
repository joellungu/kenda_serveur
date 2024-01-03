package org.kenda.controllers;

import org.kenda.models.Companie.Companie;
import org.kenda.models.Hotels.Hotel;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/hotel")
public class HotelController {
    //

    @GET
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hotel> list() {
        return Hotel.listAll();
    }

    @GET
    @Path("all")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hotel> listAll() {
        return Hotel.listAll();
    }

    @GET
    @Path("/{id}")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Companie get(Long id) {
        return Hotel.findById(id);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Hotel hotel) {
        hotel.persist();
        /*
        if(partenaire.isPersistent()){
            partenaire.idPartenaire = partenaire.id;
        }
        */
        return Response.ok().build();
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel update(Hotel companie) {
        Hotel entity = Hotel.findById(companie.id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;
        entity.nom = companie.nom;
        entity.postnom = companie.postnom;
        entity.prenom = companie.telephone;
        entity.sexe = companie.email;
        entity.email = companie.email;
        entity.telephone = companie.telephone;
        entity.denomination = companie.denomination;
        entity.adresseEtablissement = companie.adresseEtablissement;
        entity.rccm = companie.rccm;
        entity.idnat = companie.idnat;
        entity.numeroImpot = companie.numeroImpot;
        entity.provinceSiege = companie.provinceSiege;
        entity.typeEtablissement = companie.typeEtablissement;
        entity.status = companie.status;
        entity.photo =  companie.photo;
        entity.code =  companie.code;
        entity.token =  companie.token;
        //
        return entity;
    }

    @PUT
    @Path("logo/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel updateLogo(@PathParam("id") Long id, byte[] photo) {
        Hotel entity = Hotel.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;
        entity.photo = photo;
        //
        return entity;
    }

    @GET
    @Path("logoU/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel updateLogoU(@PathParam("id") Long id) {
        Hotel entity = Hotel.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        //entity.name = person.name;
        entity.photo = null;
        //
        return entity;
    }


    /*
    @GET
    @Path("/login/{numero}/{password}")
    public Response login(@PathParam("numero") String numero,
                          @PathParam("password") String password) {
        HashMap params = new HashMap();
        //
        params.put("telephone",numero);
        params.put("motdepasse",password);
        //
        Predicate<Companie> p = a -> a.motdepasse.equals(password) && a.telephone.equals(numero);
        Optional partenaires = Companie.list("telephone =:telephone and motdepasse =:motdepasse ",params).stream().findFirst();
        //Partenaire agent = partenaires.get(0)
        try{
            return Response.ok(partenaires).build();
        }catch (Exception ex){
            return Response.serverError().build();
        }
        //return Agent.findById(id);
    }

*/
    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Hotel partenaire = Hotel.findById(id);
        if(partenaire == null) {
            throw new NotFoundException();
        }
        partenaire.delete();
    }


    @GET
    @Path("profil.png")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getBackground(@QueryParam("id") long id){
        Hotel companie = Hotel.findById(id);
        //return partenaire.photo;

        if(companie.photo != null){
            System.out.println("La photo: "+companie.photo+" : "+companie.photo.length);
            return Response.ok(companie.photo).build();
        }else {
            System.out.println("La photo: "+companie.photo+" : "+companie.photo);
            return Response.status(404).build();
        }
    }

}
