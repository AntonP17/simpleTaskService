package by.antohakon.testproject.dto;

import by.antohakon.testproject.entity.Status;
import lombok.Builder;

@Builder
public record TaskDto (Long id, String title, String description, Status status){
}
