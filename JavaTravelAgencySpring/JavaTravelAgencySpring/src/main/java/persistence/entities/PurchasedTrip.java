package persistence.entities;

import javax.persistence.*;
import java.sql.Date;
@NamedQueries({
        @NamedQuery(name = "findPurchasedTripsByCustomer",query = "select p from PurchasedTrip p inner join p.customer customer where name= :name"),
        @NamedQuery(name = "findPurchasedTripsByDate",query = "select p from PurchasedTrip p where (dateOfPurchase between :dateOfPurchase and :currentDate) ")
})
@Entity
@Table(name = "purchased_trips")
public class PurchasedTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_of_purchase")
    private Date dateOfPurchase;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "discount")
    private double discount;

    @ManyToOne
    @JoinColumn(name = "customers_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "trips_id")
    private Trip trip;

    public PurchasedTrip(Date dateOfPurchase, double totalPrice, double discount) {
        this.dateOfPurchase = dateOfPurchase;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

    public PurchasedTrip() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    @Override
    public String toString(){
        return "Purchased date: " + dateOfPurchase + " Total price: " + totalPrice + " Discount: " + discount;
    }
}
