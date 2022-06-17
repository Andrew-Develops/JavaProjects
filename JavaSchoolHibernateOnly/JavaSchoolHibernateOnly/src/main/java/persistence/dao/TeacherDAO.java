package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import persistence.entities.Student;
import persistence.entities.Teacher;

import javax.persistence.Query;
import java.util.List;

public class TeacherDAO {

    //inseram un profesor in DB
    public void insertTeacher(Teacher teacher) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(teacher);
        session.getTransaction().commit();
        session.close();
    }
    //afisam toti profesorii din db
    public List<Teacher> findAllTeachers() {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findAllTeachersQuery = session.createNamedQuery("findAllTeachers");
        List<Teacher> teacherList = findAllTeachersQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return teacherList;
    }
    //cautam profesorii dupa nume
    public List<Teacher> findTeacherByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findTeacherByNameQuery = session.createNamedQuery("findTeacherByName");
        findTeacherByNameQuery.setParameter("firstName", name);
        List<Teacher> teacherList = findTeacherByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return teacherList;
    }
    //stergem un profesor dupa nume
    public void deleteTeacherByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteTeacherByNameQuery = session.createNamedQuery("deleteTeacherByName");
        deleteTeacherByNameQuery.setParameter("firstName", name);
        //folosim .executeUpdate() pentru a updata lista dupa ce stergem profesorul
        deleteTeacherByNameQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    //updatam firstName unui profesor in functie de id
    public void updateTeacherFirstName(String firstName,int id){
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateTeacherByIdQuery = session.createNamedQuery("updateTeacherFirstNameById");
        updateTeacherByIdQuery.setParameter("firstName",firstName);
        updateTeacherByIdQuery.setParameter("id",id);
        int result = updateTeacherByIdQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    //updatam lastName unui profesor in functie de id
    public void updateTeacherLastName(String lastName,int id){
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateTeacherByIdQuery = session.createNamedQuery("updateTeacherLastNameById");
        updateTeacherByIdQuery.setParameter("lastName",lastName);
        updateTeacherByIdQuery.setParameter("id",id);
        int result = updateTeacherByIdQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
