package by.antohakon.testproject.service;

import by.antohakon.testproject.entity.Task;
import by.antohakon.testproject.exceptions.TaskNotFoundException;
import by.antohakon.testproject.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest2 {

    //region Generated with Explyt. Tests for TaskServiceImpl.deleteTaskById(Long)

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void testDeleteTaskByIdSuccessfully() {
        // Arrange
        Long taskId = 1L;
        Task task = new Task();

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // Act
        taskService.deleteTaskById(taskId);

        // Assert
        Mockito.verify(taskRepository).delete(task);
        Mockito.verify(taskRepository).findById(taskId);
    }

    @Test
    public void testDeleteTaskByIdThrowsTaskNotFoundException() {
        // Arrange
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        TaskNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                TaskNotFoundException.class,
                () -> taskService.deleteTaskById(taskId)
        );

        assertEquals("Task not found with id " + taskId, exception.getMessage());
        Mockito.verify(taskRepository).findById(taskId);
    }

    //endregion

}
