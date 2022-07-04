package business.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CountryDTO {
    @NotNull
    @NotBlank(message = "Introduceti o tara")
    @Pattern(regexp = "([a-z A-Z])*")
    @NotEmpty
    private String name;
    @Valid
    private ContinentDTO continentDTO;

    public CountryDTO(String name) {
        this.name = name;
    }

    public CountryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContinentDTO getContinentDTO() {
        return continentDTO;
    }

    public void setContinentDTO(ContinentDTO continentDTO) {
        this.continentDTO = continentDTO;
    }

    @Override
    public String toString() {
        return "CountryDTO: " + name + "," + continentDTO ;
    }
}
