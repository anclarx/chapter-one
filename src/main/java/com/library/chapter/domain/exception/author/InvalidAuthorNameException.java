package com.library.chapter.domain.exception.author;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthorNameException extends RuntimeException {

    public InvalidAuthorNameException() {
        super("Nome do autor inválido. O campo é obrigatório e deve conter entre 3 e 255 caracteres.");
    }
}
