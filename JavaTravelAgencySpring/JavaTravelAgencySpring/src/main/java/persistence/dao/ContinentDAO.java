package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Continent;
import persistence.util.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class ContinentDAO {

    //inseram un continent
    public void insertContinent(Continent continent) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(continent);
        session.getTransaction().commit();
        session.close();
    }

    //stergem un continent(in cazul in care nu mai avem oferte in acea zona)
    public int deleteContinent(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteContinentQuery = session.createNamedQuery("deleteContinent");
        deleteContinentQuery.setParameter("name", name);
        int result = deleteContinentQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //verificam sa nu adaugam acelasi continent de 2 ori
    public long countContinent(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query countContinentQuery = session.createNamedQuery("countContinent");
        countContinentQuery.setParameter("name", name);
        Long result = (Long) countContinentQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cautam un continent dupa nume
    //cand incercam sa inseram un continent dam de eroarea .NoResultException, acel try catch ar trebui sa rezolve problema
    public Continent findContinentByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findContinentQuery = session.createNamedQuery("findContinentByName");
        findContinentQuery.setParameter("name", name);
        Continent continent = null;
        try {
            continent = (Continent) findContinentQuery.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return continent;
    }
}
