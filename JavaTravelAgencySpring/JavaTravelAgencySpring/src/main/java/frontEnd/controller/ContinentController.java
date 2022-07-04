package frontEnd.controller;

import business.dto.ContinentDTO;
import business.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContinentController {

    @Autowired
    ContinentService continentService;

    //adaugam un continent
    @PostMapping(path = "/insertContinent")
    public ResponseEntity insertContinent(@RequestBody ContinentDTO continentDTO) {
        if (continentService.countContinent(continentDTO.getName()) != 0) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Continent: " + continentDTO.getName() + " already in database");
        }
        continentService.insertContinent(continentDTO);
        return ResponseEntity.ok(continentDTO.getName() + " added.");
    }

    //cautam un continent dupa nume
    @GetMapping(path = "/findContinentByName")
    public ResponseEntity findContinentByName(@RequestParam String name){
        ContinentDTO continentDTO = continentService.findContinentByName(name);
        if(continentDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(name + " no continent with that name in the database.");
        }
        return ResponseEntity.ok(continentDTO);
    }

    //stergem un continent
    @DeleteMapping(path = "/deleteContinentByName")
    public ResponseEntity deleteContinentByName(@RequestParam String name){
        if(continentService.countContinent(name)==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(name + " can't found that continent in database");
        }
        continentService.deleteContinent(name);
        return ResponseEntity.ok("Continent: " + name + " deleted from database");
    }
}
