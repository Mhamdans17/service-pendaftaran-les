package tokyo.dev.pendaftaranles.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tokyo.dev.pendaftaranles.constant.ApiConstants;
import tokyo.dev.pendaftaranles.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

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
                        ApiConstants.STATUS_FAILED,
                        ApiConstants.INVALID_REQUEST_CODE_PEDAFTARAN,
                        message,
                        null
                )
        );
    }

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
                        ApiConstants.STATUS_FAILED,
                        ApiConstants.INVALID_REQUEST_GET_DATA_CODE,
                        message,
                        null
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(
            IllegalArgumentException ex) {

        return ResponseEntity.badRequest().body(
                new ApiResponse<>(
                        ApiConstants.STATUS_FAILED,
                        ApiConstants.INVALID_REQUEST_CODE_PEDAFTARAN,
                        ex.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(
            BadRequestException ex) {

        return ResponseEntity.badRequest().body(
                new ApiResponse<>(
                        ApiConstants.STATUS_FAILED,
                        ApiConstants.INVALID_REQUEST_CODE_PEDAFTARAN,
                        ex.getMessage(),
                        null
                )
        );
    }
}
