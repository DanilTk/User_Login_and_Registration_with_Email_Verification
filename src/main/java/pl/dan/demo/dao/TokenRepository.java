package pl.dan.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.dan.demo.model.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "update ConfirmationToken ct set ct.confirmedAt = ?2 where ct.token = ?1")
    int updateConfirmationTime(String token, LocalDateTime confirmedAt);
}