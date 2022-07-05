package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.PurchasedTrip;
import persistence.util.HibernateUtil;

import javax.persistence.Query;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Repository
public class PurchasedTripDAO {


    public void insertPurchasedTrip(PurchasedTrip purchasedTrip) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(purchasedTrip);
        session.getTransaction().commit();
        session.close();
    }

    public List<PurchasedTrip> findPurchasedTripByCustomer(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findPurchasedTripQuery = session.createNamedQuery("findPurchasedTripsByCustomer");
        findPurchasedTripQuery.setParameter("name", name);
        List<PurchasedTrip> purchasedTripList = findPurchasedTripQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return purchasedTripList;
    }

    //afisam excursiile utilizatorului de la data achizitiei pana in prezent
    public List<PurchasedTrip> findPurchasedTripByDate(Date date) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query showRecentlyPurchasedTripsQuery = session.createNamedQuery("findPurchasedTripsByDate");
        showRecentlyPurchasedTripsQuery.setParameter("dateOfPurchase", date);
        Calendar calendar = Calendar.getInstance();
        java.util.Date dateUtil = calendar.getTime();
        java.sql.Date currentDate = new Date(dateUtil.getTime());
        showRecentlyPurchasedTripsQuery.setParameter("currentDate", currentDate);
        List<PurchasedTrip> purchasedTripList = showRecentlyPurchasedTripsQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return purchasedTripList;
    }
}
