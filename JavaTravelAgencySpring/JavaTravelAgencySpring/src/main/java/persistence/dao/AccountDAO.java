package persistence.dao;

import org.hibernate.Session;
import persistence.entities.Account;
import persistence.util.HibernateUtil;

import javax.persistence.Query;

public class AccountDAO {

    //updatam statusul unui user cand il logam
    public int updateUserLogIn(Boolean loggedIn, String userName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateUserLogInQuery = session.createNamedQuery("updateUserLogIn");
        updateUserLogInQuery.setParameter("loggedIn", loggedIn);
        updateUserLogInQuery.setParameter("userName", userName);
        int result = updateUserLogInQuery.executeUpdate();
        session.beginTransaction().commit();
        session.close();
        return result;
    }

    //cautam contul in functie de userName si password
    public Account findAccount(String userName, String password) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findAccountQuery = session.createNamedQuery("findAccount");
        findAccountQuery.setParameter("userName", userName);
        findAccountQuery.setParameter("password", password);
        Account account = (Account) findAccountQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return account;
    }

    //verificam daca userul exista in baza de date
    public Account checkRegistration(String userName, String password) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query checkRegistrationQuery = session.createNamedQuery("checkRegistration");
        checkRegistrationQuery.setParameter("userName", userName);
        checkRegistrationQuery.setParameter("password", password);
        Account account = (Account) checkRegistrationQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return account;
    }

    //stergem contul in functie de userName
    public int deleteAccount(String userName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteAccountQuery = session.createNamedQuery("deleteAccount");
        deleteAccountQuery.setParameter("userName", userName);
        int result = deleteAccountQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return result;
    }

    //cream o metoda care verifica daca exista mai multe de 1 intrare in baza de date cu acelasi userName
    //facem asta pentru a opri introducerea in baza de date a unui userName care exista deja
    public long countUserName(String userName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query checkUserNameQuery = session.createNamedQuery("checkUserName");
        checkUserNameQuery.setParameter("userName", userName);
        long result = (long) checkUserNameQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return result;
    }
    //updatam userName-ul unui user
    public int changeUserName(String newUserName, String userName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query changeUserNameQuery = session.createNamedQuery("changeUserName");
        changeUserNameQuery.setParameter("newUserName", newUserName);
        changeUserNameQuery.setParameter("userName", userName);
        int result = changeUserNameQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return result;


    }
}
