package business.service;

import business.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.CityDAO;
import persistence.dao.ContinentDAO;
import persistence.dao.CountryDAO;
import persistence.dao.HotelDAO;
import persistence.entities.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class HotelService {

    @Autowired
    ContinentDAO continentDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    HotelDAO hotelDAO;

    public void insertHotelDTO(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setNumberOfStars(hotelDTO.getNumberOfStars());
        hotel.setDescription(hotelDTO.getDescription());
        setCity(hotelDTO, hotel);
        setRoomsInHotel(hotelDTO, hotel);
        hotelDAO.insertHotel(hotel);
    }


    public void setRoomsInHotel(HotelDTO hotelDTO, Hotel hotel) {
        Set<Room> roomSet = new HashSet<>();
        for (RoomDTO r : hotelDTO.getRoomDTOSet()) {
            Room room = new Room();
            room.setType(r.getType());
            room.setNumberOfRooms(r.getNumberOfRooms());
            room.setExtraBed(r.isExtraBed());
            room.setRoomsAvailable(r.getRoomsAvailable());
            room.setPrice(r.getPrice());
            roomSet.add(room);
        }
        hotel.setRoomSet(roomSet);
    }

    public void setRoomsInHotelDTO(HotelDTO hotelDTO, Hotel hotel) {
        Set<RoomDTO> roomDTOSet = new HashSet<>();
        for (Room r : hotel.getRoomSet()) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setType(r.getType());
            roomDTO.setNumberOfRooms(r.getNumberOfRooms());
            roomDTO.setExtraBed(r.isExtraBed());
            roomDTO.setRoomsAvailable(r.getRoomsAvailable());
            roomDTO.setPrice(r.getPrice());
            roomDTOSet.add(roomDTO);
        }
        hotelDTO.setRoomDTOSet(roomDTOSet);
    }


    //setam oras pe hotel
    public void setCity(HotelDTO hotelDTO, Hotel hotel) {
        City cityFound = cityDAO.findCityByName(hotelDTO.getCityDTO().getName());
        if (cityFound != null) {
            hotel.setCity(cityFound);
        } else {
            City city = new City();
            city.setName(hotelDTO.getCityDTO().getName());
            setCountry(hotelDTO, city);
            hotel.setCity(city);
        }
    }

    //setam city pe oras
    public void setCountry(HotelDTO hotelDTO, City city) {
        //mai intai cautam in baza de date daca acea tara exista, daca exista atunci il punem pe country prin .setCountry()
        //altfel trebuie sa il cream de la 0
        Country countryFound = countryDAO.findCountryByName(hotelDTO.getCityDTO().getCountryDTO().getName());
        if (countryFound != null) {
            city.setCountry(countryFound);
        } else {
            Country country = new Country(hotelDTO.getCityDTO().getCountryDTO().getName());
            setContinent(hotelDTO, country);
            city.setCountry(country);
        }
    }

    //setam continentul pe city
    public void setContinent(HotelDTO hotelDTO, Country country) {
        //mai intai cautam in baza de date daca acel continent exista, daca exista atunci il punem pe country prin .setContinent()
        //altfel trebuie sa il cream de la 0
        Continent continentFound = continentDAO.findContinentByName(hotelDTO.getCityDTO().getCountryDTO().getContinentDTO().getName());
        if (continentFound != null) {
            country.setContinent(continentFound);
        } else {
            Continent continent = new Continent(hotelDTO.getCityDTO().getCountryDTO().getContinentDTO().getName());
            country.setContinent(continent);
        }
    }

    //cautam hotelul dupa nume
    public HotelDTO findHotelByAddress(String address) {
        Hotel hotel = hotelDAO.findHotelByAddress(address);
        if (hotel == null) {
            return null;
        }
        HotelDTO hotelDTO = new HotelDTO();
        CityDTO cityDTO = new CityDTO();
        CountryDTO countryDTO = new CountryDTO();
        ContinentDTO continentDTO = new ContinentDTO();
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setNumberOfStars(hotel.getNumberOfStars());
        hotelDTO.setDescription(hotel.getDescription());
        //setam camera pe hotel
        setRoomsInHotelDTO(hotelDTO, hotel);
        continentDTO.setName(hotel.getCity().getCountry().getContinent().getName());
        //setam continentul pe tara
        countryDTO.setName(hotel.getCity().getCountry().getName());
        countryDTO.setContinentDTO(continentDTO);
        //setam tara pe oras
        cityDTO.setName(hotel.getCity().getName());
        cityDTO.setCountryDTO(countryDTO);
        //setam orasul pe hotel
        hotelDTO.setCityDTO(cityDTO);
        return hotelDTO;

    }

    //cautam un hotel dupa nume
    public List<HotelDTO> findHotelByName(String hotelName) {
        List<HotelDTO> hotelDTOList = new LinkedList<>();
        List<Hotel> hotelList = hotelDAO.findHotelByName(hotelName);
        if (hotelList.isEmpty()) {
            return null;
        }
        for (Hotel h : hotelList) {
            HotelDTO hotelDTO = new HotelDTO();
            CityDTO cityDTO = new CityDTO();
            CountryDTO countryDTO = new CountryDTO();
            hotelDTO.setName(h.getName());
            hotelDTO.setAddress(h.getAddress());
            hotelDTO.setNumberOfStars(h.getNumberOfStars());
            hotelDTO.setDescription(h.getDescription());
            setRoomsInHotelDTO(hotelDTO, h);
            countryDTO.setName(h.getCity().getCountry().getName());
            cityDTO.setName(h.getCity().getName());
            cityDTO.setCountryDTO(countryDTO);
            hotelDTO.setCityDTO(cityDTO);
            hotelDTOList.add(hotelDTO);
        }
        return hotelDTOList;
    }

    public List<HotelDTO> findHotelInCity(String cityName) {
        List<HotelDTO> hotelDTOList = new LinkedList<>();
        List<Hotel> hotelList = hotelDAO.findHotelByCity(cityName);
        for (Hotel h : hotelList) {
            HotelDTO hotelDTO = new HotelDTO();
            CityDTO cityDTO = new CityDTO();
            CountryDTO countryDTO = new CountryDTO();
            //setam continentul
            ContinentDTO continentDTO = new ContinentDTO(h.getCity().getCountry().getContinent().getName());
            hotelDTO.setName(h.getName());
            hotelDTO.setAddress(h.getAddress());
            hotelDTO.setNumberOfStars(h.getNumberOfStars());
            hotelDTO.setDescription(h.getDescription());
            //setam camera pe hotel
            setRoomsInHotelDTO(hotelDTO, h);
            //setam tara pe continent
            countryDTO.setName(h.getCity().getCountry().getName());
            countryDTO.setContinentDTO(continentDTO);
            //setam orasul pe tara
            cityDTO.setName(h.getCity().getName());
            cityDTO.setCountryDTO(countryDTO);
            //setam orasul pe hotel
            hotelDTO.setCityDTO(cityDTO);
            hotelDTOList.add(hotelDTO);
        }
        return hotelDTOList;
    }

    public long countHotelByName(String name) {
        return hotelDAO.countHotel(name);
    }

    public int deleteHotelByName(String name) {
        return hotelDAO.deleteHotel(name);
    }

    public List<String> countHotelByAddress(String address) {
        return hotelDAO.countHotelByAddress(address);
    }

    public int changeHotelName(String oldName, String newName) {
        return hotelDAO.changeHotelName(oldName, newName);
    }
}
