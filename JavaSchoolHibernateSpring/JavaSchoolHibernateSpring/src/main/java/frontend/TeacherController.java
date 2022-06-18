package frontend;

import business.dto.TeacherDTO;
import business.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    //afisam toti profesorii
    @GetMapping(path = "/getAllTeachers")
    public ResponseEntity<List<TeacherDTO>> findAllTeachers() {
        List<TeacherDTO> teacherDTOList = teacherService.findAllTeachers();
        if (teacherDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(teacherDTOList);
        }
    }

    //afisam profesorii dupa nume
    @GetMapping(path = "/getTeacherByName")
    public ResponseEntity<List<TeacherDTO>> findTeacherByName(@RequestParam String name) {
        List<TeacherDTO> teacherDTOList = teacherService.findTeacherByName(name);
        if (teacherDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(teacherDTOList);
        }
    }

    //inseram un profesor in db
    //mai intai verificam daca mai exista un student cu acelasi: nume, prenume pentru a nu il adauga inca o data
    @PostMapping(path = "/insertTeacher")
    public String insertTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {
        Long numberOfTeachers = teacherService.teacherCountByName(teacherDTO.getFirstName(), teacherDTO.getLastName());
        if (numberOfTeachers == 0) {
            teacherService.insertTeacher(teacherDTO);
            return "Profesorul adaugat cu succes";
        } else {
            return "Profesorul deja existent in baza de date";
        }
    }

    //updatam numele unui profesor in functie de in
    @PutMapping(path = "/updateTeacherFirstName")
    public String updateTeacherFirstName(@RequestParam String firstName, @RequestParam int id) {
        int changesMade = teacherService.updateTeacherFirstName(firstName, id);
        if (changesMade > 0) {
            return "Profesorul updatat cu succes";
        } else {
            return "Profesorul nu a fost updatat";
        }
    }

    //updatam prenumele unui profesor in functie de in
    @PutMapping(path = "/updateTeacherLastName")
    public String updateTeacherLastName(@RequestParam String lastName, @RequestParam int id) {
        int changesMade = teacherService.updateTeacherLastName(lastName, id);
        if (changesMade > 0) {
            return "Profesorul updatat cu succes";
        } else {
            return "Profesorul nu a fost updatat";
        }
    }

    //stergem un profesor din db
    //mai intai verificam daca exista mai mult de 1 profesor cu acelasi: nume, prenume pentru a nu sterge mai multe de 1 profesor
    @DeleteMapping(path = "/deleteTeacherByName")
    public String deleteTeacher(@RequestParam String firstName, @RequestParam String lastName) {
        Long numberOfTeachers = teacherService.teacherCountByName(firstName, lastName);
        if (numberOfTeachers <= 1) {
            int operationsMade = teacherService.deleteTeacher(firstName, lastName);
            if (operationsMade > 0) {
                return "Operatia efectuata cu succes.Intrari sters: " + operationsMade;
            } else {
                return "Nu a fost sters nici un profesor";
            }
        } else {
            return "Restrangeti va rog campurile";
        }
    }
}
