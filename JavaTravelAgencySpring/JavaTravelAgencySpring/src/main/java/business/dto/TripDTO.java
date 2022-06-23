package business.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class TripDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "([a-z A-Z])*")
    private String name;
    @NotNull
    private String mealType;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date departureDate;
    @NotNull
    private Date returnDate;
    @NotNull
    private int numberOfDays;
    @NotNull
    private boolean promoted;
    @NotNull
    private double priceForAdult;
    @NotNull
    private double priceForChild;
    @NotNull
    private int numberOfTripsAvailable;
    @NotNull
    private int numberOfAdults;
    @NotNull
    private int numberOfChildren;
    @Valid
    private FlightDTO departureFlightDTO;
    @Valid
    private FlightDTO returningFlightDTO;
    @Valid
    private HotelDTO stayingHotelDTO;

    public TripDTO(String name, String mealType, java.sql.Date departureDate, Date returnDate, int numberOfDays, boolean promoted,
                   double priceForAdult, double priceForChild, int numberOfTripsAvailable, int numberOfAdults, int numberOfChildren) {
        this.name = name;
        this.mealType = mealType;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.numberOfDays = numberOfDays;
        this.promoted = promoted;
        this.priceForAdult = priceForAdult;
        this.priceForChild = priceForChild;
        this.numberOfTripsAvailable = numberOfTripsAvailable;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    }

    public TripDTO() {
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

    public java.sql.Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(java.sql.Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public FlightDTO getDepartureFlightDTO() {
        return departureFlightDTO;
    }

    public void setDepartureFlightDTO(FlightDTO departureFlightDTO) {
        this.departureFlightDTO = departureFlightDTO;
    }

    public FlightDTO getReturningFlightDTO() {
        return returningFlightDTO;
    }

    public void setReturningFlightDTO(FlightDTO returningFlightDTO) {
        this.returningFlightDTO = returningFlightDTO;
    }

    public HotelDTO getStayingHotelDTO() {
        return stayingHotelDTO;
    }

    public void setStayingHotelDTO(HotelDTO stayingHotelDTO) {
        this.stayingHotelDTO = stayingHotelDTO;
    }

    @Override
    public String toString() {
        return "TripDTO{" +
                "name='" + name + '\'' +
                ", mealType='" + mealType + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", numberOfDays=" + numberOfDays +
                ", promoted=" + promoted +
                ", priceForAdult=" + priceForAdult +
                ", priceForChild=" + priceForChild +
                ", numberOfTripsAvailable=" + numberOfTripsAvailable +
                ", numberOfAdults=" + numberOfAdults +
                ", numberOfChildren=" + numberOfChildren +
                ", departureFlightDTO=" + departureFlightDTO +
                ", returningFlightDTO=" + returningFlightDTO +
                ", stayingHotelDTO=" + stayingHotelDTO +
                '}';
    }
}
