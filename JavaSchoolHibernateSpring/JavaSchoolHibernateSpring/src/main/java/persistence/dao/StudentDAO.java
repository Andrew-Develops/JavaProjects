package persistence.dao;

import persistence.config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Student;

import javax.persistence.Query;
import java.util.List;

@Repository
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
    public int deleteStudentByName(String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteStudentByNameQuery = session.createNamedQuery("deleteStudentByName");
        deleteStudentByNameQuery.setParameter("firstName", firstName);
        deleteStudentByNameQuery.setParameter("lastName", lastName);
        //folosim .executeUpdate() pentru a updata lista dupa ce stergem studentul
        int result = deleteStudentByNameQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //updatam firstName unui student in functie de id
    public int updateStudentFirstName(String firstName, int id) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateStudentByIdQuery = session.createNamedQuery("updateStudentFirstNameById");
        updateStudentByIdQuery.setParameter("firstName", firstName);
        updateStudentByIdQuery.setParameter("id", id);
        int result = updateStudentByIdQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //updatam lastName unui student in functie de id
    public int updateStudentLastName(String lastName, int id) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateStudentByIdQuery = session.createNamedQuery("updateStudentLastNameById");
        updateStudentByIdQuery.setParameter("lastName", lastName);
        updateStudentByIdQuery.setParameter("id", id);
        int result = updateStudentByIdQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cream o metoda care verifica daca exista mai mult de 1 instanta in DB cu acelasi:  nume, prenume si anul nasterii
    //facem asta pentru a opri adaugarea unui student nou, daca aceasta deja exista in DB
    public Long studentCountByNameAndYearOfBirth(String firstName, String lastName, int yearOfBirth) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query countStudentQuery = session.createNamedQuery("countStudentByNameAndYearOfBirth");
        countStudentQuery.setParameter("firstName", firstName);
        countStudentQuery.setParameter("lastName", lastName);
        countStudentQuery.setParameter("yearOfBirth", yearOfBirth);
        Long countStudent = (Long) (countStudentQuery.getSingleResult());
        session.getTransaction().commit();
        session.close();
        return countStudent;

    }
}
