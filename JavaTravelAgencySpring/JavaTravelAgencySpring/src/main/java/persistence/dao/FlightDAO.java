package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Flight;
import persistence.util.HibernateUtil;

import javax.persistence.NoResultException;
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
    //cand incercam sa inseram un flight dam de eroarea .NoResultException, acel try catch ar trebui sa rezolve problema
    public Flight findFlightByFlightNumber(String flightNumber) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findFlightQuery = session.createNamedQuery("flightByNumber");
        findFlightQuery.setParameter("flightNumber", flightNumber);
        Flight flight = null;
        try {
            flight = (Flight) findFlightQuery.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return flight;
    }

    public void updateSeatAvailable(int numberOfPeople, String flightNumber) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateSeatsAvailableQuery = session.createNamedQuery("updateSeatsAvailable");
        updateSeatsAvailableQuery.setParameter("numberOfPersons", numberOfPeople);
        updateSeatsAvailableQuery.setParameter("flightNumber", flightNumber);
        updateSeatsAvailableQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
