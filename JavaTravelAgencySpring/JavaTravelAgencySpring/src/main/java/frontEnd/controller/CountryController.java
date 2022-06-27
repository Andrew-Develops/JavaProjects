package frontEnd.controller;

import business.dto.CountryDTO;
import business.service.ContinentService;
import business.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryService countryService;
    @Autowired
    ContinentService continentService;

    @PostMapping(path = "/insertCountry")
    public ResponseEntity insertCountry(@RequestBody @Valid CountryDTO countryDTO) {
        if (countryService.countCountry(countryDTO.getName()) != 0) {
            //countryService.insertCountry(countryDTO);
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("This country is already in the database.");
        }
        countryService.insertCountry(countryDTO);
        return ResponseEntity.ok("Country: " + countryDTO.getName() + " added");
    }

    @GetMapping(path = "/findCountry")
    public ResponseEntity getCountryByName(@RequestParam String name) {
        CountryDTO countryDTO = countryService.findCountryByName(name);
        if (countryDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No country with that name in the database");
        }
        return ResponseEntity.ok(countryDTO);
    }

    @GetMapping(path = "/findCountriesInContinent")
    public ResponseEntity getCountryByContinent(@RequestParam String nameContinent) {
        if (continentService.countContinent(nameContinent) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No country with that name in the database");
        }
        List<CountryDTO> countryDTOList = countryService.findCountriesByContinent(nameContinent);
        if (countryDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Countr: " + nameContinent + " not found on that continent");
        }
        return ResponseEntity.ok(countryDTOList);
    }

    @DeleteMapping(path = "/deleteCountry")
    public ResponseEntity deleteCountryByName(@RequestParam String name) {
        if (countryService.countCountry(name) != 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No country with that name in the database");
        }
        countryService.deleteCountry(name);
        return ResponseEntity.ok("Country: " + name + " deleted from database");
    }

    @PutMapping(path = "/changeCountryName")
    public ResponseEntity changeCountryName(@RequestParam String oldName, String newName) {
        //mai intai verificam daca numele cu care vrem sa il schimbam deja exista in baza de date
        if (countryService.countCountry(newName) != 0) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("There is already a country with the name: " + newName + " in database");
        }
        int result = countryService.changeCountryName(oldName, newName);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(oldName + " was not found in database");
        } else {
            return ResponseEntity.ok("Country: " + oldName + " changed into " + newName);
        }
    }

}
