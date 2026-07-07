package com.example.parserapi.exception;

import com.example.parserapi.entity.ParserEntity;
import com.example.parserapi.model.ParseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
      @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ParseResponse> handleValidationErrors(MethodArgumentNotValidException exception)
    {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        // get first error message
        String errorMessage = "Invalid input";
        if (!fieldErrors.isEmpty()) {
            FieldError firstError = fieldErrors.get(0);
            errorMessage = firstError.getField() + ": " + firstError.getDefaultMessage();
        }

        return ResponseEntity
                .badRequest()
                .body(buildErrorResponse(errorMessage));
    }
      @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ParseResponse> handleNotFound(ResourceNotFoundException exception)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildErrorResponse(exception.getMessage()));

    }
     @ExceptionHandler(Exception.class)
    public ResponseEntity<ParseResponse> handleGenericError(Exception exception)
    {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildErrorResponse(exception.getMessage()));
    }

    private ParseResponse buildErrorResponse(String message)
    {
      ParseResponse response=new ParseResponse();
          response.setStatus("FAILURE");
          response.setMessage(message);
          response.setName(null);
           response.setAge(null);
           response.setEmail(null);
           response.setDepartment(null);
           response.setProcessedAt(LocalDateTime.now()
                   .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
              return response;

    }





}
