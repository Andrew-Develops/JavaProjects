package com.registration.spring.frontEnd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //aceasta metoda returneaza un login page(de tipul thymeleaf template)
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(){
        //"index" este un thymeleaf template
        return "index";
    }




}
