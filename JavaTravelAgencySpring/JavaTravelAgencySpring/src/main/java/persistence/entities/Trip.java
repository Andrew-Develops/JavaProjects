package persistence.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "departure_date")
    private Date departureDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_flight")
    private Flight departureFlight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staying_hotel")
    private Hotel stayingHotel;

    @Column(name = "return_date")
    private Date returnDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "returning_flight")
    private Flight returningFlight;

    @Column(name = "number_of_days")
    private int numberOfDays;

    @Column(name = "promoted")
    private boolean promoted;

    @Column(name = "price_for_adults")
    private double priceForAdult;

    @Column(name = "price_for_child")
    private double priceForChild;

    @Column(name = "number_of_trips_available")
    private int numberOfTripsAvailable;

    @OneToOne(mappedBy = "trip")
    private Set<PurchasedTrip> purchasedTripSet;

    public Trip(String name, String mealType, Date departureDate, Flight departureFlight, Hotel stayingHotel, Date returnDate, Flight returningFlight, int numberOfDays, boolean promoted, double priceForAdult, double priceForChild, int numberOfTripsAvailable) {
        this.name = name;
        this.mealType = mealType;
        this.departureDate = departureDate;
        this.departureFlight = departureFlight;
        this.stayingHotel = stayingHotel;
        this.returnDate = returnDate;
        this.returningFlight = returningFlight;
        this.numberOfDays = numberOfDays;
        this.promoted = promoted;
        this.priceForAdult = priceForAdult;
        this.priceForChild = priceForChild;
        this.numberOfTripsAvailable = numberOfTripsAvailable;
    }

    public Trip() {
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

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Flight getDepartureFlight() {
        return departureFlight;
    }

    public void setDepartureFlight(Flight departureFlight) {
        this.departureFlight = departureFlight;
    }

    public Hotel getStayingHotel() {
        return stayingHotel;
    }

    public void setStayingHotel(Hotel stayingHotel) {
        this.stayingHotel = stayingHotel;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Flight getReturningFlight() {
        return returningFlight;
    }

    public void setReturningFlight(Flight returningFlight) {
        this.returningFlight = returningFlight;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public double getPriceForAdult() {
        return priceForAdult;
    }

    public void setPriceForAdult(double priceForAdult) {
        this.priceForAdult = priceForAdult;
    }

    public double getPriceForChild() {
        return priceForChild;
    }

    public void setPriceForChild(double priceForChild) {
        this.priceForChild = priceForChild;
    }

    public int getNumberOfTripsAvailable() {
        return numberOfTripsAvailable;
    }

    public void setNumberOfTripsAvailable(int numberOfTripsAvailable) {
        this.numberOfTripsAvailable = numberOfTripsAvailable;
    }

    public Set<PurchasedTrip> getPurchasedTripSet() {
        return purchasedTripSet;
    }

    public void setPurchasedTripSet(Set<PurchasedTrip> purchasedTripSet) {
        this.purchasedTripSet = purchasedTripSet;
    }

    @Override
    public String toString() {
        return "Trip: " + name + "departure flight: " + departureFlight + " returning flight: " + returningFlight
                + ", staying Hotel:" + stayingHotel + ", meal type:" + mealType + ", departure date:" + departureDate
                + ", return date:" + returnDate + ", number of days:" + numberOfDays + ", promoted:" + promoted
                + ", price for adult:" + priceForAdult + ", price for child:" + priceForChild
                + ", number of trips available: " + numberOfTripsAvailable;
    }
}
