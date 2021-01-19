package pl.dan.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dan.demo.model.ConfirmationToken;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

}