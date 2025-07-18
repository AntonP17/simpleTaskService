package by.antohakon.testproject.dto.mapper;

import by.antohakon.testproject.dto.CreateTaskDto;
import by.antohakon.testproject.dto.TaskDto;
import by.antohakon.testproject.entity.Task;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  TaskMapper {

    TaskDto toDto(Task task);
    Task toEntity(CreateTaskDto taskDto);

    List<TaskDto> toDtoList(List<Task> tasks);


}
