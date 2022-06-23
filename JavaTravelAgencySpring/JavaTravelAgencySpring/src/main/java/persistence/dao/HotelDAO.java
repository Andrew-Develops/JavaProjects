package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Hotel;
import persistence.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HotelDAO {

    //inseram un hotel
    public void insertHotel(Hotel hotel) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(hotel);
        session.getTransaction().commit();
        session.close();
    }

    //cautam daca hotelul exista deja in baza de date, pentru a nu introduce inca unul
    public long countHotel(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query countHotelQuery = session.createNamedQuery("countHotel");
        countHotelQuery.setParameter("name", name);
        long result = (Long) countHotelQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //stergem un hotel
    public int deleteHotel(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteHotelQuery = session.createNamedQuery("deleteHotelByName");
        deleteHotelQuery.setParameter("name", name);
        int result = deleteHotelQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cautam un hotel dupa nume
    public List<Hotel> findHotelByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findHotelByNameQuery = session.createNamedQuery("findHotelByName");
        findHotelByNameQuery.setParameter("name", name);
        List<Hotel> hotelsList = findHotelByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return hotelsList;
    }

    //cautam un hotel dupa oras
    public List<Hotel> findHotelByCity(String city) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findHotelByCityQuery = session.createNamedQuery("findHotelsInCity");
        findHotelByCityQuery.setParameter("name", city);
        List<Hotel> hotelsList = findHotelByCityQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return hotelsList;
    }

    //cautam un hotel dupa adresa
    public List<Hotel> findHotelByAddress(String address) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findHotelByAddressQuery = session.createNamedQuery("findHotelByAddress");
        findHotelByAddressQuery.setParameter("address", address);
        List<Hotel> hotelsList = findHotelByAddressQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return hotelsList;
    }

    //schimbam numele unui hotel
    public int changeHotelName(String oldName, String newName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query changeHotelNameQuery = session.createNamedQuery("changeHotelName");
        changeHotelNameQuery.setParameter("newName", newName);
        changeHotelNameQuery.setParameter("name", oldName);
        int result = changeHotelNameQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cautam un hotel dupa numarul de stele
    private double findHotelByNumberOfStars(double numberOfStars) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findHotelByNumberOfStarsQuery = session.createNamedQuery("findHotelByNumberOfStars");
        findHotelByNumberOfStarsQuery.setParameter("numberOfStars", numberOfStars);
        double result = (Double) findHotelByNumberOfStarsQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

}
