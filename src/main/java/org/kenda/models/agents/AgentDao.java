package org.kenda.models.agents;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

public interface AgentDao {
    @SqlUpdate("CREATE TABLE agent (" +
            "id bigint PRIMARY KEY, " +
            "idclient bigint," +
            "nom varchar," +
            "postnom varchar," +
            "prenom varchar," +
            "numero varchar," +
            "adresse varchar," +
            "email varchar," +
            "password varchar," +
            "role INTEGER," +
            "roletitre varchar" +
            ")")
    void createTable();

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (?, ?)")
    void insertPositional(int id, String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertNamed(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertBean(@BindBean Agent course);

    @SqlQuery("SELECT * FROM course ORDER BY \"name\"")
    @RegisterBeanMapper(Agent.class)
    List<Agent> listCourses();
}
