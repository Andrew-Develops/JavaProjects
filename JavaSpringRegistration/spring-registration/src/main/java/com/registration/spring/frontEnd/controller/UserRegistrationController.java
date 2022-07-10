package com.registration.spring.frontEnd.controller;

import com.registration.spring.business.dto.UserRegistrationDTO;
import com.registration.spring.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    //folosim acest obiect "user" in formularul nostru din registration.html
    //De fiecare data cand un user introduce un formular, acea data va fi stocata in "user", user care este de tipul UserDTO
    //Thymeleaf obtine un obiect "user" din aceasta metoda
    //Este un pas important
    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDto() {
        return new UserRegistrationDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    //cream un handler method care se va ocupa de requesturile http post
    //"user" contine datele unui formular si apoi legam acest formular de obiectul DTO: UserRegistrationDTO
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }


}
