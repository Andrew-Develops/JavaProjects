package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Flight;
import persistence.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class FlightDAO {

    //inseram un zbor
    public void insertFlight(Flight flight) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(flight);
        session.getTransaction().commit();
        session.close();
    }

    //stergem un zbor dupa numarul borului
    public int deleteFlight(String flightNumber) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteFlightQuery = session.createNamedQuery("deleteFlight");
        deleteFlightQuery.setParameter("flightNumber", flightNumber);
        int result = deleteFlightQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //numaram cate zboruri cu acelasi flightNumber exista in baza de date
    public long countFlightNumber(String flightNumber) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query countFlightQuery = session.createNamedQuery("countFlightNumber");
        countFlightQuery.setParameter("flightNumber", flightNumber);
        long result = (Long) countFlightQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //afisam toate zborurile dupa flightNumber
    public List<Flight> findFlight(String flightNumber) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findFlightQuery = session.createNamedQuery("flightByNumber");
        findFlightQuery.setParameter("flightNumber", flightNumber);
        List<Flight> flights = findFlightQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return flights;
    }
}
