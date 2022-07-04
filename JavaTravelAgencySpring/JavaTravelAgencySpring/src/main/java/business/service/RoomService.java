package business.service;

import business.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.RoomDAO;
import persistence.entities.Room;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomDAO roomDAO;

    //cautam o camera dupa tipul ei
    public RoomDTO findRoomByType(String type) {
        RoomDTO roomDTO = new RoomDTO();
        Room room = roomDAO.findRoomByType(type);
        roomDTO.setType(room.getType());
        roomDTO.setNumberOfRooms(room.getNumberOfRooms());
        roomDTO.setExtraBed(room.isExtraBed());
        roomDTO.setRoomsAvailable(room.getRoomsAvailable());
        roomDTO.setPrice(room.getPrice());
        return roomDTO;
    }

    //updatam o camera dupa disponibilitate
    public void updateRoomAvailability(String typeRoom, int numberRoom) {
        roomDAO.updateRoomAvailable(typeRoom, numberRoom);
    }
}
