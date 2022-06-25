package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "identificator")
public class Identificator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "number")
    private int number;
    @OneToOne(mappedBy = "studentIdentificator")
    private Student student;
    @OneToOne(mappedBy = "teacherIdentificator")
    private Teacher teacher;

    public Identificator(int number) {
        this.number = number;
    }

    public Identificator() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Identificator{" +
                "number=" + number +
                '}';
    }
}
