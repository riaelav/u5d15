package valeriapagliarini.u5d15.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import valeriapagliarini.u5d15.entities.User;
import valeriapagliarini.u5d15.exceptions.BadRequestException;
import valeriapagliarini.u5d15.payloads.UserRegistrationDTO;
import valeriapagliarini.u5d15.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(UserRegistrationDTO dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new BadRequestException("Username already in use");
        }

        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new BadRequestException("Email already in use");
        }

        User newUser = new User(
                dto.username(), dto.email(),
                passwordEncoder.encode(dto.password()),
                dto.role()
        );

        return userRepository.save(newUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }
}
