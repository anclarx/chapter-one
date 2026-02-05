package com.library.chapter.api.exception;

import com.library.chapter.domain.exception.AuthorNotFoundException;
import com.library.chapter.domain.exception.BookNotFoundException;
import com.library.chapter.domain.exception.EmailAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (exception instanceof AuthorNotFoundException || exception instanceof BookNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception instanceof EmailAlreadyExistsException) {
            status = HttpStatus.CONFLICT;
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
}