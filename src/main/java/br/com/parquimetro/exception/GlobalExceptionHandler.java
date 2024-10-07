package br.com.parquimetro.exception;

import br.com.parquimetro.dto.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ValidationError> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ValidationError error = new ValidationError("ticket", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<ValidationError> errors = new ArrayList<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {

            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errors.add(new ValidationError(field, message));

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

    @ExceptionHandler(TicketAlreadyClosedException.class)
    public ResponseEntity<ValidationError> handleTicketAlreadyClosedException(TicketAlreadyClosedException ex) {

        ValidationError error = new ValidationError("ticket", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ValidationError> handleIllegalStateException(IllegalStateException ex) {

        ValidationError error = new ValidationError("sistema", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

}
