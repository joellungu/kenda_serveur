package org.kenda.models.partenaires;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

public interface PartenaireDao {
    @SqlUpdate("CREATE TABLE partenaire (" +
            "idclient bigint PRIMARY KEY, " +
            "nom varchar," +
            "adresse varchar," +
            "numero1 varchar," +
            "numero2 varchar," +
            "email varchar," +
            "password varchar" +
            ")")
    void createTable();

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (?, ?)")
    void insertPositional(int id, String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertNamed(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertBean(@BindBean Partenaire course);

    @SqlQuery("SELECT * FROM course ORDER BY \"name\"")
    @RegisterBeanMapper(Partenaire.class)
    List<Partenaire> listCourses();
}
