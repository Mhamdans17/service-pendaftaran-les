package tokyo.dev.pendaftaranles.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tokyo.dev.pendaftaranles.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // BODY validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationBody(
            MethodArgumentNotValidException ex
    ) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.badRequest().body(
                new ApiResponse(
                        "FAILED",
                        "40001",
                        message,
                        null
                )
        );
    }

    // PARAM validation error
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleValidationParam(
            ConstraintViolationException ex
    ) {

        String message = ex.getConstraintViolations()
                .iterator()
                .next()
                .getMessage();

        return ResponseEntity.badRequest().body(
                new ApiResponse(
                        "FAILED",
                        "40001",
                        message,
                        null
                )
        );
    }
}
