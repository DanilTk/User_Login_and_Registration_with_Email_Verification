package pl.dan.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dan.demo.model.ConfirmationToken;
import pl.dan.demo.model.User;
import pl.dan.demo.model.UserRegistrationForm;
import pl.dan.demo.model.UserRole;
import pl.dan.demo.service.validation.EmailValidator;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final UserService userService;
    private final TokenService tokenService;

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

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = tokenService.findToken(token).orElseThrow(() -> new IllegalStateException("Token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Token already has been confirmed");
        }

        LocalDateTime tokenExpirationTime = confirmationToken.getExpiresAt();

        if (tokenExpirationTime.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        tokenService.confirmExpiration(token);
        userService.enableAccount(confirmationToken.getUser().getEmail());

        return "Token confirmed";
    }
}
