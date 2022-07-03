package frontEnd.controller;

import business.dto.HotelDTO;
import business.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping(path = "/insertHotel")
    public ResponseEntity insertHotel(@RequestBody @Valid HotelDTO hotelDTO) {
        List<String> addressList = hotelService.countHotelByAddress(hotelDTO.getAddress());
        if (addressList.contains(hotelDTO.getAddress())) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Hotel: " + hotelDTO.getName() + " with address: " + hotelDTO.getAddress() + " is already in the database");
        }
        hotelService.insertHotelDTO(hotelDTO);
        return ResponseEntity.ok(hotelDTO.getName() + " added.");
    }

    @GetMapping(path = "/findHotelByName")
    public ResponseEntity findHotelByName(@RequestParam String hotelName) {
        List<HotelDTO> hotelDTOList = hotelService.findHotelByName(hotelName);
        if (hotelDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hotels with the name: " + hotelName + " found in database");
        }
        return ResponseEntity.ok(hotelDTOList);
    }

    @GetMapping(path = "/findHotelByCity")
    public ResponseEntity findHotelByCity(@RequestParam String cityName) {
        List<HotelDTO> hotelDTOList = hotelService.findHotelInCity(cityName);
        if (hotelDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hotels found in city: " + cityName);
        }
        return ResponseEntity.ok(hotelDTOList);
    }

    @GetMapping(path = "/findHotelByAddress")
    public ResponseEntity findHotelByAddress(@RequestParam String addressName) {
        HotelDTO hotelDTO = hotelService.findHotelByAddress(addressName);
        if (hotelDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't find any hotel with the address: " + addressName);
        }
        return ResponseEntity.ok(hotelDTO);
    }

    @PutMapping(path = "/changeHotelName")
    public ResponseEntity changeHotelName(@RequestParam String oldName, String newName) {
        if (hotelService.countHotelByName(newName) != 0) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(newName + " there is already a hotel with that name in database");
        }
        int result = hotelService.changeHotelName(oldName, newName);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(oldName + " was not found in database");
        } else {
            return ResponseEntity.ok("Hotel: " + oldName + " changed into " + newName);
        }
    }

    @DeleteMapping(path = "/deleteHotelByName")
    public ResponseEntity deleteHotelByName(@RequestParam String hotelName) {
        //daca este egal cu 0, atunci nu exista nici un hotel cu acel nume in baza de date
        if (hotelService.countHotelByName(hotelName) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(hotelName + " can't found that city in database");
        }
        hotelService.deleteHotelByName(hotelName);
        return ResponseEntity.ok("City: " + hotelName + " deleted from database");
    }
}
