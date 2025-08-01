package valeriapagliarini.u5d15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valeriapagliarini.u5d15.entities.User;
import valeriapagliarini.u5d15.exceptions.ValidationException;
import valeriapagliarini.u5d15.payloads.LoginRequestDTO;
import valeriapagliarini.u5d15.payloads.LoginRespDTO;
import valeriapagliarini.u5d15.payloads.UserRegistrationDTO;
import valeriapagliarini.u5d15.payloads.UserResponseDTO;
import valeriapagliarini.u5d15.services.AuthorizationService;
import valeriapagliarini.u5d15.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO registerUser(@RequestBody @Validated UserRegistrationDTO payload,
                                        BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(
                    validationResult.getFieldErrors()
                            .stream()
                            .map(fieldError -> fieldError.getDefaultMessage())
                            .toList()
            );
        } else {
            User user = userService.register(payload);
            return new UserResponseDTO(user.getId());
        }
    }


    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody LoginRequestDTO payload) {
        String accessToken = authorizationService.checkCredentialsAndGenerateToken(payload);
        return new LoginRespDTO(accessToken);
    }

}
