package org.kenda.models.entretiens;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

public interface EntretienDao {
    @SqlUpdate("CREATE TABLE entretien (" +
            "id bigint PRIMARY KEY, " +
            "idbus bigint," +
            "date timestamp," +
            "type varchar," +
            "companie varchar," +
            "description TEXT," +
            "status INTEGER" +
            ")")
    void createTable();

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (?, ?)")
    void insertPositional(int id, String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertNamed(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertBean(@BindBean Entretien course);

    @SqlQuery("SELECT * FROM course ORDER BY \"name\"")
    @RegisterBeanMapper(Entretien.class)
    List<Entretien> listCourses();
}
