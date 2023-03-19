package org.kenda.models.itinerance;

/*
    Cette classe nous aide pour connaitre le prix
    selon les trajets pour éviter que le admin ce repète
 */
import org.kenda.models.arrets.Arret;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class Itinerances {
    List<String> l = new LinkedList<>();

    List<HashMap<String,Object>> listeArrets = new LinkedList<>();
    /*
    Kinshasa,
Kasangulu,
Mvululu,
Kisangolo,
Lwila,
Kibweya,
Sona-Bata,
Mbanza-Mboma,
Madimba,
Kikulukuta,
Kisantu,
Mbanza-Ngungu,
Nzenze,
Mboma,
Kukala,
Kimpese,
Kifua,
Kisonga,
Nkamuna,
Loanda,
Kenge,
Kitadila,
Matadi,
Noqui,
Boma,
Moanda,
     */
    public List<HashMap<String,Object>> getListeDesArrets1(String arr) {
        List<String> liste = new LinkedList<>();
        List<String> ll = new LinkedList<>();
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
        int o = 0;
        for(String s : liste){
            if(!s.equals(arr)){
                ll.add(s);
            }else{
                ll.add(s);
                break;
            }
        }
        l.clear();
        listeArrets.clear();
        l = ll;
        try {
            for (String s : ll) {
                ajouter(s,getNewList(l,s));
                //
                //System.out.println("LA langueur de ll: " + ll.size() + " arret: " + s + " == " + ll.contains(s));
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        //listeArrets.add(getArret(Arret.find("nom",ll.get(ll.size() - 1)).firstResult(),
        //        Arret.find("nom",liste.get(o)).firstResult()));
        /*
        Arret kinshasa = (Arret) Arret.find("nom","Kinshasa").firstResult();
        Arret Kasangulu = (Arret) Arret.find("nom","Kasangulu").firstResult();
        Arret Mvululu = (Arret) Arret.find("nom","Mvululu").firstResult();
        Arret Kisangolo = (Arret) Arret.find("nom","Kisangolo").firstResult();
        Arret Lwila = (Arret) Arret.find("nom","Lwila").firstResult();
        Arret Kibweya = (Arret) Arret.find("nom","Kibweya").firstResult();
        Arret Sona_Bata = (Arret) Arret.find("nom","Sona-Bata").firstResult();
        Arret Mbanza_Mboma = (Arret) Arret.find("nom","Mbanza-Mboma").firstResult();
        Arret Madimba = (Arret) Arret.find("nom","Madimba").firstResult();
        Arret Kikulukuta = (Arret) Arret.find("nom","Kikulukuta").firstResult();
        Arret Kisantu = (Arret) Arret.find("nom","Kisantu").firstResult();
        Arret Mbanza_Ngungu = (Arret) Arret.find("nom","Mbanza-Ngungu").firstResult();
        Arret Nzenze = (Arret) Arret.find("nom","Nzenze").firstResult();
        Arret Mboma = (Arret) Arret.find("nom","Mboma").firstResult();
        Arret Kukala = (Arret) Arret.find("nom","Kukala").firstResult();
        Arret Kimpese = (Arret) Arret.find("nom","Kimpese").firstResult();
        Arret Kifua = (Arret) Arret.find("nom","Kifua").firstResult();
        Arret Kisonga = (Arret) Arret.find("nom","Kisonga").firstResult();
        Arret Nkamuna = (Arret) Arret.find("nom","Nkamuna").firstResult();
        Arret Loanda = (Arret) Arret.find("nom","Loanda").firstResult();
        Arret Kenge = (Arret) Arret.find("nom","Kenge").firstResult();
        Arret Kitadila = (Arret) Arret.find("nom","Kitadila").firstResult();
        Arret Matadi = (Arret) Arret.find("nom","Matadi").firstResult();
        Arret Noqui = (Arret) Arret.find("nom","Noqui").firstResult();
        Arret Boma = (Arret) Arret.find("nom","Boma").firstResult();
        Arret Moanda = (Arret) Arret.find("nom","Moanda").firstResult();
                //
        listeArrets.add(getArret(kinshasa,Kasangulu));
        //_______________________________
        listeArrets.add(getArret(Kasangulu,Mvululu));
        //_______________________________
        listeArrets.add(getArret(Mvululu,Kisangolo));
        //_______________________________
        listeArrets.add(getArret(Kisangolo,Lwila));
        //_______________________________
        listeArrets.add(getArret(Lwila,Kibweya));
        //_______________________________
        listeArrets.add(getArret(Kibweya,Sona_Bata));
        //_______________________________
        listeArrets.add(getArret(Sona_Bata,Mbanza_Mboma));
        //_______________________________
        listeArrets.add(getArret(Mbanza_Mboma,Madimba));
        //_______________________________
        listeArrets.add(getArret(Madimba,Kikulukuta));
        //_______________________________
        listeArrets.add(getArret(Kikulukuta,Kisantu));
        //_______________________________
        listeArrets.add(getArret(Kisantu,Mbanza_Ngungu));
        //_______________________________
        listeArrets.add(getArret(Mbanza_Ngungu,Nzenze));
        //_______________________________
        listeArrets.add(getArret(Nzenze,Mboma));
        //_______________________________
        listeArrets.add(getArret(Mboma,Kukala));
        //_______________________________
        listeArrets.add(getArret(Kukala,Kimpese));
        //_______________________________
        listeArrets.add(getArret(Kimpese,Kifua));
        //_______________________________
        listeArrets.add(getArret(Kifua,Kisonga));
        //_______________________________
        listeArrets.add(getArret(Kisonga,Nkamuna));
        //_______________________________
        listeArrets.add(getArret(Nkamuna,Loanda));
        //_______________________________
        listeArrets.add(getArret(Loanda,Kenge));
        //_______________________________
        listeArrets.add(getArret(Kenge,Kitadila));
        //_______________________________
        listeArrets.add(getArret(Kitadila,Matadi));
        //_______________________________
        listeArrets.add(getArret(Matadi,Noqui));
        //_______________________________
        listeArrets.add(getArret(Noqui,Boma));
        //_______________________________
        listeArrets.add(getArret(Boma,Moanda));
        //_______________________________
        listeArrets.add(getArret(kinshasa,Moanda));
        //_______________________________
        */
        return listeArrets;
    }
    private HashMap<String,Object> getArret(Arret a1, Arret a2) {
        HashMap<String,Object> a12 = new HashMap<>();
        a12.put("arretDepart",a1);
        a12.put("arretArrive",a2);
        a12.put("prix",0);
        a12.put("active",false);
        return a12;
    }

    public void ajouter(String depart, List<String> l){
        //
        for(int i = 0; i < l.size(); i++){
            //
            Arret d = (Arret) Arret.find("nom",depart).firstResult();
            Arret a = (Arret) Arret.find("nom",l.get(i)).firstResult();
            //
            listeArrets.add(getArret(d,a));
        }
    }
    //
    public List<HashMap<String,Object>> listeKinshasaGoma(){
        return new LinkedList<>();
    }

    private List getNewList(List<String> ld, String x){
        List<String> lis = new LinkedList<>();
        for(int t = 0; t < ld.size(); t++){
            if(ld.get(t) != x){
                lis.add(ld.get(t));
            }
        }
        l = lis;
        return lis;
    }

    public void addArretKnKc(){
        List<Arret> liste = new LinkedList<>();
        liste.add(new Arret("Kinshasa","Kinshasa",14,14,1));
        liste.add(new Arret("Kasangulu","Kongo-Central",14,14,1));
        liste.add(new Arret("Mvululu","Kongo-Central",14,14,1));
        liste.add(new Arret("Kisangolo","Kongo-Central",14,14,1));
        liste.add(new Arret("Lwila","Kongo-Central",14,14,1));
        liste.add(new Arret("Kibweya","Kongo-Central",14,14,1));
        liste.add(new Arret("Sona-Bata","Kongo-Central",14,14,1));
        liste.add(new Arret("Mbanza-Mboma","Kongo-Central",14,14,1));
        liste.add(new Arret("Madimba","Kongo-Central",14,14,1));
        liste.add(new Arret("Kikulukuta","Kongo-Central",14,14,1));
        liste.add(new Arret("Kisantu","Kongo-Central",14,14,1));
        liste.add(new Arret("Mbanza-Ngungu","Kongo-Central",14,14,1));
        liste.add(new Arret("Nzenze","Kongo-Central",14,14,1));
        liste.add(new Arret("Mboma","Kongo-Central",14,14,1));
        liste.add(new Arret("Kukala","Kongo-Central",14,14,1));
        liste.add(new Arret("Kimpese","Kongo-Central",14,14,1));
        liste.add(new Arret("Kifua","Kongo-Central",14,14,1));
        liste.add(new Arret("Kisonga","Kongo-Central",14,14,1));
        liste.add(new Arret("Nkamuna","Kongo-Central",14,14,1));
        liste.add(new Arret("Loanda","Kongo-Central",14,14,1));
        liste.add(new Arret("Kenge","Kongo-Central",14,14,1));
        liste.add(new Arret("Kitadila","Kongo-Central",14,14,1));
        liste.add(new Arret("Matadi","Kongo-Central",14,14,1));
        liste.add(new Arret("Noqui","Kongo-Central",14,14,1));
        liste.add(new Arret("Boma","Kongo-Central",14,14,1));
        liste.add(new Arret("Moanda","Kongo-Central",14,14,1));
        //
        liste.forEach((a)->{
            a.persist();
        });
    }
}
