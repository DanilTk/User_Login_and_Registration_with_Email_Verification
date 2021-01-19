package pl.dan.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dan.demo.dao.TokenRepository;
import pl.dan.demo.model.ConfirmationToken;

@Service
@AllArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public void saveToken(ConfirmationToken token) {
        tokenRepository.save(token);
    }
}
