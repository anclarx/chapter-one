package com.library.chapter.domain.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("O e-mail " + email + " já está em uso por outro usuário");
    }
}
