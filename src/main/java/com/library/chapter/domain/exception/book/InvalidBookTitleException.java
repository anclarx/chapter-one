package com.library.chapter.domain.exception.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBookTitleException extends RuntimeException {

    public InvalidBookTitleException() {
        super("Título do livro inválido. O campo é obrigatório e deve conter entre 3 e 255 caracteres.");
    }
}
