package org.kenda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.QueryParam;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ServerEndpoint("/recherchelieu/{name}")
@ApplicationScoped
public class StartWebSocket {

    Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("name") String name) {
        System.out.println("onOpen> " + name);
        sessions.put(name, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("name") String name) {
        System.out.println("onClose> " + name);
        sessions.remove(name);
        //broadcast("User " + name + " left");
    }

    @OnError
    public void onError(Session session, @PathParam("name") String name, Throwable throwable) {
        System.out.println("onError> " + name + ": " + throwable);
        sessions.remove(name);
        //broadcast("User " + username + " left on error: " + throwable);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("name") String name) {
        //
        System.out.println("onMessage> " + name + " : "+message);
        //();
        sessions.entrySet().forEach((s -> {
            //
            List<String> liste = new LinkedList();
            //List<String> ll = new LinkedList<>();
            liste.add("Kinshasa");
            liste.add("Kasangulu");
            liste.add("Mvululu");
            liste.add("Kisangolo");
            liste.add("Lwila");
            liste.add("Kibweya");
            liste.add("Sona-Bata");
            liste.add("Mbanza-Mboma");
            liste.add("Madimba");
            liste.add("Kikulukuta");
            liste.add("Kisantu");
            liste.add("Mbanza-Ngungu");
            liste.add("Nzenze");
            liste.add("Mboma");
            liste.add("Kukala");
            liste.add("Kimpese");
            liste.add("Kifua");
            liste.add("Kisonga");
            liste.add("Nkamuna");
            liste.add("Loanda");
            liste.add("Kenge");
            liste.add("Kitadila");
            liste.add("Matadi");
            liste.add("Noqui");
            liste.add("Boma");
            liste.add("Moanda");
            //
            String ss = (String)s.getKey();
            if(ss.equals(name)){
                ObjectMapper obj = new ObjectMapper();
                //
                Predicate<String> p = r -> r.toLowerCase().contains(message.toLowerCase());
                List l = liste.stream().filter(p).collect(Collectors.toList());
                //
                System.out.println("Le message: "+s.getKey()+"="+name);
                //;
                try {
                    //
                    s.getValue().getAsyncRemote().sendText(obj.writeValueAsString(l));
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
                System.out.println("Le message: "+s.getKey()+"="+name);
            }
        }));
    }
}
