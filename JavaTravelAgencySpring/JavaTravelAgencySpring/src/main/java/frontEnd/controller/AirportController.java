package frontEnd.controller;

import business.dto.AirportDTO;
import business.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AirportController {

    @Autowired
    AirportService airportService;

    @PostMapping(path = "/insertAirport")
    public ResponseEntity insertAirport(@RequestBody @Valid AirportDTO airportDTO) {
        if (airportService.countAirportByName(airportDTO.getName()) != 0) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("This Airport is already in the database.");
        }
        airportService.insertAeroport(airportDTO);
        return ResponseEntity.ok(airportDTO.getName() + " added.");
    }

    @GetMapping(path = "/findAirport")
    public ResponseEntity findAirport(@RequestParam String name) {
        AirportDTO airportDTO = airportService.findAirportByName(name);
        if (airportDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(name + " no airport with that name in the database.");
        }
        return ResponseEntity.ok(airportDTO);
    }

    @GetMapping(path = "/findAirportByCityName")
    public ResponseEntity findAirportByCityName(@RequestParam String cityName) {
        List<AirportDTO> airportDTOList = airportService.findAirportByCityName(cityName);
        if (airportDTOList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No airport found in city: '" + cityName);
        }
        return ResponseEntity.ok(airportDTOList);
    }

    @PutMapping(path = "/changeAirportName")
    public ResponseEntity changeAirportName(@RequestParam String oldName, String newName) {
        if (airportService.countAirportByName(newName) != 0) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(newName + " there is already an airport with that name in database");
        }
        int result = airportService.changeAirportName(oldName, newName);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(oldName + " was not found in database");
        } else {
            return ResponseEntity.ok("Airport: " + oldName + " changed into " + newName);
        }
    }

    @DeleteMapping(path = "/deleteAirportByName")
    public ResponseEntity deleteAirportByName(@RequestBody String name) {
        if (airportService.deleteAirportByName(name) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(name + " can't find airport with that name in database");
        }
        airportService.deleteAirportByName(name);
        return ResponseEntity.ok("Airport: " + name + " deleted from database");
    }
}
