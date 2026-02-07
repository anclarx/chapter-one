package com.library.chapter.domain.exception.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBookIsbnException extends RuntimeException {

    public InvalidBookIsbnException() {
        super("Isbn do livro inválido. O campo é obrigatório e deve conter no máximo 20 caracteres.");
    }
}