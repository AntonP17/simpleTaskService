package by.antohakon.testproject.dto;

import by.antohakon.testproject.entity.Roles;

public record RegistrationRequestDto(String username, String password, Roles role) {
}
