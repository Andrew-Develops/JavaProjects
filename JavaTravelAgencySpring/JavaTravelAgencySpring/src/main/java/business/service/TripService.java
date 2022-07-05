package business.service;

import business.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.*;
import persistence.entities.*;


import java.util.*;

@Service
public class TripService {

    @Autowired
    TripDAO tripDAO;
    @Autowired
    HotelDAO hotelDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentDAO continentDAO;
    @Autowired
    AirportDAO airportDAO;
    @Autowired
    FlightDAO flightDAO;


    //metoda care ne introduce un trip in baza de date
    public void insertTrip(TripDTO tripDTO) {
        Continent departureContinent = setContinent(tripDTO.getDepartureFlightDTO().getDepartureAirport().getCityDTO().getCountryDTO().getContinentDTO());
        Continent arrivingContinent = setContinent(tripDTO.getDepartureFlightDTO().getArrivingAirport().getCityDTO().getCountryDTO().getContinentDTO());
        Continent returningDepartureContinent = setReturningContinent(arrivingContinent, tripDTO.getReturningFlightDTO().getDepartureAirport());
        Continent returningArrivingContinent = setReturningContinent(departureContinent, tripDTO.getReturningFlightDTO().getArrivingAirport());

        Country departureCountry = setCountryDepartureFlight(departureContinent, tripDTO.getDepartureFlightDTO().getDepartureAirport());
        Country arrivingCountry = setCountryDepartureFlight(arrivingContinent, tripDTO.getDepartureFlightDTO().getArrivingAirport());
        Country returningDepartureCountry = setCountryReturningFlight(arrivingContinent, returningDepartureContinent, arrivingCountry, tripDTO.getReturningFlightDTO().getDepartureAirport());
        Country returningArrivingCountry = setCountryReturningFlight(departureContinent, returningArrivingContinent, departureCountry, tripDTO.getReturningFlightDTO().getArrivingAirport());

        City departureCity = setDepartureCity(departureCountry, tripDTO.getDepartureFlightDTO().getDepartureAirport());
        City arrivingCity = setDepartureCity(arrivingCountry, tripDTO.getDepartureFlightDTO().getArrivingAirport());
        City returningDepartureCity = setCityReturningFlight(arrivingCountry, returningDepartureCountry, arrivingCity, tripDTO.getReturningFlightDTO().getDepartureAirport());
        City returningArrivingCity = setCityReturningFlight(departureCountry, returningArrivingCountry, departureCity, tripDTO.getReturningFlightDTO().getArrivingAirport());

        Airport departureAirport = setDepartureAirport(departureCity, tripDTO.getDepartureFlightDTO().getDepartureAirport());
        Airport arrivingAirport = setDepartureAirport(arrivingCity, tripDTO.getDepartureFlightDTO().getArrivingAirport());
        Airport returningDepartureAirport = setReturningAirport(arrivingCity, returningDepartureCity, arrivingAirport, tripDTO.getReturningFlightDTO().getDepartureAirport());
        Airport returningArrivingAirport = setReturningAirport(departureCity, returningArrivingCity, departureAirport, tripDTO.getReturningFlightDTO().getArrivingAirport());

        Flight departureFlight = setDepartureFlight(tripDTO, departureAirport, arrivingAirport);
        Flight returningFlight = setReturningFlight(tripDTO, departureAirport, arrivingAirport, returningDepartureAirport, returningArrivingAirport);

        Hotel stayingHotel = setHotel(tripDTO, departureFlight, arrivingCity);

        Trip trip = setTrip(tripDTO, departureFlight, returningFlight, stayingHotel);

        tripDAO.insertTrip(trip);
    }

    //am convertit un obiect TripDTO intr-un obiect Trip pentru a-l pune in baza de date
    public Trip setTrip(TripDTO tripDTO, Flight departureFlight, Flight returningFlight, Hotel stayingHotel) {
        Trip trip = new Trip();
        trip.setName(tripDTO.getName());
        trip.setDepartureFlight(departureFlight);
        trip.setReturningFlight(returningFlight);
        trip.setStayingHotel(stayingHotel);
        trip.setMealType(tripDTO.getMealType());
        trip.setDepartureDate(tripDTO.getDepartureDate());
        trip.setReturnDate(tripDTO.getReturnDate());
        trip.setNumberOfDays(tripDTO.getNumberOfDays());
        trip.setPriceForAdult(tripDTO.getPriceForAdult());
        trip.setPriceForChild(tripDTO.getPriceForChild());
        trip.setPromoted(tripDTO.isPromoted());
        trip.setNumberOfTripsAvailable(tripDTO.getNumberOfTripsAvailable());
        return trip;
    }

    //am creat obiectul hotel care il punem in trip
    public Hotel setHotel(TripDTO tripDTO, Flight departureFlight, City arrivingCity) {
        City city = new City();
        Hotel hotel = hotelDAO.findHotelByAddress(tripDTO.getStayingHotelDTO().getAddress());
        if (hotel == null) {
            hotel = new Hotel();
            hotel.setName(tripDTO.getStayingHotelDTO().getName());
            if (tripDTO.getStayingHotelDTO().getCityDTO().getName().equalsIgnoreCase(departureFlight.getArrivingAirport().getCity().getName())) {
                city = arrivingCity;
            } else {
                city = setCity(tripDTO.getStayingHotelDTO().getCityDTO());
                if (city.getCountry() == null) {
                    Country country = setCountry(tripDTO.getStayingHotelDTO().getCityDTO().getCountryDTO());
                    if (country.getContinent() == null) {
                        Continent continent = setContinent(tripDTO.getStayingHotelDTO().getCityDTO().getCountryDTO().getContinentDTO());
                        country.setContinent(continent);
                    }
                }
            }
            hotel.setCity(city);
            hotel.setAddress(tripDTO.getStayingHotelDTO().getAddress());
            hotel.setDescription(tripDTO.getStayingHotelDTO().getDescription());
            hotel.setNumberOfStars(tripDTO.getStayingHotelDTO().getNumberOfStars());
            //am introdus setul de camere pe hotel
            setHotelRooms(tripDTO, hotel);
        }
        return hotel;
    }

    //am pus camerele intr-un set
    private void setHotelRooms(TripDTO tripDTO, Hotel hotel) {
        Set<Room> roomSet = new HashSet<>();
        for (RoomDTO r : tripDTO.getStayingHotelDTO().getRoomDTOSet()) {
            Room room = new Room();
            room.setType(r.getType());
            room.setNumberOfRooms(r.getNumberOfRooms());
            room.setExtraBed(r.isExtraBed());
            room.setPrice(r.getPrice());
            room.setRoomsAvailable(r.getRoomsAvailable());
            roomSet.add(room);
        }
        hotel.setRoomSet(roomSet);
    }

    //folosim metoda pentru a crea un obiect City pe care il folosim in DepartureCity si ReturningCity
    private City setCity(CityDTO cityDTO) {
        City city = cityDAO.findCityByName(cityDTO.getName());
        if (city == null) {
            city = new City();
            city.setName(cityDTO.getName());
        }
        return city;
    }

    //setam care este orasul la plecare
    private City setDepartureCity(Country departureCountry, AirportDTO departureFlightAirport) {
        City departureCity = setCity(departureFlightAirport.getCityDTO());
        if (departureCity.getCountry() == null) {
            departureCity.setCountry(departureCountry);
        }
        return departureCity;
    }

    //setam care este orasul la intoarcere
    private City setCityReturningFlight(Country arrivingCountry, Country returningDepartureCountry, City arrivingCity, AirportDTO departureFlightAirport) {
        City returningDepartureCity = null;
        if (!departureFlightAirport.getCityDTO().getName().equalsIgnoreCase(arrivingCity.getName())) {
            returningDepartureCity = setCity(departureFlightAirport.getCityDTO());
            if (returningDepartureCountry == null) {
                returningDepartureCity.setCountry(arrivingCountry);
            } else {
                returningDepartureCity.setCountry(returningDepartureCountry);
            }
        }
        return returningDepartureCity;
    }


    //Cream un obiect Country cara este folosit la crearea DepartureCountry si ReturningCountry
    private Country setCountry(CountryDTO countryDTO) {
        Country country = countryDAO.findCountryByName(countryDTO.getName());
        if (country == null) {
            country = new Country();
            country.setName(countryDTO.getName());
        }
        return country;
    }

    //setam tara la plecare
    private Country setCountryDepartureFlight(Continent departureContinent, AirportDTO departureAirport) {
        Country departureCountry = setCountry(departureAirport.getCityDTO().getCountryDTO());
        if (departureCountry.getContinent() == null) {
            departureCountry.setContinent(departureContinent);
        }
        return departureCountry;
    }

    //setam tara la intoarcere
    private Country setCountryReturningFlight(Continent arrivingContinent, Continent returningDepartureContinent, Country arrivingCountry, AirportDTO departureAirport) {
        Country returningDepartureCountry = null;
        if (!arrivingCountry.getName().equalsIgnoreCase(departureAirport.getCityDTO().getCountryDTO().getName())) {
            returningDepartureCountry = setCountry(departureAirport.getCityDTO().getCountryDTO());
            if (returningDepartureCountry.getContinent() == null) {
                returningDepartureCountry.setContinent(arrivingContinent);
            } else {
                returningDepartureCountry.setContinent(returningDepartureContinent);
            }
        }
        return returningDepartureCountry;
    }

    //setam un continent
    private Continent setContinent(ContinentDTO continentDTO) {
        Continent continent = continentDAO.findContinentByName(continentDTO.getName());
        if (continent == null) {
            continent = new Continent();
            continent.setName(continentDTO.getName());
        }
        return continent;
    }

    //setam care este Continentul la intoarcere
    private Continent setReturningContinent(Continent arrivingContinent, AirportDTO departureAirport) {
        Continent returningDepartureContinent = null;
        //daca continentul nu este egal cu aeroportul de aterizare atunci il setam noi
        if (!arrivingContinent.getName().equalsIgnoreCase(departureAirport.getCityDTO().getCountryDTO().getContinentDTO().getName())) {
            returningDepartureContinent = setContinent(departureAirport.getCityDTO().getCountryDTO().getContinentDTO());
        }
        return returningDepartureContinent;
    }

    //Cream un obiect de tip Airport si il folosind cand setam DepartureAirport si ReturningAirport
    private Airport setAirport(AirportDTO airportDTO) {
        Airport airport = airportDAO.findAirportByName(airportDTO.getName());
        if (airport == null) {
            airport = new Airport();
            airport.setName(airportDTO.getName());
        }
        return airport;
    }

    //setam aeroportul la plecare
    private Airport setDepartureAirport(City departureCity, AirportDTO departureFlightAirport) {
        Airport departureAirport = setAirport(departureFlightAirport);
        if (departureAirport.getCity() == null) {
            departureAirport.setCity(departureCity);
        }
        return departureAirport;
    }

    //setam aeroportul la venire
    private Airport setReturningAirport(City arrivingCity, City returningDepartureCity, Airport arrivingAirport, AirportDTO departureFlightAirport) {
        Airport returningDepartureAirport = null;
        if (!arrivingAirport.getName().equalsIgnoreCase(departureFlightAirport.getName())) {
            returningDepartureAirport = setAirport(departureFlightAirport);
            if (returningDepartureAirport.getCity() == null) {
                returningDepartureAirport.setName(departureFlightAirport.getName());
                if (returningDepartureCity != null) {
                    returningDepartureAirport.setCity(returningDepartureCity);
                } else {
                    returningDepartureAirport.setCity(arrivingCity);
                }
            }
        }
        return returningDepartureAirport;
    }

    //Cream un obiect Flight si il folosim atunci cand cream DepartureFlight si ReturningFlight
    private Flight setFlight(FlightDTO flightDTO) {
        Flight flight = flightDAO.findFlightByFlightNumber(flightDTO.getFlightNumber());
        if (flight == null) {
            flight = new Flight();
            flight.setFlightNumber(flightDTO.getFlightNumber());
            flight.setSeatsAvailable(flightDTO.getSeatsAvailable());
            flight.setPrice(flightDTO.getPrice());
            flight.setDepartureDate(flightDTO.getDepartureDate());
            flight.setArrivingDate(flightDTO.getArrivingDate());
            flight.setDepartureTime(flightDTO.getDepartureTime());
            flight.setArrivingTime(flightDTO.getArrivingTime());
        }
        return flight;
    }

    //setam avionul care pleaca
    private Flight setDepartureFlight(TripDTO tripDTO, Airport departureAirport, Airport arrivingAirport) {
        Flight departureFlight = setFlight(tripDTO.getDepartureFlightDTO());
        if (departureFlight.getDepartureAirport() == null) {
            departureFlight.setDepartureAirport(departureAirport);
        }
        if (departureFlight.getArrivingAirport() == null) {
            departureFlight.setArrivingAirport(arrivingAirport);
        }
        return departureFlight;
    }

    //setam avionul care se intoarce
    private Flight setReturningFlight(TripDTO tripDTO, Airport departureAirport, Airport arrivingAirport, Airport returningDepartureAirport, Airport returningArrivingAirport) {
        Flight returningFlight = setFlight(tripDTO.getReturningFlightDTO());
        if (returningFlight.getDepartureAirport() == null) {
            if (returningDepartureAirport == null) {
                returningFlight.setDepartureAirport(arrivingAirport);
            } else {
                returningFlight.setDepartureAirport(returningDepartureAirport);
            }
        }

        if (returningFlight.getArrivingAirport() == null) {
            if (returningArrivingAirport == null) {
                returningFlight.setArrivingAirport(departureAirport);
            } else {
                returningFlight.setArrivingAirport(returningArrivingAirport);
            }
        }
        return returningFlight;
    }

    //verificam cate instante cu acelasi nume si Departure Date sunt in baza de date
    public long countTrips(String name, Date date) {
        return tripDAO.countTripByNameAndDepartureDate(name, date);
    }

    //stergem o excursie dupa nume
    public int deleteTripByName(String name) {
        return tripDAO.deleteTripByName(name);
    }

    public TripDTO findTripByName(String name) {
        Trip trip = tripDAO.findTripByName(name);
        if (trip == null) {
            return null;
        }
        return getTripDTO(trip);
    }

    //afisam toate excursiile
    public List<TripDTO> findAllTrips() {
        List<Trip> tripList = tripDAO.findAllTrips();
        List<TripDTO> tripDTOList = getTripDTOList(tripList);
        return tripDTOList;
    }

    //afisam excursiile care sunt la promotie
    public List<TripDTO> findPromotedTrips(boolean promoted) {
        List<Trip> tripList = tripDAO.findPromotedTrips(promoted);
        List<TripDTO> tripDTOList = getTripDTOList(tripList);
        return tripDTOList;
    }

    //afisam excursiile dupa nume
    public List<TripDTO> findTripsByName(String name) {
        List<Trip> tripList = tripDAO.findTripsByName(name);
        List<TripDTO> tripDTOList = getTripDTOList(tripList);
        return tripDTOList;
    }

    //afisam excursiile dupa data plecarii
    public List<TripDTO> findTripsByDepartureDate(Date departureDate) {
        List<Trip> tripList = tripDAO.findTripByDepartureDate(departureDate);
        List<TripDTO> tripDTOList = getTripDTOList(tripList);
        return tripDTOList;
    }

    //afisam excursiile dupa tipul mancarii (All-Inclusive, Breakfast, Nothing)
    public List<TripDTO> findTripByMealType(String mealType) {
        List<Trip> tripList = tripDAO.findByMealType(mealType);
        List<TripDTO> tripDTOList = getTripDTOList(tripList);
        return tripDTOList;
    }

    //folosim metoda pentru a pune obiectele TripDTO intr-o lista
    private List<TripDTO> getTripDTOList(List<Trip> tripList) {
        List<TripDTO> tripDTOList = new LinkedList<>();
        for (Trip trip : tripList) {
            tripDTOList.add(getTripDTO(trip));
        }
        return tripDTOList;
    }

    //am convertit un obiect Trip intr-un obiect de tip TripDTO
    private TripDTO getTripDTO(Trip trip) {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setName(trip.getName());
        tripDTO.setDepartureDate(trip.getDepartureDate());
        tripDTO.setDepartureFlightDTO(setDepartureFlightDTO(trip.getDepartureFlight()));
        tripDTO.setReturnDate(trip.getReturnDate());
        tripDTO.setReturningFlightDTO(setReturningFlightDTO(trip.getReturningFlight()));
        tripDTO.setMealType(trip.getMealType());
        tripDTO.setStayingHotelDTO(setStayingHotel(trip.getStayingHotel()));
        tripDTO.setNumberOfDays(trip.getNumberOfDays());
        tripDTO.setPriceForAdult(trip.getPriceForAdult());
        tripDTO.setPriceForChild(trip.getPriceForChild());
        tripDTO.setNumberOfTripsAvailable(trip.getNumberOfTripsAvailable());
        tripDTO.setPromoted(trip.isPromoted());
        return tripDTO;
    }


    //setam Departure Airport DTO si il folosim cand cream obiectul FlightDTO
    private void setDepartureAirportDTO(FlightDTO departureFlightDTO, Airport departureAirport) {
        AirportDTO departureAirportDTO = new AirportDTO();
        departureAirportDTO.setName(departureAirport.getName());
        ContinentDTO departureContinentDTO = new ContinentDTO(departureAirport.getCity().getCountry().getContinent().getName());
        CountryDTO departureCountryDTO = new CountryDTO(departureAirport.getCity().getCountry().getName());
        departureCountryDTO.setContinentDTO(departureContinentDTO);
        CityDTO departureCityDTO = new CityDTO(departureAirport.getCity().getName());
        departureCityDTO.setCountryDTO(departureCountryDTO);
        departureAirportDTO.setCityDTO(departureCityDTO);
        departureFlightDTO.setDepartureAirport(departureAirportDTO);
    }

    //setam Arriving Airport DTO si il folosim cand cream obiectul FlightDTO
    private void setArrivingAirportDTO(Flight departureFlight, FlightDTO departureFlightDTO) {
        Airport arrivingAirport = departureFlight.getArrivingAirport();
        AirportDTO arrivingAirportDTO = new AirportDTO();
        arrivingAirportDTO.setName(arrivingAirport.getName());
        ContinentDTO arrivingContinentDTO = new ContinentDTO(arrivingAirport.getCity().getCountry().getContinent().getName());
        CountryDTO arrivingCountryDTO = new CountryDTO(arrivingAirport.getCity().getCountry().getName());
        arrivingCountryDTO.setContinentDTO(arrivingContinentDTO);
        CityDTO arrivingCityDTO = new CityDTO(arrivingAirport.getCity().getName());
        arrivingCityDTO.setCountryDTO(arrivingCountryDTO);
        arrivingAirportDTO.setCityDTO(arrivingCityDTO);
        departureFlightDTO.setArrivingAirport(arrivingAirportDTO);
    }

    //convertim un Hotel intr-un HotelDTO
    private HotelDTO setStayingHotel(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setNumberOfStars(hotel.getNumberOfStars());
        hotelDTO.setDescription(hotel.getDescription());
        ContinentDTO continentDTO = new ContinentDTO(hotel.getCity().getCountry().getContinent().getName());
        CountryDTO countryDTO = new CountryDTO(hotel.getCity().getCountry().getName());
        countryDTO.setContinentDTO(continentDTO);
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName(hotel.getCity().getName());
        cityDTO.setCountryDTO(countryDTO);
        hotelDTO.setCityDTO(cityDTO);

        hotelDTO.setRoomDTOSet(getRoomDTOS(hotel));
        return hotelDTO;
    }

    //Setam un set de camere pe hotel
    private Set<RoomDTO> getRoomDTOS(Hotel hotel) {
        Set<RoomDTO> roomDTOSet = new HashSet<>();
        for (Room room : hotel.getRoomSet()) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setNumberOfRooms(room.getNumberOfRooms());
            roomDTO.setType(room.getType());
            roomDTO.setRoomsAvailable(room.getRoomsAvailable());
            roomDTO.setExtraBed(room.isExtraBed());
            roomDTO.setPrice(room.getPrice());
            roomDTOSet.add(roomDTO);
        }
        return roomDTOSet;
    }

    //convertim un obiect Flight intr-un obiect FlightDTO
    private FlightDTO setDepartureFlightDTO(Flight departureFlight) {
        FlightDTO departureFlightDTO = new FlightDTO();
        departureFlightDTO.setFlightNumber(departureFlight.getFlightNumber());
        departureFlightDTO.setSeatsAvailable(departureFlight.getSeatsAvailable());
        departureFlightDTO.setPrice(departureFlight.getPrice());
        departureFlightDTO.setDepartureDate(departureFlight.getDepartureDate());
        departureFlightDTO.setDepartureTime(departureFlight.getDepartureTime());
        departureFlightDTO.setArrivingDate(departureFlight.getArrivingDate());
        departureFlightDTO.setArrivingTime(departureFlight.getArrivingTime());

        setDepartureAirportDTO(departureFlightDTO, departureFlight.getDepartureAirport());
        setArrivingAirportDTO(departureFlight, departureFlightDTO);
        return departureFlightDTO;
    }

    //convertim un obiect Flight intr-un obiect FlightDTO
    private FlightDTO setReturningFlightDTO(Flight returningFlight) {
        FlightDTO returningFlightDTO = new FlightDTO();
        returningFlightDTO.setFlightNumber(returningFlight.getFlightNumber());
        returningFlightDTO.setSeatsAvailable(returningFlight.getSeatsAvailable());
        returningFlightDTO.setPrice(returningFlight.getPrice());
        returningFlightDTO.setDepartureDate(returningFlight.getDepartureDate());
        returningFlightDTO.setDepartureTime(returningFlight.getDepartureTime());
        returningFlightDTO.setArrivingDate(returningFlight.getArrivingDate());
        returningFlightDTO.setArrivingTime(returningFlight.getArrivingTime());

        setDepartureAirportDTO(returningFlightDTO, returningFlight.getDepartureAirport());
        setArrivingAirportDTO(returningFlight, returningFlightDTO);
        return returningFlightDTO;
    }

    public boolean checkAvailability(TripDTO tripDTO) {
        if (tripDTO.getNumberOfTripsAvailable() > 0) {
            return true;
        } else {
            return false;
        }
    }


}
