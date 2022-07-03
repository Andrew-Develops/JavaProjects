package business.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AirportDTO {
    @NotNull
    @NotBlank(message = "Introduceti un aeroport")
    @NotEmpty
    private String name;
    @Valid
    private CityDTO cityDTO;

    public AirportDTO(String name) {
        this.name = name;
    }

    public AirportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }

    @Override
    public String toString() {
        return "AirportDTO{" +
                "name='" + name + '\'' +
                ", cityDTO=" + cityDTO +
                '}';
    }
}
