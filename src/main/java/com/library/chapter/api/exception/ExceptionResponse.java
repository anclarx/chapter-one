package com.library.chapter.api.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
    private int status;
    private String error;
    private String path;
    private LocalDateTime timestamp;
}
