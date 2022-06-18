package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TeacherDTO {

    @NotBlank(message = "name field can't be blank")
    @NotEmpty
    @Pattern(regexp = "([a-zA-Z])*")
    @NotNull
    private String firstName;
    @NotBlank(message = "name field can't be blank")
    @NotEmpty
    @Pattern(regexp = "([a-zA-Z])*")
    @NotNull
    private String lastName;
    @NotBlank(message = "name field can't be blank")
    @NotEmpty
    @NotNull
    private String curriculum;
    private AddressDTO addressDTO;
    private IdentificatorDTO identificatorDTO;

    public TeacherDTO(String firstName, String lastName, String curriculum, AddressDTO addressDTO, IdentificatorDTO identificatorDTO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.curriculum = curriculum;
        this.addressDTO = addressDTO;
        this.identificatorDTO = identificatorDTO;
    }

    public TeacherDTO() {
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

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public IdentificatorDTO getIdentificatorDTO() {
        return identificatorDTO;
    }

    public void setIdentificatorDTO(IdentificatorDTO identificatorDTO) {
        this.identificatorDTO = identificatorDTO;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", curriculum='" + curriculum + '\'' +
                ", addressDTO=" + addressDTO +
                ", identificatorDTO=" + identificatorDTO +
                '}';
    }
}
