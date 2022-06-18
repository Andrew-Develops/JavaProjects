package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GroupDTO {

    @NotBlank(message = "name field can't be blank")
    @NotEmpty
    @NotNull
    private String name;

    public GroupDTO(String name) {
        this.name = name;
    }

    public GroupDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
