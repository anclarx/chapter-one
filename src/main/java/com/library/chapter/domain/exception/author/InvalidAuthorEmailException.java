package com.library.chapter.domain.exception.author;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthorEmailException extends RuntimeException {

    public InvalidAuthorEmailException() {
        super("E-mail do autor inválido. O endereço deve ser válido e conter entre 3 e 255 caracteres.");
    }
}
