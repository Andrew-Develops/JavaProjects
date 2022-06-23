package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Country;
import persistence.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CountryDAO {

    //inseram o tara
    public void insertCountry(Country country) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(country);
        session.getTransaction().commit();
        session.close();
    }

    //verificam cate tari cu acelasi nume avem in baza de date pentru a nu mai adauga inca una la fel
    public long countCountry(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query countCountryQuery = session.createNamedQuery("countCountry");
        countCountryQuery.setParameter("name", name);
        long result = (Long) countCountryQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //stergem o tara dupa nume
    public int deleteCountry(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteCountryQuery = session.createNamedQuery("deleteCountry");
        deleteCountryQuery.setParameter("name", name);
        int result = deleteCountryQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //cautam o tara dupa nume
    public Country findCountryByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findCountryQuery = session.createNamedQuery("findCountry");
        findCountryQuery.setParameter("name", name);
        Country country = (Country) findCountryQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return country;
    }

    //cautam toate tarile din acel continent
    public List<Country> findCountriesInContinent(String continent) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findCountriesInContinentQuery = session.createNamedQuery("findCountriesInContinent");
        findCountriesInContinentQuery.setParameter("name", continent);
        List<Country> countryList = findCountriesInContinentQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return countryList;
    }

    //schimbam numele unei tari
    public int changeCountryName(String oldName, String newName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query changeCountryNameQuery = session.createNamedQuery("changeCountryName");
        changeCountryNameQuery.setParameter("newName", newName);
        changeCountryNameQuery.setParameter("name", oldName);
        int result = changeCountryNameQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
