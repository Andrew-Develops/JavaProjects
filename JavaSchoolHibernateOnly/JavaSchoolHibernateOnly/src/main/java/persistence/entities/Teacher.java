package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findAllTeachers", query = "select teacher from Teacher teacher"),
        @NamedQuery(name = "findTeacherByName", query = "select teacher from Teacher teacher where teacher.firstName = :firstName"),
        @NamedQuery(name = "deleteTeacherByName", query = "delete Teacher where firstName = :firstName"),
        @NamedQuery(name = "updateTeacherFirstNameById", query = "update from Teacher teacher set teacher.firstName = :firstName where teacher.id = :id"),
        @NamedQuery(name = "updateTeacherLastNameById", query = "update from Teacher teacher set teacher.lastName = :lastName where teacher.id = :id")

})
@Entity
@Table(name = "professor")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "curriculum")
    private String curriculum;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address teacherAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identificator_id")
    private Identificator teacherIdentificator;
    @ManyToMany(mappedBy = "teacherSet", cascade = CascadeType.ALL)
    private Set<Student> studentSet;

    public Teacher(String firstName, String lastName, String curriculum, Address teacherAddress, Identificator teacherIdentificator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.curriculum = curriculum;
        this.teacherAddress = teacherAddress;
        this.teacherIdentificator = teacherIdentificator;
    }

    public Teacher(String firstName, String lastName, String curriculum, Address teacherAddress, Identificator teacherIdentificator, Set<Student> studentSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.curriculum = curriculum;
        this.teacherAddress = teacherAddress;
        this.teacherIdentificator = teacherIdentificator;
        this.studentSet = studentSet;
    }

    public Teacher() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public Address getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(Address teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public Identificator getTeacherIdentificator() {
        return teacherIdentificator;
    }

    public void setTeacherIdentificator(Identificator teacherIdentificator) {
        this.teacherIdentificator = teacherIdentificator;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", curriculum='" + curriculum + '\'' +
                ", teacherAddress=" + teacherAddress +
                ", teacherIdentificator=" + teacherIdentificator +
                '}';
    }
}
