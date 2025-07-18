package by.antohakon.testproject.service;

import by.antohakon.testproject.dto.CreateTaskDto;
import by.antohakon.testproject.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    Page<TaskDto> getTasks(Pageable pageable);
    TaskDto getTaskById(Long id);
    TaskDto createTask(CreateTaskDto task);
    TaskDto updateTask(CreateTaskDto task, Long id);
    void deleteTaskById(Long id);

}
