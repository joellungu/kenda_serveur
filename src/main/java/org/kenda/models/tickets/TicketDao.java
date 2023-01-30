package org.kenda.models.tickets;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

public interface TicketDao {
    @SqlUpdate("CREATE TABLE ticket (" +
            "id bigint PRIMARY KEY, " +
            "idcourse bigint," +
            "idarretdepart bigint," +
            "idarretarrive bigint," +
            "prix double," +
            "prixtotal double," +
            "device varchar(3)," +
            "codepostal varchar," +
            "phone bigint," +
            "dateheure timestamp," +
            "coderecuperation varchar(10)," +
            "valider boolean," +
            "nombreplace INTEGER," +
            "datedepart timestamp," +
            "datearrive timestamp," +
            "depart varchar," +
            "arrive varchar" +
            ")")
    void createTable();

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (?, ?)")
    void insertPositional(int id, String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertNamed(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("INSERT INTO course (id, \"name\") VALUES (:id, :name)")
    void insertBean(@BindBean Ticket course);

    @SqlQuery("SELECT * FROM course ORDER BY \"name\"")
    @RegisterBeanMapper(Ticket.class)
    List<Ticket> listCourses();
}
