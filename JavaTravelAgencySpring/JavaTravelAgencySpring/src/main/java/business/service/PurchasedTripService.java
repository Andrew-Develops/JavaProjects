package business.service;

import business.dto.CustomerDTO;
import business.dto.PurchasedTripDTO;
import business.dto.TripDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.CustomerDAO;
import persistence.dao.PurchasedTripDAO;
import persistence.dao.TripDAO;
import persistence.entities.PurchasedTrip;
import persistence.entities.Trip;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class PurchasedTripService {

    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    TripDAO tripDAO;
    @Autowired
    CustomerService customerService;
    @Autowired
    TripService tripService;
    @Autowired
    PurchasedTripDAO purchasedTripDAO;

    public void insertPurchasedTrip(PurchasedTripDTO purchasedTripDTO) {
        PurchasedTrip purchasedTrip = new PurchasedTrip();
        purchasedTrip.setCustomer(customerDAO.findCustomerByEmail(purchasedTripDTO.getCustomerDTO().getEmail()));
        purchasedTrip.setTrip(tripDAO.findTripByName(purchasedTripDTO.getTripDTO().getName()));

        //am creat un obiect care retine data actuala si l-am introdus in purchasedTrip
        Calendar calendar = Calendar.getInstance();
        java.util.Date dateUtil = calendar.getTime();
        java.sql.Date dateOfPurchased = new Date(dateUtil.getTime());
        purchasedTrip.setDateOfPurchase(dateOfPurchased);

        purchasedTrip.setTotalPrice(calculatePrice(purchasedTripDTO, purchasedTrip.getTrip()));
        purchasedTripDAO.insertPurchasedTrip(purchasedTrip);

    }

    private double calculatePrice(PurchasedTripDTO purchasedTripDTO, Trip trip) {
        double totalPriceForFlight = (trip.getDepartureFlight().getPrice() * (purchasedTripDTO.getTripDTO().getNumberOfAdults() + purchasedTripDTO.getTripDTO().getNumberOfChildren())) +
                (trip.getReturningFlight().getPrice() * (purchasedTripDTO.getTripDTO().getNumberOfAdults() + purchasedTripDTO.getTripDTO().getNumberOfChildren()));
        double totalPrice = purchasedTripDTO.getTotalPrice() + ((trip.getPriceForAdult() * purchasedTripDTO.getTripDTO().getNumberOfAdults()) + (trip.getPriceForChild() * purchasedTripDTO.getTripDTO().getNumberOfChildren()))
                + totalPriceForFlight;
        return totalPrice;
    }

    public List<PurchasedTripDTO> showPurchasedTrips(String customerName) {
        List<PurchasedTripDTO> purchasedTripDTOS = new LinkedList<>();
        List<PurchasedTrip> purchasedTripList = purchasedTripDAO.findPurchasedTripByCustomer(customerName);

        for (PurchasedTrip p : purchasedTripList) {
            PurchasedTripDTO purchasedTripDTO = new PurchasedTripDTO();
            TripDTO tripDTO = tripService.findTripByName(p.getTrip().getName());
            CustomerDTO customerDTO = customerService.findCustomerByEmail(p.getCustomer().getEmail());
            purchasedTripDTO.setDateOfPurchase(p.getDateOfPurchase());
            purchasedTripDTO.setTotalPrice(p.getTotalPrice());
            purchasedTripDTO.setDiscount(p.getDiscount());
            purchasedTripDTO.setTripDTO(tripDTO);
            purchasedTripDTO.setCustomerDTO(customerDTO);
            purchasedTripDTOS.add(purchasedTripDTO);
        }
        return purchasedTripDTOS;
    }


}
