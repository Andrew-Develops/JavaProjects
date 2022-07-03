package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.entities.Room;
import persistence.util.HibernateUtil;

import javax.persistence.NoResultException;
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
    public Room findRoomByType(String type) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query findRoomByTypeQuery = session.createNamedQuery("findRoomByType");
        findRoomByTypeQuery.setParameter("type", type);
        //cand incercam sa adaugam cascadat informatiile aveam o eroare NoResultException, care nu ne permitea
        //se pare ca asta a rezolvat treaba
        Room room = null;
        try {
            room = (Room) findRoomByTypeQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        session.close();
        return room;
    }

    //actualizam numarul de camere disponibile
    public int updateRoomAvailable(String typeRoom, int roomAvailable) {
        Session session = HibernateUtil.getSessionFactoryMethod().openSession();
        session.beginTransaction();
        Query updateRoomAvailableQuery = session.createNamedQuery("updateRoomsAvailable");
        updateRoomAvailableQuery.setParameter("roomsAvailable", roomAvailable);
        updateRoomAvailableQuery.setParameter("type", typeRoom);
        int result = updateRoomAvailableQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
