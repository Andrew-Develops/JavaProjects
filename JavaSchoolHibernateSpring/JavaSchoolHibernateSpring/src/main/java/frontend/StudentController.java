package frontend;

import business.dto.StudentDTO;
import business.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    //afisam toti studentii
    @GetMapping(path = "/getAllStudents")
    public ResponseEntity<List<StudentDTO>> findAllStudents() {
        List<StudentDTO> studentDTOList = studentService.findAllStudents();
        if (studentDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(studentDTOList);
        }
    }

    //afisam studentii dupa nume
    @GetMapping(path = "/getStudentByName")
    public ResponseEntity<List<StudentDTO>> findStudentByName(@RequestParam String name) {
        List<StudentDTO> studentDTOList = studentService.findStudentByName(name);
        if (studentDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(studentDTOList);
        }
    }

    //inseram un student in db
    //mai intai verificam daca mai exista un student cu acelasi: nume, prenume, anul nasterii pentru a nu il adauga inca o data
    @PostMapping(path = "/insertStudent")
    public String insertStudent(@RequestBody @Valid StudentDTO studentDTO) {
        Long numberOfStudents = studentService.countStudentsByNameAndYearOfBirth(studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getYearOfBirth());
        //Inainte sa inseram un student verificam daca mai exista deja un student cu acelasi : nume, prenume, anul nasterii
        if (numberOfStudents == 0) {
            studentService.insertStudent(studentDTO);
            return "Studentul inserat cu succes";
        } else {
            return "Studentul deja existent in baza de date";
        }
    }

    //updatam numele unui student in functie de id-ul lui din db
    @PutMapping(path = "/updateStudentFirstName")
    public String updateStudentFirstName(@RequestParam String firstName, @RequestParam int id) {
        int changesMade = studentService.updateStudentFirstName(firstName, id);
        if (changesMade > 0) {
            return "Studentul updatat cu succes";
        } else {
            return "Studentul nu a fost updatat";
        }
    }

    //updatam prenumele unui student in functie de id-ul lui din db
    @PutMapping(path = "/updateStudentLastName")
    public String updateStudentLastName(@RequestParam String lastName, @RequestParam int id) {
        int changesMade = studentService.updateStudentLastName(lastName, id);
        if (changesMade > 0) {
            return "Studentul updatat cu succes";
        } else {
            return "Studentul nu a fost updatat";
        }
    }

    //stergem un student din db
    //mai intai verificam daca exista mai mult de 1 student cu acelasi: nume, prenume, anul nasterii pentru a nu sterge mai multe de 1 student
    @DeleteMapping(path = "/deleteStudentByName")
    public String deleteStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int yearOfBirth) {
        Long numberOfStudents = studentService.countStudentsByNameAndYearOfBirth(firstName, lastName, yearOfBirth);
        //daca avem mai multe de 1 student cu acelasi nume atunci nu efectuam operatia si cerem sa se restranga cautare
        if (numberOfStudents <= 1) {
            //daca operatiunea a fost efectuata returnam numarul de randuri afectate in db
            int operationsMade = studentService.deleteStudent(firstName, lastName);
            if (operationsMade > 0) {
                return "Operatie efectuata cu succes.Intrari sterse: " + operationsMade;
            } else {
                return "Nu a fost sters nici un student";
            }
        } else {
            return "Restrangeti va rog campurile";
        }
    }

}
