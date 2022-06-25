package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StudentDTO {

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
    @NotNull
    private int yearOfBirth;
    @NotBlank(message = "name field can't be blank")
    @NotEmpty
    @NotNull
    private String electives;
    private GroupDTO groupDTO;
    private AddressDTO addressDTO;
    private IdentificatorDTO identificatorDTO;

    public StudentDTO(String firstName, String lastName, int yearOfBirth, String electives, GroupDTO groupDTO, AddressDTO addressDTO, IdentificatorDTO identificatorDTO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.electives = electives;
        this.groupDTO = groupDTO;
        this.addressDTO = addressDTO;
        this.identificatorDTO = identificatorDTO;
    }

    public StudentDTO() {
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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getElectives() {
        return electives;
    }

    public void setElectives(String electives) {
        this.electives = electives;
    }

    public GroupDTO getGroupDTO() {
        return groupDTO;
    }

    public void setGroupDTO(GroupDTO groupDTO) {
        this.groupDTO = groupDTO;
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
        return "StudentDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", electives='" + electives + '\'' +
                ", groupDTO=" + groupDTO +
                ", addressDTO=" + addressDTO +
                ", identificatorDTO=" + identificatorDTO +
                '}';
    }
}
