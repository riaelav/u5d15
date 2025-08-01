package valeriapagliarini.u5d15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valeriapagliarini.u5d15.entities.User;
import valeriapagliarini.u5d15.exceptions.ValidationException;
import valeriapagliarini.u5d15.payloads.UserRegistrationDTO;
import valeriapagliarini.u5d15.payloads.UserResponseDTO;
import valeriapagliarini.u5d15.services.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO registerUser(@RequestBody @Validated UserRegistrationDTO payload, BindingResult validationResult) {
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

}
