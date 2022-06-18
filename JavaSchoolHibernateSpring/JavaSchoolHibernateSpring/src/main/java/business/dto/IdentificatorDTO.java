package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class IdentificatorDTO {

    @NotBlank(message = "name field can't be blank")
    @NotEmpty
    @NotNull
    private int number;

    public IdentificatorDTO(int number) {
        this.number = number;
    }

    public IdentificatorDTO() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "IdentificatorDTO{" +
                "number=" + number +
                '}';
    }
}
