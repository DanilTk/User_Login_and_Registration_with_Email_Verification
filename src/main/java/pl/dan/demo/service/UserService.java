package pl.dan.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dan.demo.dao.UserRepository;
import pl.dan.demo.model.User;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", s)));
    }

    public String signUpUser(User user) {

        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("User email already exists");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        //todo: send confirmation & token;

        return "";
    }
}
