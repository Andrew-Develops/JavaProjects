package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Continent;
import persistence.util.HibernateUtil;

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
    public Continent findContinentByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findContinentQuery = session.createNamedQuery("findContinentByName");
        findContinentQuery.setParameter("name", name);
        Continent continent = (Continent) findContinentQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return continent;
    }
}
