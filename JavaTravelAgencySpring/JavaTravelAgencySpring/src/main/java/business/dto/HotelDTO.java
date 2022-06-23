package business.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class HotelDTO {
    @NotNull
    @NotBlank(message = "Introduceti un hotel")
    @Pattern(regexp = "([a-z A-Z])*")
    @NotEmpty
    private String name;
    @NotNull
    @NotBlank
    @NotEmpty
    private String address;
    @NotNull
    @Min(1)
    @Max(5)
    private double numberOfStars;
    @NotNull
    @Valid
    private String description;
    @NotNull
    @Valid
    private RoomDTO roomDTO;
    @Valid
    private CityDTO cityDTO;

    public HotelDTO(String name, String address, double numberOfStars, String description) {
        this.name = name;
        this.address = address;
        this.numberOfStars = numberOfStars;
        this.description = description;
    }

    public HotelDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(double numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", numberOfStars=" + numberOfStars +
                ", description='" + description + '\'' +
                ", roomDTO=" + roomDTO +
                ", cityDTO=" + cityDTO +
                '}';
    }
}
