package com.library.chapter.domain.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserLoginAlreadyExistsException extends RuntimeException {

    public UserLoginAlreadyExistsException(String login) {
        super("Já existe um usuário cadastrado com o login: " + login + ".");
    }
}
