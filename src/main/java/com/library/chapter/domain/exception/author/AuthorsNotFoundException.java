package com.library.chapter.domain.exception.author;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorsNotFoundException extends RuntimeException {

    public AuthorsNotFoundException(List<Long> ids) {
        super("Autores não encontrados com os IDs: " + ids.toString());
    }
}