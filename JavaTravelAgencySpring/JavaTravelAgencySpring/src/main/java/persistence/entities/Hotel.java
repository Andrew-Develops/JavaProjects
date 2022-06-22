package persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "number_of_stars")
    private double numberOfStars;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cities_id")
    private City city;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "hotels_rooms",
            joinColumns = {@JoinColumn(name = "hotels_id")},
            inverseJoinColumns = {@JoinColumn(name = "rooms_id")})
    private Set<Room> roomSet;

    public Hotel(String name, String address, double numberOfStars, String description) {
        this.name = name;
        this.address = address;
        this.numberOfStars = numberOfStars;
        this.description = description;
    }

    public Hotel() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(double numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Hotel: " + name + ", address: " + address + ", number of stars: " + numberOfStars
                + ", description: " + description + ", " + city;
    }
}
