package com.library.chapter.domain.exception;

import java.util.List;

public class AuthorsNotFoundException extends RuntimeException {
    public AuthorsNotFoundException(List<Long> ids) {
        super("Autores não encontrados com os IDs: " + ids.toString());
    }
}