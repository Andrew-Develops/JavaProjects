package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Country;
import persistence.util.HibernateUtil;

import javax.persistence.NoResultException;
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
        //cand incercam sa adaugam cascadat informatiile aveam o eroare NoResultException, care nu ne permitea
        //sa adaugam un oras nou pe acelas continent, si se pare ca asta a rezolvat treaba
        Country country = null;
        try {
            country = (Country) findCountryQuery.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
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
