package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Airport;
import persistence.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AirportDAO {

    //inseram un aeroport
    public void insertAirport(Airport airport) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(airport);
        session.getTransaction().commit();
        session.close();
    }

    //cauta un airport dupa numele unui oras
    public List<Airport> findAirportByCityName(String cityName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findAirportByCityNameQuery = session.getNamedQuery("findAirportNameByCityName");
        findAirportByCityNameQuery.setParameter("name", cityName);
        List<Airport> airportList = findAirportByCityNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();

        return airportList;
    }

    //stergem un aeroport dupa nume
    public int deleteAirportByName(String airportName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteAirportQuery = session.getNamedQuery("deleteAirportByName");
        deleteAirportQuery.setParameter("name", airportName);
        int result = deleteAirportQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return result;
    }

    //schimbam numele unui aeroport
    public int changeAirportName(String oldName, String newName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query changeAirportNameQuery = session.getNamedQuery("changeAirportName");
        changeAirportNameQuery.setParameter("newName", newName);
        changeAirportNameQuery.setParameter("name", oldName);
        int result = changeAirportNameQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return result;
    }

    //verificam numarul de aeroporturi sa nu mai adaugam unul cu acelasi nume
    public long countAirportName(String airportName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query countAirportNameQuery = session.getNamedQuery("countAirportName");
        countAirportNameQuery.setParameter("name", airportName);
        long result = (Long) countAirportNameQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return result;
    }

    //cautam un aeroport dupa nume
    public Airport findAirportByName(String airportName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findAirportByNameQuery = session.getNamedQuery("findAirportByName");
        findAirportByNameQuery.setParameter("name", airportName);
        Airport airport = (Airport) findAirportByNameQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return airport;
    }


}
