package com.library.chapter.domain.exception.author;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AuthorEmailAlreadyExistsException extends RuntimeException {

    public AuthorEmailAlreadyExistsException(String email) {
        super("Já existe um autor cadastrado com o e-mail: " + email + ".");
    }
}
