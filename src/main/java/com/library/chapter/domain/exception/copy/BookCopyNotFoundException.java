package com.library.chapter.domain.exception.copy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookCopyNotFoundException extends RuntimeException {

    public BookCopyNotFoundException(Long id) {
        super("Exemplar não encontrado (ID: " + id + ").");
    }
}
