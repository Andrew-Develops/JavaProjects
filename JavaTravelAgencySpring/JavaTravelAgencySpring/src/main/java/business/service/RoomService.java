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

    public void updateRoomAvailability(String typeRoom, int numberRoom) {
        roomDAO.updateRoomAvailable(typeRoom, numberRoom);
    }
}
