package org.kenda;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ServerEndpoint("/paiement/{parametres}")
@ApplicationScoped
public class paiement {

    Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("parametres") String parametres) {
        System.out.println("onOpen> " + parametres);
        String[] params = parametres.split(",");
        String cles = ""+params[0]+""+params[1]+""+params[2]+""+params[3];
        //Je lance le paiement...
        System.out.println("jojo13"+"-"+params[3]+"-"+Double.parseDouble(params[4])+"-"+params[5]);;
        verif("jojo13",""+params[3],Double.parseDouble(params[4]),""+params[5]);
        sessions.put(cles, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("parametres") String parametres) {
        System.out.println("onClose> " + parametres);
        String[] params = parametres.split(",");
        String cles = ""+params[0]+""+params[1]+""+params[2]+""+params[3];
        sessions.remove(cles);
        //broadcast("User " + name + " left");
    }

    @OnError
    public void onError(Session session, @PathParam("parametres") String parametres, Throwable throwable) {
        System.out.println("onError> " + parametres + ": " + throwable);
        String[] params = parametres.split(",");
        String cles = ""+params[0]+""+params[1]+""+params[2]+""+params[3];
        sessions.remove(cles);
        //broadcast("User " + username + " left on error: " + throwable);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("parametres") String parametres) {
        //
        System.out.println("onMessage> " + parametres + " : "+message);
        //
        String[] params = parametres.split(",");
        String cles = ""+params[0]+""+params[1]+""+params[2]+""+params[3];
        //
        sessions.entrySet().forEach((s -> {
            //
            String ss = (String)s.getKey();
            if(ss.equals(cles)){
                ObjectMapper obj = new ObjectMapper();

                try {
                    //
                    s.getValue().getAsyncRemote().sendText("Message truc: "+message);
                    /*
                    s.getValue().getAsyncRemote().sendObject("Salut bro", result ->  {
                        if (result.getException() != null) {
                            System.out.println("Unable to send message: " + result.getException());
                        }
                    });
                    */
                } catch (Exception e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            }else{
                System.out.println("Le message: "+s.getKey()+"="+cles);
            }
        }));
    }

    public String verif(String reference, String phone, double prix, String devise){
        String urlPost = "http://41.243.7.46:3006/api/rest/v1/paymentService";
        String body = "{\n" +
                "  \"merchant\":\"ECOSYS\"," +
                "  \"type\":1," +
                "  \"reference\": \"" + reference +"\","+
                "  \"phone\": \"243" + phone +"\","+
                "  \"amount\": \"" + prix +"\","+
                "  \"currency\": \"" + devise +"\","+
                "  \"callbackUrl\":\"http://dgc-epst.uc.r.appspot.com\"" +
                "}";
        var requete = HttpRequest.newBuilder()
                .uri(URI.create(urlPost))
                .header("Content-Type","application/json")
                .header("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJcL2xvZ2luIiwicm9sZXMiOlsiTUVSQ0hBTlQiXSwiZXhwIjoxNzMzOTEwMTY5LCJzdWIiOiJlNjFiZTYyNTA2M2NlNGQzOTc3ZTY2ZTI1ODdiZjIwOSJ9.KXwGzLyTGJT4iLnA6rtqPKRLE195j5oFWLbmpOlh2uo")
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
    }
}
