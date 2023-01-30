package org.kenda.models;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.kenda.models.adresses.AdresseDao;
import org.kenda.models.agents.AgentDao;
import org.kenda.models.arrets.ArretDao;
import org.kenda.models.bus.BusDao;
import org.kenda.models.courses.CourseDao;
import org.kenda.models.entretiens.EntretienDao;
import org.kenda.models.partenaires.PartenaireDao;
import org.kenda.models.tickets.TicketDao;
import org.kenda.models.trajets.TrajetDao;
import org.kenda.models.utilisateurs.UtilisateurDao;
import redis.clients.jedis.Jedis;

public class Connexion {

    public static Jdbi jdbi = Jdbi.create
            ("jdbc:postgresql://localhost:5432/kenda", "postgres", "joellungu");
    public static Jedis jedis = new Jedis();
    //

    public static Jdbi seConnecter() {
        jdbi.installPlugin(new SqlObjectPlugin());
        try(Handle handle = jdbi.open()){
            CourseDao courseDao = handle.attach(CourseDao.class);
            AdresseDao adresseDao = handle.attach(AdresseDao.class);
            AgentDao agentDao = handle.attach(AgentDao.class);
            ArretDao arretDao = handle.attach(ArretDao.class);
            BusDao busDao = handle.attach(BusDao.class);
            EntretienDao entretienDao = handle.attach(EntretienDao.class);
            PartenaireDao partenaireDao = handle.attach(PartenaireDao.class);
            TicketDao ticketDao = handle.attach(TicketDao.class);
            TrajetDao trajetDao = handle.attach(TrajetDao.class);
            UtilisateurDao utilisateurDao = handle.attach(UtilisateurDao.class);

            //
            try{

                //jedis.set
                //jedis.set("key", "value");
                String value = jedis.get("key");
                System.out.println(value);
                /*
                courseDao.createTable();
                adresseDao.createTable();
                agentDao.createTable();
                arretDao.createTable();
                busDao.createTable();
                entretienDao.createTable();
                partenaireDao.createTable();
                ticketDao.createTable();
                trajetDao.createTable();
                utilisateurDao.createTable();
                 */

            }catch (Exception ex){
                System.out.print("Erreur du à: "+ex);
            }
            //
        }
        return jdbi;
        /*
         */
    }
}
