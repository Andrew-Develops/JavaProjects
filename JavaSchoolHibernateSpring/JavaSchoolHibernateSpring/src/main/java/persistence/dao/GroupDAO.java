package persistence.dao;

import persistence.config.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Group;

import javax.persistence.Query;
import java.util.List;
@Repository
public class GroupDAO {

    //inseram o grupa
    public void insertGroup(Group group) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
        session.close();
    }

    //cautam o grupa dupa nume
    //folosim metoda pentru a insera un Set de Students pe o grupa deja existenta in DB
    public List<Group> findGroupByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findGroupByNameQuery = session.createNamedQuery("findGroupByName");
        findGroupByNameQuery.setParameter("name", name);
        List<Group> groupList = findGroupByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return groupList;
    }

    public List<Group> findAllGroups(){
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findAllGroups = session.createNamedQuery("findAllGroups");
        List<Group> groupList = findAllGroups.getResultList();
        session.getTransaction().commit();
        session.close();
        return groupList;
    }

    //cream o metoda care verifica daca exista mai mult de 1 instanta in DB cu acelasi nume
    //facem asta pentru a opri adaugarea unei grupe noi, daca aceasta deja exista in DB
    public Long groupCountyByName(String name) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query countGroupByNameQuery = session.createNamedQuery("countGroupByName");
        countGroupByNameQuery.setParameter("name", name);
        //cream o variabila de tip long ptc raspunsul metodei .getSingleResult() este Long
        Long countGroupByName = (Long) (countGroupByNameQuery.getSingleResult());
        session.getTransaction().commit();
        session.close();
        return countGroupByName;
    }
}
