package com.library.chapter.domain.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserEmailAlreadyExistsException extends RuntimeException {

    public UserEmailAlreadyExistsException(String email) {
        super("Já existe um usuário cadastrado com o e-mail: " + email + ".");
    }
}
