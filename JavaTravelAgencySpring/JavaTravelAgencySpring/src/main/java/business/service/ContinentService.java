package business.service;

import business.dto.ContinentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ContinentDAO;
import persistence.entities.Continent;

@Service
public class ContinentService {

    @Autowired
    ContinentDAO continentDAO;

    //inseram un continent
    public void insertContinent(ContinentDTO continentDTO) {
        Continent continent = new Continent();
        continent.setName(continentDTO.getName());
        continentDAO.insertContinent(continent);
    }

    //verificam daca mai este o instanta cu acelasi nume in baza de date
    public long countContinent(String name) {
        return continentDAO.countContinent(name);
    }

    //stergem un continent
    public int deleteContinent(String name) {
        return continentDAO.deleteContinent(name);
    }

    //cautam un continent dupa nume
    public ContinentDTO findContinentByName(String name) {
        ContinentDTO continentDTO = new ContinentDTO();
        Continent continent = continentDAO.findContinentByName(name);
        continentDTO.setName(continent.getName());

        return continentDTO;
    }

}
