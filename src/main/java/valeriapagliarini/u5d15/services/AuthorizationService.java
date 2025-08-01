package valeriapagliarini.u5d15.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import valeriapagliarini.u5d15.entities.User;
import valeriapagliarini.u5d15.exceptions.UnauthorizedException;
import valeriapagliarini.u5d15.payloads.LoginRequestDTO;
import valeriapagliarini.u5d15.tools.JWTTools;

@Service
public class AuthorizationService {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;


    public String checkCredentialsAndGenerateToken(LoginRequestDTO body) {
     
        User found = this.userService.findByEmail(body.email());


        if (bcrypt.matches(body.password(), found.getPassword())) {

            String accessToken = jwtTools.createToken(found);

            return accessToken;
        } else {
            throw new UnauthorizedException("Incorrect credentials!");
        }
    }


}
