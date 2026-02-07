package com.library.chapter.api.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String message,
        int status,
        String error,
        String path,
        LocalDateTime timestamp
) {}