package org.kenda.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kenda.models.CommandeService;
import org.kenda.models.paiement.Devise;
import org.kenda.models.paiement.Paiement;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;


@Path("/paiement")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaiementController {

    private final Map<String, CompletableFuture<String>> waitingRequests = new ConcurrentHashMap<>();

    public String lancer(String devise, String telephone, Double m, String reference) {
        System.out.println("la devise: $" + devise + "lE MONTANT: $" + m);
        // String dev = devise == "USD" ? "USD":"CDF";
        // double montant = deviseMetier.conversion(m,1L, devise=="USD");
        //
        String dev = devise.equals("USD") ? "USD" : "CDF";
        double montant = conversion(m, 1L, devise.equals("USD"));
        //
        System.out.println("la devise: $" + devise + "lE MONTANT: $" + montant);
        // String urlPost =
        // "http://41.243.7.46:3006/flexpay/api/rest/v1/paymentService";
        ////////////////// http://41.243.7.46:3006/api/rest/v1/paymentService

        String urlPost = "https://backend.flexpay.cd/api/rest/v1/paymentService";
        String body = "{\n" +
                "  \"merchant\":\"Min_EDU-NC\"," +
                "  \"type\":1," +
                "  \"reference\": \"" + reference + "\"," +
                "  \"phone\": \"" + telephone + "\"," +
                "  \"amount\": \"" + montant + "\"," +
                "  \"currency\":\"" + dev + "\"," +
                "  \"callbackUrl\":\"https://epst-serveur-a595d15d6608.herokuapp.com/paiement/trigger\"" +
                "}";
        /*
         * //montant
         * String body = "{\n" +
         * "  \"merchant\":\"EPSTAPP\"," +
         * "  \"type\":1," +
         * "  \"reference\": \""+reference+"\"," +
         * "  \"phone\": \""+telephone+"\"," +
         * "  \"amount\": \""+montant+"\"," +
         * "  \"currency\":\""+dev+"\"," +
         * "  \"callbackUrl\":\"http://dgc-epst.uc.r.appspot.com\"" +
         * "}";
         */
        var requete = HttpRequest.newBuilder()
                .uri(URI.create(urlPost))
                .header("Content-Type", "application/json")
                .header("Authorization",
                        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJcL2xvZ2luIiwicm9sZXMiOlsiTUVSQ0hBTlQiXSwiZXhwIjoxNzk0NzYxNDYzLCJzdWIiOiJlZGZiYTY0ZTYxNjM1NWMzYjdjZDJjYzZiZTA5NzMzYiJ9.1gps60CJKzY1CP8XgAEvx8ArRAfqD9v5a9PeJr4qA6c")
                // .header("Authorization","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MkkydjNXQkhUUVdpTlg4ejhQVSIsInJvbGVzIjpbIk1FUkNIQU5UIl0sImlzcyI6Ii9sb2dpbiIsImV4cCI6MTczNTY4NjAwMH0.b3H5IvM1cNtQ5I3Xz3Rf3hBO_pbgFgQ5VpdKrFUI3g0")
                // .header("Authorization","Bearer
                // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJcL2xvZ2luIiwicm9sZXMiOlsiTUVSQ0hBTlQiXSwiZXhwIjoxNzM3NTUyMDEwLCJzdWIiOiI4ZTE4NzJlODQwZTc5YjM5OWIxMDliMmYyNjk5YWY3YSJ9.co6sS0YEdCy3v3nja0NHvS5dYnMNmjZPJET_Ri7pB0E")
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

        // return "";
    }

    public String checklancer(String orderNumer) {

        String urlPost = "https://beta-backend.flexpay.cd/api/rest/v1/check/" + orderNumer;
        ////////////////// http://41.243.7.46:3006/api/rest/v1/paymentService
        /*
         * // flexpay
         * String body = "{\n" +
         * "  \"merchant\":\"KACHIDI_BINARY\"," +
         * "  \"type\":1," +
         * "  \"reference\": \""+reference+"\"," +
         * "  \"phone\": \""+telephone+"\"," +
         * "  \"amount\": \""+m+"\"," +
         * "  \"currency\":\""+devise+"\"," +
         * "  \"callbackUrl\":\"http://dgc-epst.uc.r.appspot.com\"" +
         * "}";
         */
        var requete = HttpRequest.newBuilder()
                .uri(URI.create(urlPost))
                .header("Content-Type", "application/json")
                .header("Authorization",
                        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJcL2xvZ2luIiwicm9sZXMiOlsiTUVSQ0hBTlQiXSwiZXhwIjoxNzc3NTQzNDc3LCJzdWIiOiI1YzFhMWM5NjQwMGFkODBkMGVlMmU5OWY0NDlhYjYwZiJ9.fVwocevB2T-ag46QGxiCqEvBC3zyPCqpgL4vONIlj2w")
                .GET()
                // .POST(HttpRequest.BodyPublishers.ofString(body))
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

        // return "";
    }

    Toolkit toolkit;
    Timer timer;

    public void AnnoyingBeep() {
        String reponse = lancer("", "", 1.0, "");
        System.out.println(reponse);
        // reponse = "{"+reponse+"}";
        System.out.println("{" + reponse + "}");
        System.out.println(reponse.getClass());
        // ObjectMapper obj = new ObjectMapper();
        //
        try {
            /*
             * JSONObject obj = new JSONObject(reponse);
             * //JsonNode jn = obj.readTree(reponse);
             * String c1 = obj.get("code").toString();
             * String c2 = obj.get("message").toString();
             * String c3 = obj.get("orderNumber").toString();
             * 
             * System.out.println(obj.get("code").toString());
             * System.out.println(obj.get("message").toString());
             * System.out.println(obj.get("orderNumber").toString());
             * System.out.println("____________________________________________");
             * //
             * if(c1.equals("0")) {
             * toolkit = Toolkit.getDefaultToolkit();
             * timer = new Timer();
             * timer.schedule(new RemindTask(),
             * 5 * 1000, //initial delay
             * 5 * 1000); //subsequent rate
             * }
             */
            //
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    class RemindTask extends TimerTask {
        int numWarningBeeps = 5;

        public void run() {
            if (numWarningBeeps > 0) {
                toolkit.beep();
                System.out.println("Beep!");
                numWarningBeeps--;
            } else {
                toolkit.beep();
                System.out.println("Time's up!");
                timer.cancel(); // Not necessary because
                // we call System.exit
                // System.exit(0); // Stops the AWT thread
                // (and everything else)
            }
        }
    }

    @Path("paie")
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response lancerPaiment(CommandeService commandeService
    // HashMap paiement
    ) throws InterruptedException, JsonProcessingException {
        //
        //
        String rep = lancer(commandeService.currency, commandeService.phone, commandeService.amount,
                commandeService.reference);
        CompletableFuture<String> future = new CompletableFuture<>();
        waitingRequests.put(commandeService.reference, future);
        ObjectMapper obj = new ObjectMapper();

        try {
            // Attendre que la future soit complétée par une autre requête
            String result = future.get(); // Bloque jusqu'à ce que la future soit complétée

            JsonNode repCheck = obj.readTree(result);
            //repCheck["transaction"]['status']
            if(repCheck.get("code").asText().equals("0") ||
                    repCheck.get("transaction").get("status").asInt() == (0)){
                String reponse = "Paiement éffectué";
                commandeService.ticketList.forEach((t)-> t.persist());
                return Response.status(200).entity(reponse).build();

            } else {
                //
                String reponse = "Paiement non éffectué";
                //
                return Response.status(404).entity(reponse).build();
            }
            //

            //return Response.ok(result).build();
        } catch (ExecutionException e) {
            // return Response.serverError().entity("Error: " + e.getMessage()).build();
            return Response.status(404).entity(e.getMessage()).build();
        } finally {
            // Nettoyer la future après utilisation
            waitingRequests.remove("joellungu123");
            // return Response.serverError().entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("check/{orderNumer}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String checkPaiment(@PathParam("orderNumer") String orderNumer) {
        //

        // System.out.println("Le montant: "+paiement.amount);
        // System.out.println("Le devise: "+paiement.callbackurl);
        // System.out.println("Le phone: "+paiement.phone);
        // System.out.println("Le montant: ");

        // paiement.persist();
        // AnnoyingBeep();
        //
        return checklancer(orderNumer);
    }

    @Path("/devise")
    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteTaux(Devise devise) {
        //
        Devise.deleteAll();
        //
    }

    @Path("/devise")
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public double setTaux(Devise devise) {
        //
        devise.persist();
        return devise.taux;
    }

    @Path("/devise")
    @GET
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public double getTaux() {
        //
        try {
            Devise devise = Devise.findAll().firstResult();
            return devise.taux;
        } catch (Exception ex) {
            return 0;
        }
    }

    @Path("/paiee")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String verificationPaiment() {
        //
        // AnnoyingBeep();
        //
        // return lancer("",1,"");
        return "";
    }

    /*
     * @Path("/devise")
     * 
     * @POST
     * 
     * @Consumes(MediaType.APPLICATION_JSON)
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * 
     * @Transactional
     * public void setDevise(Devise devise) {
     * //
     * devise.persist();
     * //
     * }
     */

    //
    private double conversion(Double montant, Long id, Boolean de) {
        Devise devise = Devise.findAll().firstResult();
        if (devise != null) {
            System.out.println("Taux: " + devise.taux);
        }
        double d = devise != null ? devise.taux : 2027;
        double prct = (7 * montant) / 100;
        if (de) {
            System.out.println("En dollar: " + de);
            return (montant + prct) / d;
        } else {
            System.out.println("En franc: " + de);
            return montant + prct;
        }
    }

    @POST
    @Path("/trigger")
    public Response triggerRequest(String reponse) {
        /*
         * if (waitingRequests.isEmpty()) {
         * return Response.ok("No requests are waiting.").build();
         * }
         */
        //
        System.out.println("Reponse: " + reponse);
        //
        HashMap hashRep = new HashMap<>();
        //
        ObjectMapper mapper = new ObjectMapper();
        //
        try {
            JsonNode result = mapper.readTree(reponse);
            //
            // Compléter la première future en attente (ou une spécifique selon votre
            // logique)
            Iterator iterator = waitingRequests.entrySet().iterator();
            //
            while (iterator.hasNext()) {
                //
                Map.Entry<String, CompletableFuture<String>> entry = (Map.Entry<String, CompletableFuture<String>>) iterator
                        .next();
                //
                String requestId = entry.getKey();
                System.out.println("requestId: " + requestId);
                if (result.get("reference").asText().equals(requestId)) {
                    //
                    CompletableFuture<String> future = entry.getValue();
                    //
                    // Compléter la future pour débloquer la requête en attente
                    //
                    future.complete(reponse);
                }
                //
            }
            //
        } catch (Exception ex) {
            System.out.println("Erreur 1: " + ex.getMessage());
            System.out.println("Erreur 2: " + ex.getCause());
            // future.complete(reponse);
        }

        return Response.ok("Ok").build();
    }

}
