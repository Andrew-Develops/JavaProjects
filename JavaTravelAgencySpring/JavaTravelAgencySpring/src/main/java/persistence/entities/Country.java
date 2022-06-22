package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "continents_id")
    private Continent continent;

    @OneToMany(mappedBy = "country")
    private Set<City> citySet;

    public Country(String name) {
        this.name = name;
    }

    public Country() {
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

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Set<City> getCitySet() {
        return citySet;
    }

    public void setCitySet(Set<City> citySet) {
        this.citySet = citySet;
    }

    @Override
    public String toString() {
        return "Country: " + name + ", " + continent;
    }
}
