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
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Path("/paiement")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaiementController {


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
                "  \"amount\": \""+montant+"\"," +
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

    public String checklancer(String orderNumer) {

        String urlPost = "https://beta-backend.flexpay.cd/api/rest/v1/check/"+orderNumer;
        //////////////////http://41.243.7.46:3006/api/rest/v1/paymentService
        /*
        // flexpay
        String body = "{\n" +
                "  \"merchant\":\"KACHIDI_BINARY\"," +
                "  \"type\":1," +
                "  \"reference\": \""+reference+"\"," +
                "  \"phone\": \""+telephone+"\"," +
                "  \"amount\": \""+m+"\"," +
                "  \"currency\":\""+devise+"\"," +
                "  \"callbackUrl\":\"http://dgc-epst.uc.r.appspot.com\"" +
                "}";
        */
        var requete = HttpRequest.newBuilder()
                .uri(URI.create(urlPost))
                .header("Content-Type","application/json")
                .header("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJcL2xvZ2luIiwicm9sZXMiOlsiTUVSQ0hBTlQiXSwiZXhwIjoxNzc3NTQzNDc3LCJzdWIiOiI1YzFhMWM5NjQwMGFkODBkMGVlMmU5OWY0NDlhYjYwZiJ9.fVwocevB2T-ag46QGxiCqEvBC3zyPCqpgL4vONIlj2w")
                .GET()
                //.POST(HttpRequest.BodyPublishers.ofString(body))
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



    Toolkit toolkit;
    Timer timer;

    public void AnnoyingBeep() {
        String reponse = lancer("","",1.0,"");
        System.out.println(reponse);
        //reponse = "{"+reponse+"}";
        System.out.println("{"+reponse+"}");
        System.out.println(reponse.getClass());
        //ObjectMapper obj = new ObjectMapper();
        //
        try {
            /*
            JSONObject obj = new JSONObject(reponse);
            //JsonNode jn = obj.readTree(reponse);
            String c1 = obj.get("code").toString();
            String c2 = obj.get("message").toString();
            String c3 = obj.get("orderNumber").toString();

            System.out.println(obj.get("code").toString());
            System.out.println(obj.get("message").toString());
            System.out.println(obj.get("orderNumber").toString());
            System.out.println("____________________________________________");
            //
            if(c1.equals("0")) {
                toolkit = Toolkit.getDefaultToolkit();
                timer = new Timer();
                timer.schedule(new RemindTask(),
                        5 * 1000,        //initial delay
                        5 * 1000);  //subsequent rate
            }
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
                //System.exit(0);   // Stops the AWT thread
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
                                  //HashMap paiement
    ) throws InterruptedException, JsonProcessingException {
        //

        //System.out.println("Le montant: "+paiement.amount);
        //System.out.println("Le devise: "+paiement.callbackurl);
        //System.out.println("Le phone: "+paiement.phone);
        //System.out.println("Le montant: ");
        String reponse = "";
        Response repData = null;
        //paiement.persist();
        //AnnoyingBeep();
        //
        String rep = lancer(commandeService.currency, commandeService.phone, commandeService.amount, commandeService.reference);
        ObjectMapper obj = new ObjectMapper();
        JsonNode jsonNode = obj.readTree(rep);
        for(int x = 4; x < 5; x++){
            //

            //jsonNode
            //rep['orderNumber']
            JsonNode repCheck = obj.readTree(checklancer(jsonNode.get("orderNumber").asText()));
            //repCheck["transaction"]['status']
            if(repCheck.get("transaction").get("status").asText().equals("0") ||
                    repCheck.get("transaction").get("status").asInt() == (0)){
                reponse = "Paiement éffectué";
                commandeService.ticketList.forEach((t)-> t.persist());
                repData = Response.status(200).entity(reponse).build();
                break;
            }

            if(repCheck.get("transaction").get("status").asText().equals("1") ||
                    repCheck.get("transaction").get("status").asInt() == (1)){
                reponse = repCheck.get("message").asText();
                repData = Response.status(404).build();
                break;
            }

            if(repCheck.get("transaction").get("status").asText().equals("3") ||
                    repCheck.get("transaction").get("status").asInt() == (3)){
                reponse = repCheck.get("message").asText();
                repData = Response.status(404).build();
                break;
            }

            if(repCheck.get("transaction").get("status").asText().equals("4") ||
                    repCheck.get("transaction").get("status").asInt() == (4)){
                reponse = repCheck.get("message").asText();
                repData = Response.status(404).build();
                break;
            }

            if(repCheck.get("transaction").get("status").asText().equals("5") ||
                    repCheck.get("transaction").get("status").asInt() == (5)){
                reponse = repCheck.get("message").asText();
                repData = Response.status(404).build();
                break;
            }

            System.out.println("La vérification: "+repCheck.asText());

            TimeUnit.SECONDS.sleep(30);

        }
        return repData;
    }

    @Path("check/{orderNumer}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String checkPaiment(@PathParam("orderNumer") String orderNumer) {
        //

        //System.out.println("Le montant: "+paiement.amount);
        //System.out.println("Le devise: "+paiement.callbackurl);
        //System.out.println("Le phone: "+paiement.phone);
        //System.out.println("Le montant: ");

        //paiement.persist();
        //AnnoyingBeep();
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
        }catch (Exception ex){
            return 0;
        }
    }
    @Path("/paiee")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String verificationPaiment() {
        //
        //AnnoyingBeep();
        //
        //return lancer("",1,"");
        return "";
    }

    /*
    @Path("/devise")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void setDevise(Devise devise) {
        //
        devise.persist();
        //
    }
    */

    //
    private double conversion(Double montant, Long id, Boolean de) {
        Devise devise = Devise.findAll().firstResult();
        if(devise != null){
            System.out.println("Taux: "+devise.taux);
        }
        double d = devise != null ? devise.taux : 2027;
        double prct = (7 * montant) / 100;
        if (de) {
            System.out.println("En dollar: "+de);
            return (montant + prct) / d;
        } else {
            System.out.println("En franc: "+de);
            return montant + prct;
        }
    }

}
