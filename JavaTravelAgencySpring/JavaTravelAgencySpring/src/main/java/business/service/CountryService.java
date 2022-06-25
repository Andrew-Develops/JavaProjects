package business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.CountryDAO;
@Service
public class CountryService {

    @Autowired
    CountryDAO countryDAO;

    public long countCountry(String name){
        return countryDAO.countCountry(name);
    }
}
