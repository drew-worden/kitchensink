// Package
package org.jboss.as.quickstarts.kitchensink.exceptions;

// Imports
import com.mongodb.MongoWriteException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.jboss.as.quickstarts.kitchensink.dtos.members.global.KitchenSinkResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;

/**
 * Global exception handler for the application.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the ResourceNotFoundException.
     * @param ex The exception.
     * @return The response entity.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<KitchenSinkResponse<?>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());
        KitchenSinkResponse<?> response = KitchenSinkResponse.builder()
                .message("Resource not found.")
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the ConstraintViolationException.
     * @param ex The exception.
     * @return The response entity.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<KitchenSinkResponse<?>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Validation error: {}", ex.getMessage());
        KitchenSinkResponse<?> response = KitchenSinkResponse.builder()
                .message("Validation error.")
                .errors(ex.getConstraintViolations().stream()
                        .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                        .toList())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the MongoWriteException.
     * @param ex The exception.
     * @return The response entity.
     */
    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<KitchenSinkResponse<?>> handleMongoWriteException(MongoWriteException ex) {
        log.error("Database write error: {}", ex.getMessage());
        if (ex.getError().getCode() == 11000) {
            KitchenSinkResponse<?> response = KitchenSinkResponse.builder()
                    .message("Duplicate key error.")
                    .errors(List.of("A resource with the provided email already exists."))
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            KitchenSinkResponse<?> response = KitchenSinkResponse.builder()
                    .message("A resource with the provided email already exists.")
                    .errors(List.of(ex.getMessage()))
                    .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}