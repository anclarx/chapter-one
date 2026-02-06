package com.library.chapter.domain.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("E-mail em uso por outro usuário: " + email);
    }
}
