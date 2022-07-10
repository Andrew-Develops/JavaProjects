package com.registration.spring.business.dto;

public class UserRegistrationDTO {

    //folosim obiecte DTO pentru a transfera date intre server si client si vice versa
    //in loc sa trecem o singură informatie folosim obiecte DTO pentru a trece un bulk de operatii de la server la client si invers
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserRegistrationDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserRegistrationDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
