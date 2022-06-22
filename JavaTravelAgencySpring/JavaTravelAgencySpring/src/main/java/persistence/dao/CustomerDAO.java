package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Customer;
import persistence.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class CustomerDAO {

    //inseram un customer
    public void insertCustomer(Customer customer) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }

    //cautam customer dupa email
    public Customer findCustomerByEmail(String email) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findCustomerByEmailQuery = session.getNamedQuery("findCustomerByEmail");
        findCustomerByEmailQuery.setParameter("email", email);
        Customer customer = (Customer) findCustomerByEmailQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return customer;
    }

    //cautam customer dupa username
    public Customer findCustomerByUserName(String userName) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findCustomerByUserNameQuery = session.getNamedQuery("findCustomerByUsername");
        findCustomerByUserNameQuery.setParameter("userName", userName);
        Customer customer = (Customer) findCustomerByUserNameQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return customer;
    }

    //cautam customer dupa username si parola
    public Customer findCustomerByUserNameAndPassword(String userName, String password) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findCustomerByUserNameAndPasswordQuery = session.getNamedQuery("findCustomerByUserNameAndPassword");
        findCustomerByUserNameAndPasswordQuery.setParameter("userName", userName);
        findCustomerByUserNameAndPasswordQuery.setParameter("password", password);
        Customer customer = (Customer) findCustomerByUserNameAndPasswordQuery.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return customer;
    }

    //verificam cate intrari cu acelasi email avem in DB
    public long countEmail(String email) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findEmailDuplicateQuery = session.getNamedQuery("countEmail");
        findEmailDuplicateQuery.setParameter("email", email);
        long result = (Long) (findEmailDuplicateQuery.getSingleResult());
        session.getTransaction().commit();
        session.close();

        return result;
    }


    public int deleteCustomer(int id) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query deleteCustomerQuery = session.getNamedQuery("deleteCustomerById");
        deleteCustomerQuery.setParameter("id", id);
        int result = deleteCustomerQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return result;
    }

}
