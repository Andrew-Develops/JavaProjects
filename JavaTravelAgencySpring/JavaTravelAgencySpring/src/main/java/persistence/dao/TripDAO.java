package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Trip;
import persistence.util.HibernateUtil;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class TripDAO {

    //inseram o calatorie
    public void insertTrip(Trip trip) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(trip);
        session.getTransaction().commit();
        session.close();
    }

    //stergem o calatorie
    public int deleteTripByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteTripByNameQuery = session.createNamedQuery("deleteTripByName");
        deleteTripByNameQuery.setParameter("name", name);
        int result = deleteTripByNameQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cautam calatoriile cu promotii
    public List<Trip> findPromotedTrips(boolean promoted) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findTripByPromotedQuery = session.createNamedQuery("findPromotedTrips");
        findTripByPromotedQuery.setParameter("promoted", promoted);
        List<Trip> result = findTripByPromotedQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cautam cate calatorii cu acelasi nume si plecari in acelasi timp
    public long countTripByNameAndDepartureDate(String name, Date departureDate) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query countTripByNameAndDepartureDateQuery = session.createNamedQuery("countTripByNameAndDepartureDate");
        countTripByNameAndDepartureDateQuery.setParameter("name", name);
        countTripByNameAndDepartureDateQuery.setParameter("departureDate", departureDate);
        long result = (long) countTripByNameAndDepartureDateQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cautam toate calatoriile
    public List<Trip> findAllTrips() {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findAllTripsQuery = session.createNamedQuery("findAllTrips");
        List<Trip> result = findAllTripsQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cautam calatoriile in functie de mealType
    public List<Trip> findByMealType(String mealType) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findByMealTypeQuery = session.createNamedQuery("findTripByMealType");
        findByMealTypeQuery.setParameter("mealType", mealType);
        List<Trip> result = findByMealTypeQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cautam calatoriile in functie de data la care pleaca
    public List<Trip> findTripByDepartureDate(Date departureDate) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findTripByDepartureDateQuery = session.createNamedQuery("findTripByDepartureDate");
        findTripByDepartureDateQuery.setParameter("departureDate", departureDate);
        List<Trip> result = findTripByDepartureDateQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Trip> findTripByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findTripByNameQuery = session.createNamedQuery("findTripByName");
        findTripByNameQuery.setParameter("name", name);
        List<Trip> result = findTripByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
