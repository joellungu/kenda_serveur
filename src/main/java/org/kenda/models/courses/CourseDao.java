package org.kenda.models.courses;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Date;
import java.util.List;

public interface CourseDao {
    @SqlUpdate("CREATE TABLE course (" +
            "id bigint PRIMARY KEY, " +
            "idclient bigint," +
            "dates date,"+
            "heuredepart timestamp," +
            "heurearrive timestamp," +
            "lieudepart varchar," +
            "lieuarrive varchar," +
            "idchauffeur bigint," +
            "idreceveur bigint," +
            "idembarqueur bigint," +
            "idbus bigint," +
            "status INTEGER," +
            "terminer boolean" +
            ")")
    void createTable();

    //@SqlUpdate("INSERT INTO course (id, idclient, heuredepart,heurearrive,lieudepart,lieuarrive,idchauffeur,idreceveur,idembarqueur,idbus,terminer) " +
    //        "VALUES (?,?,?,?,?,?,?,?,?,?,?)")
    //void insertPositional(int id, String name);

    //@SqlUpdate("UPDATE course SET terminer = ? WHERE id = ?")
    //void insertNamed(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("INSERT INTO course (id, idclient,dates, heuredepart,heurearrive,lieudepart,lieuarrive,idchauffeur,idreceveur,idembarqueur,idbus,status,terminer) " +
            "VALUES (:id, :idclient,:dates, :heuredepart,:heurearrive,:lieudepart,:lieuarrive,:idchauffeur,:idreceveur,:idembarqueur,:idbus,:status,:terminer)")
    void nouvelleCourse(@BindBean Course course);

    @SqlQuery("SELECT * FROM course where dates = ? and status = ? and terminer = ?")
    @RegisterBeanMapper(Course.class)
    List<Course> listCoursesParDate(Date date, int status, Boolean terminer);

    @SqlUpdate("UPDATE course set idclient = :idclient, dates = :dates, heuredepart = :heuredepart, heurearrive = :heurearrive, lieudepart = :lieudepart, lieuarrive = :lieuarrive, idchauffeur = :idchauffeur, idreceveur = :idreceveur, idembarqueur = :idembarqueur, idbus = :idbus, status = :status, terminer = :terminer where id = :id")
    void miseAjour(@BindBean Course course);

    @SqlUpdate("DELETE course where id=?")
    void supprimer(Long id);

}
