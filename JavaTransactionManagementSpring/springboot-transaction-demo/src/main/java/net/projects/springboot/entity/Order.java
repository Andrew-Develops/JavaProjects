package net.projects.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;
    @CreationTimestamp //de fiecare data cand salvam order hibernate va atribui o valoare automat
    private LocalDateTime dateCreated;
    @UpdateTimestamp // de fiecare data cand updatam order hibernate va atribui o valoare automata
    private LocalDateTime lastUpdated;
    private Long shoppingCartId;
}
