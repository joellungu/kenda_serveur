package org.kenda.models.utilisateurs.historiques;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

public interface HistoriqueDao {
    @SqlUpdate("CREATE TABLE course (" +
            "id-course bigint PRIMARY KEY, " +
            "id-client bigint," +
            "heure-depart timestamp," +
            "heure-arrive timestamp," +
            "lieu-depart varchar," +
            "lieu-arrive varchar," +
            "id-chauffeur bigint," +
            "id-receveur bigint," +
            "id-embarqueur bigint," +
            "id-bus bigint," +
            "terminer boolean" +
            ")")
    void createTable();

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (?, ?)")
    void insertPositional(int id, String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertNamed(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertBean(@BindBean Historique course);

    @SqlQuery("SELECT * FROM course ORDER BY \"name\"")
    @RegisterBeanMapper(Historique.class)
    List<Historique> listCourses();
}
