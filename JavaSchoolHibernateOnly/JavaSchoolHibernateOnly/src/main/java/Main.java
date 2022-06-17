import persistence.dao.GroupDAO;
import persistence.dao.StudentDAO;
import persistence.dao.TeacherDAO;
import persistence.entities.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        //Mai jos am facut toate operatiile CRUD folosind frameworkul Hibernate
        //Pe entitatea Teacher si Student avem : insert,find,update,delete
        //Pe entitatea Group avem : insert,find
        //Constrainturile sunt urmatoarele : unique pe:
        //Group.name, Identificator.number,
        //Teacher.addressId, Teacher.identificatorId, Student.addressId, Student.identificatorId
        //Relatii One-to-One: Teacher.addressId, Teacher.identificatorId, Student.addressId, Student.identificatorId
        //Relatii Many-to-One: Students pe Group
        //relatii One-to-Many: Group pe Student
        //relatii Many-to-Many: Teacher si Student

        StudentDAO studentDAO = new StudentDAO();
        GroupDAO groupDAO = new GroupDAO();
        TeacherDAO teacherDAO = new TeacherDAO();


        // <-------- Inseram un student in DB ------->
        Address address = new Address("Bucuresti", "Grivitei");
        Identificator identificator = new Identificator(15678);
        Group group = new Group("12a");
        Student Marian = new Student("Marian", "Ionita", 1990, "desen", group, address, identificator);
        studentDAO.insertStudent(Marian);


        // <-------- Afisam toti studentii din DB ------->
        List<Student> studentList = studentDAO.findAllStudents();
        for (Student s : studentList) {
            System.out.println(s);
        }

        // <-------- Afisam studentii dupa nume ------->
        String name = "Marian";
        List<Student> studentList2 = studentDAO.findStudentByName(name);
        for (Student s : studentList2) {
            System.out.println(s);
        }

        // <-------- Stergem un student dupa nume ------->
        String studentName = "Marian";
        studentDAO.deleteStudentByName(studentName);

        // <-------- Updatam numele si prenumele unui student dupa id ------->
        studentDAO.updateStudentFirstName("Gabi", 2);
        studentDAO.updateStudentLastName("Alexandrescu", 2);

        // <-------- Inseram un profesor in DB ------->
        Address addressTeacher = new Address("Braila", "Grivistei");
        Identificator identificatorTeacher = new Identificator(25475);
        Teacher Anton = new Teacher("Anton", "Marinescu", "stiinte", addressTeacher, identificatorTeacher);
        teacherDAO.insertTeacher(Anton);

        // <-------- Afisam toti profesorii din DB ------->
        List<Teacher> teacherList = teacherDAO.findAllTeachers();
        for (Teacher t : teacherList) {
            System.out.println(t);
        }

        // <-------- Cautam un profesor dupa nume in DB ------->
        List<Teacher> teacherList1 = teacherDAO.findTeacherByName("Anton");
        for (Teacher t : teacherList1) {
            System.out.println(t);
        }

        // <-------- Stergem un profesor dupa nume in DB ------->
        teacherDAO.deleteTeacherByName("Anton");

        // <-------- Updatam numele si prenumele unui profesor dupa id ------->
        teacherDAO.updateTeacherFirstName("Adrian", 5);
        teacherDAO.updateTeacherLastName("Maries", 5);

        // <-------- Inseram o grupa in DB ------->
        Group group12a = new Group("12a");
        groupDAO.insertGroup(group12a);

        // <-------- Afisam o grupa dupa nume in DB ------->
        List<Group> groupList = groupDAO.findGroupByName("12a");
        for (Group g : groupList) {
            System.out.println(g);
        }

        // <-------- Inseram un grup de studenti ------->
        //cream un grup de studenti, apoi ii bagam intr-un obiect group si apoi ii inseram in DB
        Address onesti = new Address("Onesti", "Izvor");
        Address bacau = new Address("Bacau", "Argentinei");
        Identificator identificator4 = new Identificator(165798);
        Identificator identificator5 = new Identificator(196245);
        Student ion = new Student("Ion", "Iftimie", 2005, "stiinte", onesti, identificator4);
        Student marcel = new Student("Marcel", "Avram", 2007, "gimnastica", bacau, identificator5);
        Set<Student> studentSet1 = new HashSet<>();
        studentSet1.add(ion);
        studentSet1.add(marcel);
        Group group12c = new Group("9c", studentSet1);
        groupDAO.insertGroup(group12c);

        // <-------- Inseram un Set de Students pe o gupa deja existenta in DB ------->
        //Pentru a introduce studenti pe un tabel deja existent trebuie mai intai sa luam tabelul din DB, apoi sa il asignam studentilor
        Address pitesti = new Address("Pitesti", "Moroistei");
        Address oradea = new Address("Oradea", "Morometii");
        Identificator identificator2 = new Identificator(157653);
        Identificator identificator3 = new Identificator(190635);
        Student matei = new Student("Matei", "Ibrahim", 2001, "gimnastica", pitesti, identificator2);
        Student marius = new Student("Marius", "Pascal", 2001, "gimnastica", oradea, identificator3);
        List<Group> groupList4 = groupDAO.findGroupByName("12a");
        matei.setStudentGroup(groupList4.get(0));
        marius.setStudentGroup(groupList4.get(0));
        studentDAO.insertStudent(matei);
        studentDAO.insertStudent(marius);

        // <-------- Setam un grup de Teacher pe Students si ii adaugam in DB ------->
        Address sibiu = new Address("Sibiu", "Moristii");
        Identificator identificator6 = new Identificator(190453);
        Group group5b = new Group("5b");
        Student alexandra = new Student("Alexandra", "Manole", 1994, "gimnastica", group5b, sibiu, identificator6);
        Address bihor = new Address("Bihor", "Grivistei");
        Address satuMare = new Address("Satu Mare", "Huit");
        Identificator identificator8 = new Identificator(29500);
        Identificator identificator9 = new Identificator(29555);
        Teacher gavril = new Teacher("Gavril", "Mihai", "matematica", bihor, identificator8);
        Teacher ionascu = new Teacher("Ionascu", "Mircea", "arte plastice", satuMare, identificator9);
        Set<Teacher> teacherSet2 = new HashSet<>();
        teacherSet2.add(gavril);
        teacherSet2.add(ionascu);
        alexandra.setTeacherSet(teacherSet2);
        studentDAO.insertStudent(alexandra);


    }
}
