package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Room;
import persistence.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RoomDAO {

    //inseram o camera
    public void insertRoom(Room room) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        session.save(room);
        session.getTransaction().commit();
        session.close();
    }

    //cautam o camera dupa tipul ei
    public List<Room> findRoomByType(String type) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findRoomByTypeQuery = session.createNamedQuery("findRoomByType");
        findRoomByTypeQuery.setParameter("type", type);
        List<Room> result = findRoomByTypeQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    //actualizam numarul de camere disponibile
    public int updateRoomAvailable(String type, String available) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateRoomAvailableQuery = session.createNamedQuery("updateRoomsAvailable");
        updateRoomAvailableQuery.setParameter("roomsAvailable", available);
        updateRoomAvailableQuery.setParameter("type", type);
        int result = updateRoomAvailableQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
