package com.library.chapter.domain.exception.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookIsbnAlreadyExistsException extends RuntimeException {

    public BookIsbnAlreadyExistsException(String isbn) {
        super("Isbn em uso por outro livro:" + isbn);
    }
}
