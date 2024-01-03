package org.kenda.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kenda.models.trajets.TrajetEvolution;
import org.kenda.models.utilisateurs.Utilisateur;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

@Path("utilisateur")
public class UtilisateurController {

    @GET
    @Path("verification/{telephone}/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verification(@PathParam("telephone") String telephone,
                                 @PathParam("code") String code) throws URISyntaxException {
        Utilisateur utilisateur = Utilisateur.find("numero",telephone).firstResult();
        HashMap rep = new HashMap();
        int c = getToken(telephone, code);
        if(c == -1){
            rep.put("erreur", "oui");
            rep.put("message", "Un problème est survenu lors de l'envoi du code");
            return Response.ok(rep).build();
        }else {
            if (utilisateur == null) {
                rep.put("erreur", "non");
                rep.put("compte", "non");
                rep.put("code", code);
                rep.put("message", "Vous n'avez pas de compte");

                return Response.ok(rep).build();
            } else {
                rep.put("erreur", "non");
                rep.put("compte", "oui");
                rep.put("utilisateur", utilisateur);
                rep.put("code", code);
                rep.put("message", "Vous avez déjà un compte");

                return Response.ok(rep).build();
            }
        }

    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response creer(Utilisateur utilisateur){
        utilisateur.persist();
        return Response.ok(utilisateur).build();
    }

    @PUT
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response mettreAjour(Utilisateur utilisateur){
        Utilisateur utilisateur1 = Utilisateur.findById(utilisateur.id);
        if(utilisateur1 == null){
            return Response.serverError().build();
        }
        utilisateur1.nom = utilisateur.nom;
        utilisateur1.numero = utilisateur.numero;
        utilisateur1.datenaissance = utilisateur.datenaissance;

        return Response.ok(utilisateur1).build();
    }

    @POST
    @Path("sends/{telephone}/{contenu}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listAll(@PathParam("telephone") String telephone, @PathParam("contenu") String contenu) throws URISyntaxException {
        //
        System.out.println("la requette");
        //
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.orange.com/oauth/v3/token"))
                //.headers("Content-Type", "application/x-www-form-urlencoded")
                //.headers("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .header("Authorization","Basic Q1JGMHVSdlhmamJ6QjVnMHJXMGk3d1FkNFVqbkVKWnI6bUE4NHBWY1JHV0d2TFEzUw==")
                //.headers("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();
        //
        var client = HttpClient.newHttpClient();
        try {
            var reponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(reponse.statusCode());
            System.out.println(reponse.body());
            //
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(reponse.body());
            //
            HashMap corps = new HashMap<>();
            HashMap outboundSMSMessageRequest = new HashMap<>();
            HashMap outboundSMSTextMessage = new HashMap<>();
            //
            outboundSMSTextMessage.put("message",contenu);
            outboundSMSMessageRequest.put("address", "tel:+"+telephone);
            outboundSMSMessageRequest.put("senderAddress", "tel:+243815381693");
            outboundSMSMessageRequest.put("outboundSMSTextMessage", outboundSMSTextMessage);
            //
            corps.put("outboundSMSMessageRequest", outboundSMSMessageRequest);
            //
            /**
             * '{"outboundSMSMessageRequest":{ \
             *         "address": "tel:+{{recipient_phone_number}}", \
             *         "senderAddress":"tel:+{{dev_phone_number}}", \
             *         "outboundSMSTextMessage":{ \
             *             "message": "Hello!" \
             *         } \
             *     } \
             * }' \
             */
            //
            //
            int rep = sendSms(telephone, objectMapper.writeValueAsString(corps),jsonNode.get("access_token").asText());
            return rep+"";
        } catch (IOException e) {
            System.out.println(e);
            return ""+e.toString();
        } catch (InterruptedException e) {
            System.out.println(e);
            return ""+e.toString();
        }
        //
        //System.out.println("la rep: "+request.toString());
        //
        //return Response.ok("ok").build();
    }

    private int getToken(String telephone, String contenu) throws URISyntaxException {
        //
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.orange.com/oauth/v3/token"))
                //.headers("Content-Type", "application/x-www-form-urlencoded")
                //.headers("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .header("Authorization","Basic Q1JGMHVSdlhmamJ6QjVnMHJXMGk3d1FkNFVqbkVKWnI6bUE4NHBWY1JHV0d2TFEzUw==")
                //.headers("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();
        //
        var client = HttpClient.newHttpClient();
        try {
            var reponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(reponse.statusCode());
            System.out.println(reponse.body());
            //
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(reponse.body());
            //
            HashMap corps = new HashMap<>();
            HashMap outboundSMSMessageRequest = new HashMap<>();
            HashMap outboundSMSTextMessage = new HashMap<>();
            //
            outboundSMSTextMessage.put("message"," Votre code d'authentification: "+contenu);
            outboundSMSMessageRequest.put("address", "tel:+"+telephone);
            outboundSMSMessageRequest.put("senderAddress", "tel:+243815381693");
            outboundSMSMessageRequest.put("outboundSMSTextMessage", outboundSMSTextMessage);
            //
            corps.put("outboundSMSMessageRequest", outboundSMSMessageRequest);
            //
            /**
             * '{"outboundSMSMessageRequest":{ \
             *         "address": "tel:+{{recipient_phone_number}}", \
             *         "senderAddress":"tel:+{{dev_phone_number}}", \
             *         "outboundSMSTextMessage":{ \
             *             "message": "Hello!" \
             *         } \
             *     } \
             * }' \
             */
            //
            //
            int rep = sendSms(telephone, objectMapper.writeValueAsString(corps),jsonNode.get("access_token").asText());
            return rep;
        } catch (IOException e) {
            System.out.println(e);
            return -1;
        } catch (InterruptedException e) {
            System.out.println(e);
            return -1;
        }
    }
    private int sendSms(String telephone, String contenu, String token) throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.orange.com/smsmessaging/v1/outbound/tel%3A%2B243815381693/requests"))
                //.headers("Content-Type", "application/x-www-form-urlencoded")
                //.headers("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization","Bearer "+token)
                //.headers("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(contenu))
                .build();
        //
        var client = HttpClient.newHttpClient();
        try {
            var reponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(reponse.statusCode());
            System.out.println(reponse.body());
            return reponse.statusCode();
        } catch (IOException e) {
            System.out.println(e);
            // ""+e.toString();
            return -1;

        } catch (InterruptedException e) {
            System.out.println(e);
            // ""+e.toString();
            return -1;
        }
    }
}
