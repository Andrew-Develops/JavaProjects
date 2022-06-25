package business.service;

import business.dto.CityDTO;
import business.dto.ContinentDTO;
import business.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.CityDAO;
import persistence.dao.ContinentDAO;
import persistence.dao.CountryDAO;
import persistence.entities.City;
import persistence.entities.Continent;
import persistence.entities.Country;

import java.util.LinkedList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentDAO continentDAO;

    //inseram un cityDTO in baza de date
    //noi primim din front end obiecte de tip dto si trebuie sa le convertim in entitati
    public void insertCityDTO(CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());
        setCountry(cityDTO, city);
        cityDAO.insertCity(city);
    }
    //introducem o tara pe un oras
    public void setCountry(CityDTO cityDTO, City city) {
        Country countryFound = countryDAO.findCountryByName(cityDTO.getCountryDTO().getName());
        if (countryFound == null) {
            Country country = new Country();
            country.setName(cityDTO.getCountryDTO().getName());
            setContinent(cityDTO, country);
            city.setCountry(country);
        } else {
            city.setCountry(countryFound);
        }
    }

    private void setContinent(CityDTO cityDTO, Country country) {
        //verificam daca continentul pe care il adaugam exista deja in baza de date
        Continent continentFound = continentDAO.findContinentByName(cityDTO.getCountryDTO().getContinentDTO().getName());
        if (continentFound == null) {
            //daca continentul nu exista cream un obiect Continent nou pe care setam numele continentului
            Continent continent = new Continent();
            continent.setName(cityDTO.getCountryDTO().getContinentDTO().getName());
            //introducem acel continent in in obiectul country
            country.setContinent(continent);
        } else {
            country.setContinent(continentFound);
        }
    }

    //cautam un oras dintr-o tara
    public List<CityDTO> findCitiesInCountry(String country) {
        List<CityDTO> cityDTOList = new LinkedList<>();
        List<City> city = cityDAO.findCityInCountry(country);
        for (City c : city) {
            CityDTO cityDTO = new CityDTO();
            CountryDTO countryDTO = new CountryDTO();
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(c.getName());
            countryDTO.setName(c.getName());
            countryDTO.setContinentDTO(continentDTO);
            cityDTO.setName(c.getName());
            cityDTO.setCountryDTO(countryDTO);

            cityDTOList.add(cityDTO);
        }
        return cityDTOList;
    }


    //cautam un oras dupa nume
    public CityDTO findCity(String name) {
        City city = cityDAO.findCityByName(name);
        if (city == null) {
            return null;
        }
        CityDTO cityDTO = new CityDTO();
        CountryDTO countryDTO = new CountryDTO();
        ContinentDTO continentDTO = new ContinentDTO();
        countryDTO.setName(city.getCountry().getName());
        continentDTO.setName(city.getCountry().getContinent().getName());
        countryDTO.setContinentDTO(continentDTO);
        cityDTO.setName(city.getName());
        cityDTO.setCountryDTO(countryDTO);

        return cityDTO;
    }

    //numaram cate instante cu acelasi nume sunt in baza de date
    public long countCity(String name) {
        return cityDAO.countCity(name);
    }

    //stergem un oras din baza de date
    public int deleteCity(String name) {
        return cityDAO.deleteCity(name);
    }

    //schimbam numele unui oras
    public int changeCityName(String name, String newName) {
        return cityDAO.changeCityName(name, newName);
    }

}
