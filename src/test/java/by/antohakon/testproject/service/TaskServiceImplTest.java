package by.antohakon.testproject.service;

import by.antohakon.testproject.dto.CreateTaskDto;
import by.antohakon.testproject.dto.TaskDto;
import by.antohakon.testproject.dto.mapper.TaskMapper;
import by.antohakon.testproject.entity.Status;
import by.antohakon.testproject.entity.Task;
import by.antohakon.testproject.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;


    @Test
    public void testgetTaskById(){

        // Arrange
        Long id = 1L;
        Task task1 = new Task(id,"task1","taskdesc", Status.NEW);
        TaskDto expectedDto = new TaskDto(id, "task1", "taskdesc", Status.NEW);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        when(taskMapper.toDto(task1)).thenReturn(expectedDto);

        // Act
        TaskDto result = taskService.getTaskById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("task1", result.title());
        assertEquals("taskdesc", result.description());
        assertEquals(Status.NEW, result.status());
    }

   @Test
    public void testCreateTask(){

       // Arrange
       CreateTaskDto createDto = new CreateTaskDto("unique task", "description", Status.NEW);
       Task newTask = new Task("unique task", "description", Status.NEW);
       TaskDto expectedDto = new TaskDto(null, "unique task", "description", Status.NEW);

       // Mocks
       when(taskRepository.existsByTitle("unique task")).thenReturn(false);
       when(taskMapper.toEntity(createDto)).thenReturn(newTask);
       when(taskRepository.save(newTask)).thenReturn(newTask); // возвращаем тот же объект
       when(taskMapper.toDto(newTask)).thenReturn(expectedDto); // мокаем с newTask (без ID)

       // Act
       TaskDto result = taskService.createTask(createDto);

       // Assert
       assertNotNull(result);
       assertEquals("unique task", result.title());
       assertEquals("description", result.description());
       assertEquals(Status.NEW, result.status());

   }

   @Test
    public void testUpdateTask(){

       // Arrange
       Long taskId = 1L;
       CreateTaskDto updateDto = new CreateTaskDto("updated title", "updated desc", Status.IN_PROGRESS);

       Task existingTask = new Task(taskId, "old title", "old desc", Status.NEW);
       Task updatedTask = new Task(taskId, "updated title", "updated desc", Status.IN_PROGRESS);
       TaskDto expectedDto = new TaskDto(taskId, "updated title", "updated desc", Status.IN_PROGRESS);

       when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
       when(taskRepository.save(existingTask)).thenReturn(updatedTask);
       when(taskMapper.toDto(updatedTask)).thenReturn(expectedDto);

       // Act
       TaskDto result = taskService.updateTask(updateDto, taskId);

       // Assert
       assertNotNull(result);
       assertEquals(taskId, result.id());
       assertEquals("updated title", result.title());
       assertEquals("updated desc", result.description());
       assertEquals(Status.IN_PROGRESS, result.status());

   }

}
