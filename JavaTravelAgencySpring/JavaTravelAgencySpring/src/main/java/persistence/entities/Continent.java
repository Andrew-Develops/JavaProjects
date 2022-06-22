package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "continent")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL)
    private Set<Country> countrySet;

    public Continent(String name) {
        this.name = name;
    }

    public Continent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Continent: " + name;
    }
}