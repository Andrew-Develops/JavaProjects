package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressDTO {

    @NotBlank(message = "name field can't be blank")
    @NotEmpty
    @NotNull
    private String city;
    @NotBlank(message = "name field can't be blank")
    @NotEmpty
    @NotNull
    private String street;

    public AddressDTO(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public AddressDTO() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
