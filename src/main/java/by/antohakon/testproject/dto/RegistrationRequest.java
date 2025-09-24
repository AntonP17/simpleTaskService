package by.antohakon.testproject.dto;

import by.antohakon.testproject.entity.Roles;

public record RegistrationRequest(String username, String password, Roles role) {
}
