package org.kenda.models.adresses;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface AdresseDao {
    @SqlUpdate("CREATE TABLE adresse (" +
            "id bigint PRIMARY KEY, " +
            "idarret bigint," +
            "idcourse bigint," +
            "heurearrive timestamp," +
            "lieuarrive varchar" +
            ")")
    void createTable();

    @SqlUpdate("INSERT INTO adresse (id, \"name\") VALUES (?, ?)")
    void insertPositional(int id, String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertNamed(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertBean(@BindBean Adresse adresse);

    @SqlQuery("SELECT * FROM course ORDER BY \"name\"")
    @RegisterBeanMapper(Adresse.class)
    List<Adresse> listCourses();
}
