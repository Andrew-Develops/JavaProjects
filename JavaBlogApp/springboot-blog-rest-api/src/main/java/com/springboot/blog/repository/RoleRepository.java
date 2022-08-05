package com.springboot.blog.repository;

import com.springboot.blog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    //In spate SpringDataJpa va auto crea un query folosind JPA Query API
    Optional<Role> findByName(String name);
}
