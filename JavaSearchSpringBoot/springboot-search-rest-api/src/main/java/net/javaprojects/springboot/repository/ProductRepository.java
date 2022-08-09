package net.javaprojects.springboot.repository;

import net.javaprojects.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //SQL Query
    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:query, '%') OR p.description LIKE CONCAT ('%', :query, '%')")
    List<Product> searchProducts(String query);

    @Override
    @Transactional(timeout = 10)
    void deleteById(Long id);

//    //Native SQL Query
//    @Query(value = "SELECT * FROM products p WHERE p.name LIKE CONCAT('%',:query, '%') OR p.description LIKE CONCAT ('%', :query, '%')", nativeQuery = true)
//    List<Product> searchProductsSQL(String query);
}
