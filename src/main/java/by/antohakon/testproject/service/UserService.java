package by.antohakon.testproject.service;

import by.antohakon.testproject.dto.RegistrationRequestDto;
import by.antohakon.testproject.entity.User;
import by.antohakon.testproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(RegistrationRequestDto request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        userRepository.save(user);

        return user;
    }

}
