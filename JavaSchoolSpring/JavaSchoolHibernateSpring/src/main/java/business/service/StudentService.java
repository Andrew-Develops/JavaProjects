package business.service;

import business.dto.AddressDTO;
import business.dto.GroupDTO;
import business.dto.IdentificatorDTO;
import business.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.StudentDAO;
import persistence.entities.Address;
import persistence.entities.Group;
import persistence.entities.Identificator;
import persistence.entities.Student;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentDAO studentDAO;

    //inseram un student
    public void insertStudent(StudentDTO studentDTO) {
        Student student = new Student();
        Group group = new Group();
        Address address = new Address();
        Identificator identificator = new Identificator();
        group.setName(studentDTO.getGroupDTO().getName());
        address.setCity(studentDTO.getAddressDTO().getCity());
        address.setStreet(studentDTO.getAddressDTO().getStreet());
        identificator.setNumber(studentDTO.getIdentificatorDTO().getNumber());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setYearOfBirth(studentDTO.getYearOfBirth());
        student.setElectives(studentDTO.getElectives());
        student.setStudentGroup(group);
        student.setStudentAddress(address);
        student.setStudentIdentificator(identificator);

        studentDAO.insertStudent(student);
    }

    //afisam toti studentii
    public List<StudentDTO> findAllStudents() {
        List<Student> studentList = studentDAO.findAllStudents();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student s : studentList) {
            StudentDTO studentDTO = new StudentDTO();
            GroupDTO groupDTO = new GroupDTO();
            AddressDTO addressDTO = new AddressDTO();
            IdentificatorDTO identificatorDTO = new IdentificatorDTO();
            groupDTO.setName(s.getStudentGroup().getName());
            addressDTO.setCity(s.getStudentAddress().getCity());
            addressDTO.setStreet(s.getStudentAddress().getStreet());
            identificatorDTO.setNumber(s.getStudentIdentificator().getNumber());
            studentDTO.setFirstName(s.getFirstName());
            studentDTO.setLastName(s.getLastName());
            studentDTO.setYearOfBirth(s.getYearOfBirth());
            studentDTO.setElectives(s.getElectives());
            studentDTO.setGroupDTO(groupDTO);
            studentDTO.setAddressDTO(addressDTO);
            studentDTO.setIdentificatorDTO(identificatorDTO);
            studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }

    //afisam studentii in functie de nume
    public List<StudentDTO> findStudentByName(String name) {
        List<Student> studentList = studentDAO.findStudentByName(name);
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student s : studentList) {
            StudentDTO studentDTO = new StudentDTO();
            GroupDTO groupDTO = new GroupDTO();
            AddressDTO addressDTO = new AddressDTO();
            IdentificatorDTO identificatorDTO = new IdentificatorDTO();
            groupDTO.setName(s.getStudentGroup().getName());
            addressDTO.setCity(s.getStudentAddress().getCity());
            addressDTO.setStreet(s.getStudentAddress().getStreet());
            identificatorDTO.setNumber(s.getStudentIdentificator().getNumber());
            studentDTO.setFirstName(s.getFirstName());
            studentDTO.setLastName(s.getLastName());
            studentDTO.setYearOfBirth(s.getYearOfBirth());
            studentDTO.setElectives(s.getElectives());
            studentDTO.setGroupDTO(groupDTO);
            studentDTO.setAddressDTO(addressDTO);
            studentDTO.setIdentificatorDTO(identificatorDTO);
            studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }

    public Long countStudentsByNameAndYearOfBirth(String firstName, String lastName, int yearOfBirth) {
        return studentDAO.studentCountByNameAndYearOfBirth(firstName, lastName, yearOfBirth);
    }

    public int updateStudentFirstName(String firstName, int id) {
        int result = studentDAO.updateStudentFirstName(firstName, id);
        return result;
    }

    public int updateStudentLastName(String lastName, int id) {
        int result = studentDAO.updateStudentLastName(lastName, id);
        return result;
    }

    public int deleteStudent(String firstName, String lastName) {
        int result = studentDAO.deleteStudentByName(firstName, lastName);
        return result;
    }
}
