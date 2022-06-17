package persistence.entities;
import persistence.entities.Student;
import javax.persistence.*;
import java.util.Set;
@NamedQueries({
        @NamedQuery(name = "findGroupByName", query = "select group from Group group where group.name = :name")
})
@Entity
@Table(name = "classes")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL)
    private Set<Student> studentSet;

    public Group(String name, Set<Student> studentSet) {
        this.name = name;
        this.studentSet = studentSet;
        for(Student s : this.studentSet){
            s.setStudentGroup(this);
        }
    }

    public Group(String name) {
        this.name = name;
    }

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                '}';
    }
}
