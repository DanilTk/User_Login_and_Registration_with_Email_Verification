package pl.dan.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dan.demo.dao.TokenRepository;
import pl.dan.demo.model.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public void saveToken(ConfirmationToken token) {
        tokenRepository.save(token);
    }

    public Optional<ConfirmationToken> findToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public int confirmExpiration(String token) {
        return tokenRepository.updateConfirmationTime(token, LocalDateTime.now());
    }
}
