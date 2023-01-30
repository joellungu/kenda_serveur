package org.kenda.metiers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.kenda.models.Connexion;
import org.kenda.models.courses.Course;
import org.kenda.models.courses.CourseDao;
import org.kenda.models.partenaires.Partenaire;
import redis.clients.jedis.Jedis;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Date;
import java.util.*;

@ApplicationScoped
public class CourseMetier {

    Jdbi jdbi = Connexion.jdbi;

    public String nouvelleCourse(Course course) {
        jdbi.installPlugin(new SqlObjectPlugin());
        try(Handle handle = jdbi.open()){
            CourseDao courseDao = handle.attach(CourseDao.class);
            //
            try{
                courseDao.nouvelleCourse(course);
                return "Enregistrement effectué";
            }catch (Exception ex){
                System.out.println("Erreur du à: "+ex);
                return "Enregistrement non effectué";
            }//
        }
    }

    public String upDatePartenaire(Course course) {
        jdbi.installPlugin(new SqlObjectPlugin());
        try(Handle handle = jdbi.open()){
            CourseDao courseDao = handle.attach(CourseDao.class);
            //
            try{
                courseDao.miseAjour(course);
                return "Mise à jour effectué";
            }catch (Exception ex){
                System.out.println("Erreur du à: "+ex);
                return "Mise à jour non effectué";
            }
        }
    }

    public List<Course> getAllCourses(Date date, int status, Boolean terminer) {
        jdbi.installPlugin(new SqlObjectPlugin());
        try(Handle handle = jdbi.open()){
            CourseDao courseDao = handle.attach(CourseDao.class);
            //
            try{
                return courseDao.listCoursesParDate(date,status,terminer);

            }catch (Exception ex){
                System.out.println("Erreur du à: "+ex);
                return new LinkedList<>();
            }//
        }
    }

    /*
    public Course getCourse(String id) {
        String contenu = jedis.get(id);
        Course course = obj.readValue(contenu, Course.class);
        return course;
    }
    */
}
