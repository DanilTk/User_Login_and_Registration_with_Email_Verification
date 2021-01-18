package pl.dan.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
