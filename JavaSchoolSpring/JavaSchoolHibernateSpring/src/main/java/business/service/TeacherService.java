package business.service;

import business.dto.AddressDTO;
import business.dto.IdentificatorDTO;
import business.dto.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.TeacherDAO;
import persistence.entities.Address;
import persistence.entities.Identificator;
import persistence.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherDAO teacherDAO;

    //inseram un profesor
    public void insertTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        Address address = new Address();
        Identificator identificator = new Identificator();
        identificator.setNumber(teacherDTO.getIdentificatorDTO().getNumber());
        address.setCity(teacherDTO.getAddressDTO().getCity());
        address.setStreet(teacherDTO.getAddressDTO().getStreet());
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setCurriculum(teacherDTO.getCurriculum());
        teacher.setTeacherAddress(address);
        teacher.setTeacherIdentificator(identificator);

        teacherDAO.insertTeacher(teacher);
    }

    //afisam toti profesorii
    public List<TeacherDTO> findAllTeachers() {
        List<Teacher> teacherList = teacherDAO.findAllTeachers();
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        for (Teacher t : teacherList) {
            TeacherDTO teacherDTO = new TeacherDTO();
            AddressDTO addressDTO = new AddressDTO();
            IdentificatorDTO identificatorDTO = new IdentificatorDTO();

            addressDTO.setCity(t.getTeacherAddress().getCity());
            addressDTO.setStreet(t.getTeacherAddress().getStreet());
            identificatorDTO.setNumber(t.getTeacherIdentificator().getNumber());
            teacherDTO.setFirstName(t.getFirstName());
            teacherDTO.setLastName(t.getLastName());
            teacherDTO.setCurriculum(t.getCurriculum());
            teacherDTO.setAddressDTO(addressDTO);
            teacherDTO.setIdentificatorDTO(identificatorDTO);

            teacherDTOList.add(teacherDTO);
        }
        return teacherDTOList;
    }

    //afisam profesorii in functie de nume
    public List<TeacherDTO> findTeacherByName(String name) {
        List<Teacher> teacherList = teacherDAO.findTeacherByName(name);
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        for (Teacher t : teacherList) {
            TeacherDTO teacherDTO = new TeacherDTO();
            AddressDTO addressDTO = new AddressDTO();
            IdentificatorDTO identificatorDTO = new IdentificatorDTO();
            addressDTO.setCity(t.getTeacherAddress().getCity());
            addressDTO.setStreet(t.getTeacherAddress().getStreet());
            identificatorDTO.setNumber(t.getTeacherIdentificator().getNumber());
            teacherDTO.setFirstName(t.getFirstName());
            teacherDTO.setLastName(t.getLastName());
            teacherDTO.setCurriculum(t.getCurriculum());
            teacherDTO.setAddressDTO(addressDTO);
            teacherDTO.setIdentificatorDTO(identificatorDTO);

            teacherDTOList.add(teacherDTO);
        }
        return teacherDTOList;
    }

    public Long teacherCountByName(String firstName, String lastName) {
        return teacherDAO.teacherCountByName(firstName, lastName);
    }

    public int updateTeacherFirstName(String firstName, int id) {
        int result = teacherDAO.updateTeacherFirstName(firstName, id);
        return result;
    }

    public int updateTeacherLastName(String lastName, int id) {
        int result = teacherDAO.updateTeacherLastName(lastName, id);
        return result;
    }

    public int deleteTeacher(String firstName, String lastName) {
        int result = teacherDAO.deleteTeacherByName(firstName, lastName);
        return result;
    }
}
