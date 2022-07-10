package com.registration.spring.business.service;

import com.registration.spring.persistence.entities.User;
import com.registration.spring.business.dto.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

//trebuie sa extindem interfata UserDetailsService pentru DaoAuthenticationProvider
public interface UserService extends UserDetailsService {
    //aceasta este o metoda care va salva un user
    User save(UserRegistrationDTO registrationDto);

}
