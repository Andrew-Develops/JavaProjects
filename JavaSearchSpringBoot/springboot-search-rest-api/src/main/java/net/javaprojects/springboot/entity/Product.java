package net.javaprojects.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String name;
    private String description;
    private boolean active;
    private String imageUrl;
    @CreationTimestamp  //de fiecare data cand salvam un produs hibernate va atribui o valoare automat
    private LocalDateTime dateCreated;
    @UpdateTimestamp    // de fiecare data cand updatam un produs hibernate va atribui o valoare automata
    private LocalDateTime dateUpdated;
}
