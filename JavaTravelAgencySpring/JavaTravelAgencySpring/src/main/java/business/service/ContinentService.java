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

    public long countContinent(String name) {
        return continentDAO.countContinent(name);
    }

    public int deleteContinent(String name) {
        return continentDAO.deleteContinent(name);
    }

    public ContinentDTO findContinentByName(String name) {
        ContinentDTO continentDTO = new ContinentDTO();
        Continent continent = continentDAO.findContinentByName(name);
        continentDTO.setName(continent.getName());

        return continentDTO;
    }

}
