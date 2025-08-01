package valeriapagliarini.u5d15.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import valeriapagliarini.u5d15.entities.User;
import valeriapagliarini.u5d15.payloads.UserRegistrationDTO;
import valeriapagliarini.u5d15.payloads.UserResponseDTO;
import valeriapagliarini.u5d15.services.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRegistrationDTO payload) {
        User user = userService.register(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDTO(user.getId(),
                user.getUsername(), user.getEmail(), user.getRole()));

    }
}
