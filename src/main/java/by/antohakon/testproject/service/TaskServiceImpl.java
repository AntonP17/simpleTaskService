package by.antohakon.testproject.service;

import by.antohakon.testproject.dto.CreateTaskDto;
import by.antohakon.testproject.dto.TaskDto;
import by.antohakon.testproject.dto.mapper.TaskMapper;
import by.antohakon.testproject.entity.Task;
import by.antohakon.testproject.exceptions.TaskDuplicateException;
import by.antohakon.testproject.exceptions.TaskNotFoundException;
import by.antohakon.testproject.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public Page<TaskDto> getTasks(Pageable pageable) {

        return taskRepository.findAll(pageable)
                .map(task -> TaskDto.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .status(task.getStatus())
                        .build());

//        List<Task> tasks = taskRepository.findAll();
//        return taskMapper.toDtoList(tasks);

    }

    @Override
    public TaskDto getTaskById(Long id) {

        Task findTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + id));

//        TaskDto taksById = TaskDto.builder()
//                .id(findTask.getId())
//                .title(findTask.getTitle())
//                .description(findTask.getDescription())
//                .status(findTask.getStatus())
//                .build();

        return taskMapper.toDto(findTask);
    }

    @Override
    public TaskDto createTask(CreateTaskDto task) {

        if (taskRepository.existsByTitle(task.title())){
            throw new TaskDuplicateException("Task already exists with title " + task.title());
        }

        Task newTask = taskMapper.toEntity(task);

        taskRepository.save(newTask);

        return taskMapper.toDto(newTask);

    }

    @Override
    public TaskDto updateTask(CreateTaskDto task, Long id) {

        Task findTaskById = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + id));

            findTaskById.setTitle(task.title());
            findTaskById.setDescription(task.description());
            findTaskById.setStatus(task.status());
            taskRepository.save(findTaskById);

        return taskMapper.toDto(findTaskById);
    }

    @Override
    public void deleteTaskById(Long id) {

        Task findTaskById = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + id));

        taskRepository.delete(findTaskById);

    }
}
