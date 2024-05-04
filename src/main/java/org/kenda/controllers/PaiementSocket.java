package org.kenda.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kenda.models.paiement.Devise;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@ServerEndpoint("/paiementbillet/{reference}")
@ApplicationScoped
public class PaiementSocket {
    //
    Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session
            , @PathParam("reference") String reference) throws JsonProcessingException {
        //broadcast("User " + username + " joined");
        HashMap rep = new HashMap();
        rep.put("ref", reference);
        rep.put("message", "Connexion bien établie");
        /*
        commande.put("phone",phone);
        commande.put("amount",amount);
        commande.put("currency",currency);
        */
        //String rep = lancer(currency,phone,amount,reference);
        //
        ObjectMapper obj = new ObjectMapper();
        String r = obj.writeValueAsString(rep);
        //
        etablissementDeConnexion(session, r);
        //
        sessions.put(reference, session);
        System.out.println("Connexion établie avec: "+reference);
    }

    @OnClose
    public void onClose(Session session, @PathParam("reference") String reference) {
        sessions.remove(reference);
        //broadcast("User " + reference + " left");
    }

    @OnError
    public void onError(Session session, @PathParam("reference") String reference, Throwable throwable) {
        sessions.remove(reference);
        //broadcast("User " + reference + " left on error: " + throwable);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("reference") String reference) throws JsonProcessingException, ExecutionException, InterruptedException {
        //
        ObjectMapper obj = new ObjectMapper();
        JsonNode r = obj.readTree(message);
        //
        if(r.get("paie") != null){
            String currency = r.get("currency").asText();
            String phone = r.get("phone").asText();
            double amount = r.get("amount").asDouble();
            //String reference = "";
            //
            //String rep = lancer(currency,phone,amount,reference);
            //
            ExecutorService threadpool = Executors.newCachedThreadPool();
            Future<String> futureTask = threadpool.submit(() -> lancer(currency,phone,amount,reference));

            while (!futureTask.isDone()) {
                System.out.println("FutureTask is not finished yet...");
            }
            String result = futureTask.get();

            threadpool.shutdown();
            //
            reponseDeLaRequete(reference, result);
            //
        }else if(r.get("check") != null){
            //Ici je check...
        } else {

        }
        //
        //broadcast(">> " + username + ": " + message);
    }

    private void etablissementDeConnexion(Session s, String message) {
        s.getAsyncRemote().sendObject(message, result ->  {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

    private void reponseDeLaRequete(String ref, String message) {
        Session s = sessions.get(ref);
        s.getAsyncRemote().sendObject(message, result ->  {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result ->  {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }

    public String lancer(String devise, String telephone, Double m, String reference) {
        System.out.println("la devise: $"+devise+" lE MONTANT: $"+m);
        String dev = devise.equals("USD") ? "USD":"CDF";
        double montant = conversion(m,1L, devise.equals("USD"));
        System.out.println("la devise: $"+dev+" lE MONTANT: $"+montant);
        String urlPost = "https://beta-backend.flexpay.cd/api/rest/v1/paymentService";
        //////////////////http://41.243.7.46:3006/api/rest/v1/paymentService
        //flexpay
        String body = "{\n" +
                "  \"merchant\":\"KACHIDI_BINARY\"," +
                "  \"type\":1," +
                "  \"reference\": \""+reference+"\"," +
                "  \"phone\": \""+telephone+"\"," +
                "  \"amount\": \""+m+"\"," +
                "  \"currency\":\""+devise+"\"," +
                "  \"callbackUrl\":\"http://dgc-epst.uc.r.appspot.com\"" +
                "}";
        var requete = HttpRequest.newBuilder()
                .uri(URI.create(urlPost))
                .header("Content-Type","application/json")
                .header("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJcL2xvZ2luIiwicm9sZXMiOlsiTUVSQ0hBTlQiXSwiZXhwIjoxNzc3NTQzNDc3LCJzdWIiOiI1YzFhMWM5NjQwMGFkODBkMGVlMmU5OWY0NDlhYjYwZiJ9.fVwocevB2T-ag46QGxiCqEvBC3zyPCqpgL4vONIlj2w")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        var client = HttpClient.newHttpClient();
        try {
            var reponse = client.send(requete, HttpResponse.BodyHandlers.ofString());
            System.out.println(reponse.statusCode());
            System.out.println(reponse.body());
            return reponse.body();
        } catch (IOException e) {
            System.out.println(e);
            return "";
        } catch (InterruptedException e) {
            System.out.println(e);
            return "";
        }

        //return "";
    }

    private double conversion(Double montant, Long id, Boolean de) {
        Devise devise = Devise.findAll().firstResult();
        if(devise != null){
            System.out.println("Taux: "+devise.taux);
        }
        double d = devise != null ? devise.taux : 2027;
        double prct = (5 * montant) / 100;
        if (de) {
            System.out.println("En dollar: "+de);
            return (montant + prct) / d;
        } else {
            System.out.println("En franc: "+de);
            return montant + prct;
        }
    }

}
