package by.antohakon.testproject.controller;

import by.antohakon.testproject.dto.JwtResponseDto;
import by.antohakon.testproject.dto.RefreshTokenRequestDto;
import by.antohakon.testproject.dto.RegistrationRequestDto;
import by.antohakon.testproject.entity.User;
import by.antohakon.testproject.service.AuthService;
import by.antohakon.testproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/register")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public User createUser(@RequestBody RegistrationRequestDto registrationRequest) {
        return userService.registerUser(registrationRequest);
    }

    @PostMapping("/generateToken")
    public JwtResponseDto authenticateAndGetToken(@RequestBody RegistrationRequestDto registrationRequest) {
        return authService.authenticate(
                registrationRequest.username(),
                registrationRequest.password());
    }

    @PostMapping("/refresh-token")
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

}
