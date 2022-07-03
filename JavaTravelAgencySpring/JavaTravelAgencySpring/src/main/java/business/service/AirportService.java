package business.service;

import business.dto.AirportDTO;
import business.dto.CityDTO;
import business.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.AirportDAO;
import persistence.dao.CityDAO;
import persistence.dao.CountryDAO;
import persistence.entities.Airport;
import persistence.entities.City;
import persistence.entities.Country;

import java.util.LinkedList;
import java.util.List;

@Service
public class AirportService {

    @Autowired
    AirportDAO airportDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;

    //inseram un aeroport
    public void insertAeroport(AirportDTO airportDTO) {
        Airport airport = new Airport();
        airport.setName(airportDTO.getName());
        setCityForAirport(airportDTO, airport);
        airportDAO.insertAirport(airport);
    }

    //setam orasul pe aeroport
    public void setCityForAirport(AirportDTO airportDTO, Airport airport) {
        City cityFound = cityDAO.findCityByName(airportDTO.getCityDTO().getName());
        if (cityFound != null) {
            airport.setCity(cityFound);
        } else {
            City city = new City();
            city.setName(airportDTO.getCityDTO().getName());
            setCountryForCity(airportDTO, city);
            airport.setCity(city);
        }
    }

    //setam tara pe oras
    public void setCountryForCity(AirportDTO airportDTO, City city) {
        Country countryFound = countryDAO.findCountryByName(airportDTO.getCityDTO().getCountryDTO().getName());
        if (countryFound != null) {
            city.setCountry(countryFound);
        } else {
            Country country = new Country(city.getCountry().getName());
            city.setCountry(country);
        }
    }

    public AirportDTO findAirportByName(String airportName) {
        Airport airport = airportDAO.findAirportByName(airportName);
        if (airport == null) {
            return null;
        }
        AirportDTO airportDTO = new AirportDTO();
        CityDTO cityDTO = new CityDTO();
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(airport.getCity().getCountry().getName());
        cityDTO.setName(airport.getCity().getName());
        cityDTO.setCountryDTO(countryDTO);
        airportDTO.setName(airport.getName());
        airportDTO.setCityDTO(cityDTO);

        return airportDTO;
    }

    //cautam un aeroport dupa nume
    public List<AirportDTO> findAirportByCityName(String cityName) {
        List<AirportDTO> airportDTOList = new LinkedList<>();
        List<Airport> airportList = airportDAO.findAirportByCityName(cityName);
        if (airportList.isEmpty()) {
            return null;
        }
        for (Airport a : airportList) {
            AirportDTO airportDTO = new AirportDTO();
            CityDTO cityDTO = new CityDTO();
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(a.getCity().getCountry().getName());
            cityDTO.setName(a.getCity().getName());
            cityDTO.setCountryDTO(countryDTO);
            airportDTO.setName(a.getName());
            airportDTO.setCityDTO(cityDTO);
            airportDTOList.add(airportDTO);
        }
        return airportDTOList;
    }

    public long countAirportByName(String airportName) {
        return airportDAO.countAirportName(airportName);
    }

    public int changeAirportName(String oldName, String newName) {
        return airportDAO.changeAirportName(oldName, newName);
    }

    public int deleteAirportByName(String airportName) {
        return airportDAO.deleteAirportByName(airportName);
    }
}
