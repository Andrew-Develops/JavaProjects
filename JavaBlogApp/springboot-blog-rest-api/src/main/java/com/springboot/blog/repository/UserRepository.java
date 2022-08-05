package com.springboot.blog.repository;

import com.springboot.blog.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //In spate SpringDataJpa va auto crea un query folosind JPA Query API
    //Standardul este sa furnizezi mai intai tipul de actiune :find apoi dupa ce anume vrei sa cauti :ByEmail
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    //Verificam daca userul exista in DB
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
