package by.antohakon.testproject.dto;

import lombok.Builder;

@Builder
public record JwtResponseDto(String accessToken,
                             String refreshToken) {
}
