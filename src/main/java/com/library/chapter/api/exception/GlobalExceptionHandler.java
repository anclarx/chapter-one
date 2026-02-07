package com.library.chapter.api.exception;

import com.library.chapter.domain.exception.author.*;
import com.library.chapter.domain.exception.book.*;
import com.library.chapter.domain.exception.user.UserNotFoundException;
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

        if (exception instanceof AuthorNotFoundException || exception instanceof AuthorsNotFoundException
                || exception instanceof BookNotFoundException) {
            status = HttpStatus.NOT_FOUND; // 404
        }
        else if (exception instanceof AuthorEmailAlreadyExistsException || exception instanceof BookIsbnAlreadyExistsException) {
            status = HttpStatus.CONFLICT; // 409
        }
        else if (exception instanceof InvalidBookTitleException || exception instanceof InvalidBookIsbnException
                || exception instanceof InvalidBookPublisherException || exception instanceof InvalidAuthorNameException
                || exception instanceof InvalidAuthorEmailException) {
            status = HttpStatus.BAD_REQUEST; //
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