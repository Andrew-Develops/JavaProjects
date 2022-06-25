package persistence.dao;

import persistence.config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Teacher;

import javax.persistence.Query;
import java.util.List;

@Repository
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
    public int deleteTeacherByName(String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteTeacherByNameQuery = session.createNamedQuery("deleteTeacherByName");
        deleteTeacherByNameQuery.setParameter("firstName", firstName);
        deleteTeacherByNameQuery.setParameter("lastName", lastName);
        //folosim .executeUpdate() pentru a updata lista dupa ce stergem profesorul
        int result = deleteTeacherByNameQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //updatam firstName unui profesor in functie de id
    public int updateTeacherFirstName(String firstName, int id) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateTeacherByIdQuery = session.createNamedQuery("updateTeacherFirstNameById");
        updateTeacherByIdQuery.setParameter("firstName", firstName);
        updateTeacherByIdQuery.setParameter("id", id);
        int result = updateTeacherByIdQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //updatam lastName unui profesor in functie de id
    public int updateTeacherLastName(String lastName, int id) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateTeacherByIdQuery = session.createNamedQuery("updateTeacherLastNameById");
        updateTeacherByIdQuery.setParameter("lastName", lastName);
        updateTeacherByIdQuery.setParameter("id", id);
        int result = updateTeacherByIdQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cream o metoda care verifica daca exista mai mult de 1 instanta in DB cu acelasi nume si prenume
    //facem asta pentru a opri adaugarea unui teacher nou, daca aceasta deja exista in DB
    public Long teacherCountByName(String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query countTeacherByNameQuery = session.createNamedQuery("countTeacherByName");
        countTeacherByNameQuery.setParameter("firstName", firstName);
        countTeacherByNameQuery.setParameter("lastName", lastName);
        Long countTeachers = (Long) (countTeacherByNameQuery.getSingleResult());
        session.getTransaction().commit();
        session.close();
        return countTeachers;
    }
}
