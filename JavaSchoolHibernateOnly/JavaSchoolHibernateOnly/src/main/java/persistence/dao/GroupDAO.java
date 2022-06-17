package persistence.dao;

import config.HibernateUtil;
import org.hibernate.Session;
import persistence.entities.Group;

import javax.persistence.Query;
import java.util.List;

public class GroupDAO {

    //inseram o grupa
    public void insertGroup(Group group){
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
        session.close();
    }
    //cautam o grupa dupa nume
    //folosim metoda pentru a insera un Set de Students pe o grupa deja existenta in DB
    public List<Group> findGroupByName(String name){
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findGroupByNameQuery = session.getNamedQuery("findGroupByName");
        findGroupByNameQuery.setParameter("name",name);
        List<Group> groupList = findGroupByNameQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return groupList;
    }
}
