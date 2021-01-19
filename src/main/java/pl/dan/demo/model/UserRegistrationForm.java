package pl.dan.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
