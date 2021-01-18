package pl.dan.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.dan.demo.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
}
