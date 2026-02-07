package com.library.chapter.domain.exception.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBookPublisherException extends RuntimeException {

    public InvalidBookPublisherException() {
        super("Editora do livro inválida. O campo deve conter entre 3 e 255 caracteres.");
    }
}
