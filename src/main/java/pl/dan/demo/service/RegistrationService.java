package pl.dan.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dan.demo.model.User;
import pl.dan.demo.model.UserRegistrationForm;
import pl.dan.demo.model.UserRole;
import pl.dan.demo.service.validation.EmailValidator;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final UserService userService;

    public String register(UserRegistrationForm form) {

        if (!emailValidator.test(form.getEmail())) {
            throw new IllegalStateException("Invalid email");
        }

        User user = new User(form.getFirstName(),
                form.getLastName(),
                form.getEmail(),
                form.getPassword(),
                UserRole.USER);

        return userService.signUpUser(user);
    }
}
