package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findALlStudents", query = "select student from Student student"),
        @NamedQuery(name = "findStudentByName", query = "select student from Student student where student.firstName = :firstName"),
        @NamedQuery(name = "deleteStudentByName", query = "delete Student where firstName = :firstName"),
        @NamedQuery(name = "updateStudentFirstNameById", query = "update from Student student set student.firstName = :firstName where student.id = :id"),
        @NamedQuery(name = "updateStudentLastNameById", query = "update from Student student set student.lastName = :lastName where student.id = :id")
})
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @Column(name = "electives")
    private String electives;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classes_id")
    private Group studentGroup;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address studentAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identificator_id")
    private Identificator studentIdentificator;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_professor",
            joinColumns = {
                    @JoinColumn(name = "student_id")}
            , inverseJoinColumns = {
            @JoinColumn(name = "professor_id")
    }
    )
    private Set<Teacher> teacherSet;

    public Student(String firstName, String lastName, int yearOfBirth, String electives, Group studentGroup, Address studentAddress, Identificator studentIdentificator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.electives = electives;
        this.studentGroup = studentGroup;
        this.studentAddress = studentAddress;
        this.studentIdentificator = studentIdentificator;
    }

    public Student(String firstName, String lastName, int yearOfBirth, String electives, Group studentGroup, Address studentAddress, Identificator studentIdentificator, Set<Teacher> teacherSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.electives = electives;
        this.studentGroup = studentGroup;
        this.studentAddress = studentAddress;
        this.studentIdentificator = studentIdentificator;
        this.teacherSet = teacherSet;
    }

    public Student(String firstName, String lastName, int yearOfBirth, String electives, Address studentAddress, Identificator studentIdentificator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.electives = electives;
        this.studentAddress = studentAddress;
        this.studentIdentificator = studentIdentificator;
    }

    public Student() {
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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getElectives() {
        return electives;
    }

    public void setElectives(String electives) {
        this.electives = electives;
    }

    public Group getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(Group studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Address getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(Address studentAddress) {
        this.studentAddress = studentAddress;
    }

    public Identificator getStudentIdentificator() {
        return studentIdentificator;
    }

    public void setStudentIdentificator(Identificator studentIdentificator) {
        this.studentIdentificator = studentIdentificator;
    }

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }


    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", electives='" + electives + '\'' +
                ", studentGroup=" + studentGroup +
                ", studentAddress=" + studentAddress +
                ", studentIdentificator=" + studentIdentificator +
                '}';
    }
}
