package by.antohakon.testproject.dto;

import by.antohakon.testproject.entity.Status;

public record CreateTaskDto(String title, String description, Status status){
}
