package com.library.chapter.domain.exception.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookIsbnAlreadyExistsException extends RuntimeException {

    public BookIsbnAlreadyExistsException(String isbn) {
        super("Já existe um livro cadastrado com o ISBN " + isbn + ".");
    }
}
