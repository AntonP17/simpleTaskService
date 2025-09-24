package by.antohakon.testproject.config;

import by.antohakon.testproject.dto.RegistrationRequest;
import by.antohakon.testproject.entity.User;
import by.antohakon.testproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/register")
public class UserController {

    private final UserService userService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public User createUser(@RequestBody RegistrationRequest registrationRequest) {
        return userService.registerUser(registrationRequest);
    }

}
