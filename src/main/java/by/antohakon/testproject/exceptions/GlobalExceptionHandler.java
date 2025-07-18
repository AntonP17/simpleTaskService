package by.antohakon.testproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @AllArgsConstructor
    @Getter
    private static class ErrorResponse {

        private String message;
        private Instant timestamp;
        private String errorType;

    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> taskNotFoundException(TaskNotFoundException ex) {
        log.error("Task not found: {}", ex.getMessage());
        return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                   .body(new ErrorResponse(
                           ex.getMessage(),
                           Instant.now(),
                           ex.getClass().getSimpleName()
                   ));
    }

    @ExceptionHandler(TaskDuplicateException.class)
    public ResponseEntity<ErrorResponse> taskDuplicateException(Exception ex) {
        log.error("Task duplicated: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        ex.getMessage(),
                        Instant.now(),
                        ex.getClass().getSimpleName()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        ex.getMessage(),
                        Instant.now(),
                        ex.getClass().getSimpleName()
                ));
    }

}
