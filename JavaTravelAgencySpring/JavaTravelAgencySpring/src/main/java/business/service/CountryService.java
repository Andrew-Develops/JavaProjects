package business.service;

import business.dto.ContinentDTO;
import business.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ContinentDAO;
import persistence.dao.CountryDAO;
import persistence.entities.Continent;
import persistence.entities.Country;

import java.util.LinkedList;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentDAO continentDAO;

    //setam numele tarii si continentul
    public void insertCountry(CountryDTO countryDTO) {
        Country country = new Country();
        country.setName(countryDTO.getName());
        setContinent(countryDTO, country);
        countryDAO.insertCountry(country);
    }

    //setam continentul pe Country
    public void setContinent(CountryDTO countryDTO, Country country) {
        //mai intai cautam continentul in baza de date
        //daca nu exista, cream un obiect continent, il punem pe country si apoi il trimitem in baza de date
        Continent continentFound = continentDAO.findContinentByName(countryDTO.getContinentDTO().getName());
        if (continentFound == null) {
            Continent continent = new Continent();
            continent.setName(countryDTO.getContinentDTO().getName());
            //punem continentul pe country acum
            country.setContinent(continent);
            //daca continentul exista in baza de date atunci ii setam doar numele
        } else {
            country.setContinent(continentFound);
        }
    }

    //cautam un country dupa nume
    public CountryDTO findCountryByName(String name) {
        Country country = countryDAO.findCountryByName(name);
        CountryDTO countryDTO = new CountryDTO();
        ContinentDTO continentDTO = new ContinentDTO();
        countryDTO.setName(country.getName());
        continentDTO.setName(country.getContinent().getName());
        countryDTO.setContinentDTO(continentDTO);

        return countryDTO;
    }

    //cautam country dupa continent
    public List<CountryDTO> findCountriesByContinent(String continent) {
        List<CountryDTO> countryDTOList = new LinkedList<>();
        List<Country> countryList = countryDAO.findCountriesInContinent(continent);
        for (Country c : countryList) {
            CountryDTO countryDTO = new CountryDTO();
            ContinentDTO continentDTO = new ContinentDTO();
            continentDTO.setName(c.getContinent().getName());
            countryDTO.setName(c.getName());
            countryDTO.setContinentDTO(continentDTO);
            countryDTOList.add(countryDTO);
        }
        return countryDTOList;
    }

    //verificam daca tara exista deja in baza de date
    public long countCountry(String name) {
        return countryDAO.countCountry(name);
    }

    //stergem o tara
    public int deleteCountry(String name) {
        return countryDAO.deleteCountry(name);
    }

    //schimbam numele unei tari
    public int changeCountryName(String oldName, String newName) {
        return countryDAO.changeCountryName(oldName, newName);
    }
}
