package org.kenda.models.bus;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

public interface BusDao {
    @SqlUpdate("CREATE TABLE bus (" +
            "id bigint PRIMARY KEY, " +
            "idclient bigint," +
            "nom varchar," +
            "marque varchar," +
            "type varchar," +
            "numerochassis varchar," +
            "dateachat timestamp," +
            "datemiseenservice timestamp,"+
            "capacite INTEGER," +
            "caracteristiques TEXT," +
            "kilometrage INTEGER" +
            ")")
    void createTable();

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (?, ?)")
    void insertPositional(int id, String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertNamed(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertBean(@BindBean Bus course);

    @SqlQuery("SELECT * FROM course ORDER BY \"name\"")
    @RegisterBeanMapper(Bus.class)
    List<Bus> listCourses();
}
