package by.antohakon.testproject.controller;

import by.antohakon.testproject.dto.RegistrationRequest;
import by.antohakon.testproject.entity.User;
import by.antohakon.testproject.service.AuthService;
import by.antohakon.testproject.service.JwtService;
import by.antohakon.testproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/register")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public User createUser(@RequestBody RegistrationRequest registrationRequest) {
        return userService.registerUser(registrationRequest);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody RegistrationRequest registrationRequest) {
        return authService.authenticate(
                registrationRequest.username(),
                registrationRequest.password());
    }

}
