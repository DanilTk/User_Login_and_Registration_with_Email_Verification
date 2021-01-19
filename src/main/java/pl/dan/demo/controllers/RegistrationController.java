package pl.dan.demo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dan.demo.model.UserRegistrationForm;
import pl.dan.demo.service.RegistrationService;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public String registerUser(@RequestBody UserRegistrationForm form) {
        return registrationService.register(form);
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestBody String token) {
        return registrationService.confirmToken(token);
    }

}
