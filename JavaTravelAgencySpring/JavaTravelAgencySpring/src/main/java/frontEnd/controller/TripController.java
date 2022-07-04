package frontEnd.controller;

import business.dto.TripDTO;
import business.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
public class TripController {

    @Autowired
    TripService tripService;

    //inseram o excursie
    @PostMapping(path = "/insertTrip")
    public ResponseEntity insertTrip(@RequestBody @Valid TripDTO tripDTO) {
        long result = tripService.countTrips(tripDTO.getName(), tripDTO.getDepartureDate());
        if (result != 0) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Trip: " + tripDTO.getName() + " already added for date: " + tripDTO.getDepartureDate());
        }
        tripService.insertTrip(tripDTO);
        return ResponseEntity.ok(tripDTO.getName() + " added");
    }

    //cautam toate excursiile
    @GetMapping(path = "/findAllTrips")
    public ResponseEntity findAllTrips() {
        List<TripDTO> tripDTOList = tripService.findAllTrips();
        if (tripDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No trips found in database. ");
        }
        return ResponseEntity.ok(tripDTOList);
    }

    //cautam excursiile care sunt la promotie
    @GetMapping(path = "/findPromotedTrips")
    public ResponseEntity findPromotedTrips(boolean promoted) {
        List<TripDTO> tripDTOList = tripService.findPromotedTrips(promoted);
        if (tripDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No trips found.");
        }
        return ResponseEntity.ok(tripDTOList);
    }

    //cautam o excursie dupa nume
    @GetMapping(path = "/findTripsByName")
    public ResponseEntity findTripsByName(String name) {
        List<TripDTO> tripDTOList = tripService.findTripsByName(name);
        if (tripDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No trips found.");
        }
        return ResponseEntity.ok(tripDTOList);
    }

    //cautam o excursie dupa data plecarii
    @GetMapping(path = "/findTripsByDepartureDate")
    public ResponseEntity findTripsByDepartureDate(Date date) {
        List<TripDTO> tripDTOList = tripService.findTripsByDepartureDate(date);
        if (tripDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No trips found.");
        }
        return ResponseEntity.ok(tripDTOList);
    }

    //cautam o excursie dupa tipul de servire
    @GetMapping(path = "/findTripsByMealType")
    public ResponseEntity findTripsByMealType(String mealType) {
        List<TripDTO> tripDTOList = tripService.findTripByMealType(mealType);
        if (tripDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No trips found.");
        }
        return ResponseEntity.ok(tripDTOList);
    }

    //stergem o excursie dupa nume
    @DeleteMapping(path = "/deleteTripByName")
    public ResponseEntity deleteTripByName(@RequestParam String name) {
        if (tripService.deleteTripByName(name) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No trip with the name: " + name + " found");
        }
        tripService.deleteTripByName(name);
        return ResponseEntity.ok("Trip: " + name + " deleted");
    }

}
