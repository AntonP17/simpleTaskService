package by.antohakon.testproject.exceptions;

public class TaskDuplicateException extends RuntimeException {
    public TaskDuplicateException(String message) {
        super(message);
    }
}
