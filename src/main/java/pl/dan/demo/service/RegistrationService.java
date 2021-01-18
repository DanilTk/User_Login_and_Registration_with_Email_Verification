package pl.dan.demo.service;

import org.springframework.stereotype.Service;
import pl.dan.demo.model.RegistrationForm;

@Service
public class RegistrationService {

    public String register(RegistrationForm form) {
        return "works";
    }
}
