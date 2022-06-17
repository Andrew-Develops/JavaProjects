package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import persistence.entities.Student;

import javax.persistence.Query;
import java.util.List;

public class StudentDAO {

    //inseram un student in DB
    public void insertStudent(Student student) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }

    //afisam toti studentii din db
    public List<Student> findAllStudents() {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findAllStudentsQuery = session.createNamedQuery("findALlStudents");
        List<Student> studentList = findAllStudentsQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return studentList;
    }
    //cautam studentii dupa nume
    public List<Student> findStudentByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findStudentByNameQuery = session.createNamedQuery("findStudentByName");
        findStudentByNameQuery.setParameter("firstName", name);
        List<Student> studentList = findStudentByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return studentList;
    }

    //stergem un student dupa nume
    public void deleteStudentByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteStudentByNameQuery = session.createNamedQuery("deleteStudentByName");
        deleteStudentByNameQuery.setParameter("firstName", name);
        //folosim .executeUpdate() pentru a updata lista dupa ce stergem studentul
        deleteStudentByNameQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    //updatam firstName unui student in functie de id
    public void updateStudentFirstName(String firstName,int id){
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateStudentByIdQuery = session.createNamedQuery("updateStudentFirstNameById");
        updateStudentByIdQuery.setParameter("firstName",firstName);
        updateStudentByIdQuery.setParameter("id",id);
        int result = updateStudentByIdQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    //updatam lastName unui student in functie de id
    public void updateStudentLastName(String lastName,int id){
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateStudentByIdQuery = session.createNamedQuery("updateStudentLastNameById");
        updateStudentByIdQuery.setParameter("lastName",lastName);
        updateStudentByIdQuery.setParameter("id",id);
        int result = updateStudentByIdQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
