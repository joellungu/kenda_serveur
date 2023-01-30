package org.kenda.models.arrets;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

public interface ArretDao {
    @SqlUpdate("CREATE TABLE arret (" +
            "id bigint PRIMARY KEY, " +
            "idarret bigint," +
            "idcourse bigint," +
            "heurearrive timestamp," +
            "lieuarrive varchar" +
            ")")
    void createTable();

    @SqlUpdate("INSERT INTO arret (id, name) VALUES (?, ?)")
    void insertPositional(int id, String name);

    @SqlUpdate("INSERT INTO arret (id, \"name\") VALUES (:id, :name)")
    void insertNamed(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("INSERT INTO arret (id, \"name\") VALUES (:id, :name)")
    void insertBean(@BindBean Arret course);

    @SqlQuery("SELECT * FROM arret ORDER BY \"name\"")
    @RegisterBeanMapper(Arret.class)
    List<Arret> listCourses();
}
