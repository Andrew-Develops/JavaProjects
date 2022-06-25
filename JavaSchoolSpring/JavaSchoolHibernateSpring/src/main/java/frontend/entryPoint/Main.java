package frontend.entryPoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"frontend", "business", "persistence"})
public class Main {

    public static void main(String[] args) {

        //Request HTTP pentru urmatoarele clase
        //GroupController: GET,POST
        //StudentController: GET,POST,PUT,DELETE
        //TeacherController: GET,POST,PUT,DELETE
        //Validarile din clasele DTO : @NotBlank, @NotEmpty, @Pattern, @NotNull
        //Constrainturi din DB unique pe :
        //Teacher.address_id, Teacher.identificator_id
        //Student.address_id, Student.identificator_id


        SpringApplication.run(Main.class, args);
        System.out.println("Application started");
        System.out.println("Application running");

    }
}
