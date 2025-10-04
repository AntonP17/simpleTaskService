package by.antohakon.testproject.controller;

import by.antohakon.testproject.dto.CreateTaskDto;
import by.antohakon.testproject.dto.TaskDto;
import by.antohakon.testproject.service.TaskService;
import by.antohakon.testproject.service.TaskServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "task_controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping("/{taskId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId) {

        return ResponseEntity.ok(taskService.getTaskById(taskId)) ;

    }
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Page<TaskDto> getAllTasks(@PageableDefault(size = 5) Pageable pageable) {
        return taskService.getTasks(pageable);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TaskDto createTask(@RequestBody CreateTaskDto createTaskDto) {
        return taskService.createTask(createTaskDto);
    }

    @PutMapping("/{taskId}")
    @ResponseStatus(value = HttpStatus.OK)
    public TaskDto updateTask(@PathVariable Long taskId,@RequestBody CreateTaskDto createTaskDto) {
        return taskService.updateTask(createTaskDto,taskId);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTaskById(taskId);
    }

}
