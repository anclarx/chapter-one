package com.library.chapter.api.exception;

import com.library.chapter.domain.exception.author.*;
import com.library.chapter.domain.exception.book.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (exception instanceof AuthorNotFoundException || exception instanceof AuthorsNotFoundException
                || exception instanceof BookNotFoundException) {
            status = HttpStatus.NOT_FOUND; // 404
        }
        else if (exception instanceof AuthorEmailAlreadyExistsException || exception instanceof BookIsbnAlreadyExistsException) {
            status = HttpStatus.CONFLICT; // 409
        }

        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage(),
                status.value(),
                status.getReasonPhrase(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        String message = String.join(" ", errors);
        ExceptionResponse response = new ExceptionResponse(
                message,
                status.value(),
                status.getReasonPhrase(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(response);
    }
}