package com.registration.spring.persistence.repository;

import com.registration.spring.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//JpaRepository expune operatiile crud pe Entitatea User, al doilea argument este primary key din entitate
//adica acum putem face operatii crud pe entitatea User
public interface UserRepository extends JpaRepository<User, Long> {

    //cautam un user dupa email. findByEmail este o metoda din JpaRepository(Jpa creaza un query in functie de numele metodei)
    User findByEmail(String email);
}
