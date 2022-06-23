package business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

public class FlightDTO {
    @NotNull
    private String flightNumber;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date departureDate;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Europe/Bucharest")
    private Timestamp departureTime;
    @Valid
    private AirportDTO departureAirport;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivingDate;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Europe/Bucharest")
    private Timestamp arrivingTime;
    @Valid
    private AirportDTO airportDTO;
    @NotNull
    private double price;
    @NotNull
    private int seatsAvailable;

    public FlightDTO(String flightNumber, Date departureDate, Timestamp departureTime, Date arrivingDate,
                     Timestamp arrivingTime, double price, int seatsAvailable) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivingDate = arrivingDate;
        this.arrivingTime = arrivingTime;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
    }

    public FlightDTO() {
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public AirportDTO getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportDTO departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Date getArrivingDate() {
        return arrivingDate;
    }

    public void setArrivingDate(Date arrivingDate) {
        this.arrivingDate = arrivingDate;
    }

    public Timestamp getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(Timestamp arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public AirportDTO getAirportDTO() {
        return airportDTO;
    }

    public void setAirportDTO(AirportDTO airportDTO) {
        this.airportDTO = airportDTO;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", departureAirport=" + departureAirport +
                ", arrivingDate=" + arrivingDate +
                ", arrivingTime=" + arrivingTime +
                ", airportDTO=" + airportDTO +
                ", price=" + price +
                ", seatsAvailable=" + seatsAvailable +
                '}';
    }
}
