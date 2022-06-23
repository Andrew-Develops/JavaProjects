package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.City;
import persistence.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CityDAO {

    //inseram un oras
    public void insertCity(City city) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(city);
        session.getTransaction().commit();
        session.close();
    }

    //cautam un oras dupa nume
    public City findCityByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findCityByNameQuery = session.getNamedQuery("findCityByName");
        findCityByNameQuery.setParameter("name", name);
        City city = (City) findCityByNameQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return city;
    }

    //cautam un oras dintr-un tara
    public List<City> findCityInCountry(String country) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findCityByCountryQuery = session.getNamedQuery("findCitiesInCountry");
        findCityByCountryQuery.setParameter("name", country);
        List<City> cityList = findCityByCountryQuery.getResultList();
        session.getTransaction().commit();
        session.close();

        return cityList;
    }

    //cautam un oras dintr-un continent
    public List<City> findCityInContinent(String continent) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findCityByContinentQuery = session.getNamedQuery("findCitiesInContinent");
        findCityByContinentQuery.setParameter("name", continent);
        List<City> cityList = findCityByContinentQuery.getResultList();
        session.getTransaction().commit();
        session.close();

        return cityList;
    }

    //schimbam numele unui oras dupa nume
    public int changeCityName(String name, String newName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query changeCityNameQuery = session.getNamedQuery("changeCityName");
        changeCityNameQuery.setParameter("newName", newName);
        changeCityNameQuery.setParameter("name", name);
        int result = changeCityNameQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return result;
    }

    //verificam daca mai avem un oras cu acelasi nume
    public long countCity(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query countCityQuery = session.createNamedQuery("countCity");
        countCityQuery.setParameter("name", name);
        Long result = (Long) countCityQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //stergem un oras dupa nume
    public int deleteCity(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteCityQuery = session.createNamedQuery("deleteCity");
        deleteCityQuery.setParameter("name", name);
        int result = deleteCityQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
