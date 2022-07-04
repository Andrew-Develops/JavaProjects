package business.service;

import business.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.AirportDAO;
import persistence.dao.CityDAO;
import persistence.dao.CountryDAO;
import persistence.dao.FlightDAO;
import persistence.entities.Airport;
import persistence.entities.City;
import persistence.entities.Country;
import persistence.entities.Flight;


@Service
public class FlightService {

    @Autowired
    FlightDAO flightDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    AirportDAO airportDAO;

    //inseram un flight
    public void insertFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        setDepartureAirport(flightDTO, flight);
        setArrivingAirport(flightDTO, flight);
        flight.setArrivingDate(flightDTO.getArrivingDate());
        flight.setArrivingTime(flightDTO.getArrivingTime());
        flight.setPrice(flightDTO.getPrice());
        flight.setSeatsAvailable(flightDTO.getSeatsAvailable());
        flightDAO.insertFlight(flight);
    }

    //am setat arriving airport pe flight
    public void setArrivingAirport(FlightDTO flightDTO, Flight flight) {
        Airport arrivingAirportFound = airportDAO.findAirportByName(flightDTO.getArrivingAirport().getName());
        if (arrivingAirportFound == null) {
            Airport arrivingAirport = new Airport();
            arrivingAirport.setName(flightDTO.getArrivingAirport().getName());
            setArrivingCity(flightDTO, null, arrivingAirport);
            flight.setArrivingAirport(arrivingAirport);
        } else {
            setArrivingCity(flightDTO, arrivingAirportFound, arrivingAirportFound);
            flight.setArrivingAirport(arrivingAirportFound);
        }
    }

    //am setat arriving city pe arriving Airport
    private void setArrivingCity(FlightDTO flightDTO, Airport arrivingAirportFound, Airport arrivingAirport) {
        City arrivingCityFound = cityDAO.findCityByName(flightDTO.getArrivingAirport().getCityDTO().getName());
        if (arrivingCityFound == null) {
            City arrivingCity = new City();
            arrivingCity.setName(flightDTO.getArrivingAirport().getCityDTO().getName());
            setArrivingCountry(flightDTO, arrivingCity);
            arrivingAirport.setCity(arrivingCity);
        } else {
            setArrivingCountry(flightDTO, arrivingCityFound);
            arrivingAirportFound.setCity(arrivingCityFound);
        }
    }

    //am setat arriving Country pe oras
    private void setArrivingCountry(FlightDTO flightDTO, City arrivingCity) {
        Country arrivingCountryFound = countryDAO.findCountryByName(flightDTO.getArrivingAirport().getCityDTO().getName());
        if (arrivingCountryFound == null) {
            Country arrivingCountry = new Country();
            arrivingCountry.setName(flightDTO.getArrivingAirport().getCityDTO().getCountryDTO().getName());
            arrivingCity.setCountry(arrivingCountry);
        } else {
            arrivingCity.setCountry(arrivingCountryFound);
        }
    }

    //am setat departure Airport pe flight
    private void setDepartureAirport(FlightDTO flightDTO, Flight flight) {
        Airport departureAirportFound = airportDAO.findAirportByName(flightDTO.getDepartureAirport().getName());
        if (departureAirportFound == null) {
            Airport departureAirport = new Airport();
            departureAirport.setName(flightDTO.getDepartureAirport().getName());
            setDepartureCity(flightDTO, null, departureAirport);
            flight.setDepartureAirport(departureAirport);
        } else {
            setDepartureCity(flightDTO, departureAirportFound, departureAirportFound);
            flight.setDepartureAirport(departureAirportFound);
        }
    }

    //am setat orasul pe departure Airport
    private void setDepartureCity(FlightDTO flightDTO, Airport departureAirportFound, Airport departureAirport) {
        City departureCityFound = cityDAO.findCityByName(flightDTO.getDepartureAirport().getCityDTO().getName());
        if (departureCityFound == null) {
            City departureCity = new City();
            departureCity.setName(flightDTO.getDepartureAirport().getCityDTO().getName());
            setDepartureCountry(flightDTO, departureCity);
            departureAirport.setCity(departureCity);
        } else {
            setDepartureCountry(flightDTO, departureCityFound);
            departureAirportFound.setCity(departureCityFound);
        }
    }

    //am setat tara pe oras
    private void setDepartureCountry(FlightDTO flightDTO, City departureCity) {
        Country departureCountryFound = countryDAO.findCountryByName(flightDTO.getDepartureAirport().getCityDTO().getCountryDTO().getName());
        if (departureCountryFound == null) {
            Country departureCountry = new Country();
            departureCountry.setName(flightDTO.getDepartureAirport().getCityDTO().getCountryDTO().getName());
            departureCity.setCountry(departureCountry);
        } else {
            departureCity.setCountry(departureCountryFound);
        }
    }

    //cate instante cu acelasi nume se afla in baza de date
    public long countFlightNumber(String flightNumber) {
        return flightDAO.countFlightNumber(flightNumber);
    }

    //returnam aeroportul de plecare
    public AirportDTO getDepartureAirportDTO(Flight flight) {
        AirportDTO departureAirportDTO = new AirportDTO();
        departureAirportDTO.setName(flight.getDepartureAirport().getName());
        //ContinentDTO continentDTO = new ContinentDTO(flight.getDepartureAirport().getCity().getCountry().getContinent().getName());
        CountryDTO countryDTO = new CountryDTO(flight.getDepartureAirport().getCity().getCountry().getName());
        //countryDTO.setContinentDTO(continentDTO);
        CityDTO cityDTO = new CityDTO(flight.getDepartureAirport().getCity().getName());
        cityDTO.setCountryDTO(countryDTO);
        departureAirportDTO.setCityDTO(cityDTO);
        return departureAirportDTO;
    }

    //returnam aeroportul de sosire
    public AirportDTO getArrivingAirportDTO(Flight flight) {
        AirportDTO arrivingAirportDTO = new AirportDTO();
        arrivingAirportDTO.setName(flight.getArrivingAirport().getName());
        //ContinentDTO continentDTO = new ContinentDTO(flight.getArrivingAirport().getCity().getCountry().getContinent().getName());
        CountryDTO countryDTO = new CountryDTO(flight.getArrivingAirport().getCity().getCountry().getName());
        //countryDTO.setContinentDTO(continentDTO);
        CityDTO cityDTO = new CityDTO(flight.getArrivingAirport().getCity().getName());
        cityDTO.setCountryDTO(countryDTO);
        arrivingAirportDTO.setCityDTO(cityDTO);
        return arrivingAirportDTO;
    }

    //cautam un flight dupa flight number
    public FlightDTO findFlightByTheFlightNumber(String flightNumber) {
        Flight flight = flightDAO.findFlightByFlightNumber(flightNumber);
        if (flight == null) {
            return null;
        }
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setDepartureTime(flight.getDepartureTime());
        flightDTO.setArrivingTime(flight.getArrivingTime());
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setArrivingDate(flight.getArrivingDate());
        flightDTO.setPrice(flight.getPrice());
        flightDTO.setSeatsAvailable(flight.getSeatsAvailable());

        flightDTO.setDepartureAirport(getDepartureAirportDTO(flight));
        flightDTO.setArrivingAirport(getArrivingAirportDTO(flight));

        return flightDTO;
    }


}
