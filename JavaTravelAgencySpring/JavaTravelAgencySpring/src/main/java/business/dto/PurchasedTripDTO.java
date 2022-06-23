package business.dto;

import java.sql.Date;

public class PurchasedTripDTO {
    private Date dateOfPurchase;
    private double totalPrice;
    private double discount;
    private CustomerDTO customerDTO;
    private TripDTO tripDTO;

    public PurchasedTripDTO(Date dateOfPurchase, double totalPrice, double discount) {
        this.dateOfPurchase = dateOfPurchase;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

    public PurchasedTripDTO() {
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public TripDTO getTripDTO() {
        return tripDTO;
    }

    public void setTripDTO(TripDTO tripDTO) {
        this.tripDTO = tripDTO;
    }

    @Override
    public String toString() {
        return "PurchasedTripDTO{" +
                "dateOfPurchase=" + dateOfPurchase +
                ", totalPrice=" + totalPrice +
                ", discount=" + discount +
                ", customerDTO=" + customerDTO +
                ", tripDTO=" + tripDTO +
                '}';
    }
}
