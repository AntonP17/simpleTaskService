package by.antohakon.testproject.service;

import by.antohakon.testproject.dto.JwtResponseDto;
import by.antohakon.testproject.dto.RefreshTokenRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtResponseDto authenticate(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        if (authentication.isAuthenticated()) {

            String accessToken = jwtService.generateAccessToken(username);
            String refreshToken = jwtService.generateRefreshToken(username);

            return JwtResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user credentials");
        }
    }

    public JwtResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.refreshToken();

        if (jwtService.validateRefreshToken(refreshToken)) {
            String username = jwtService.extractUsername(refreshToken);
            String newAccessToken = jwtService.generateAccessToken(username);

            return JwtResponseDto.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }

}
