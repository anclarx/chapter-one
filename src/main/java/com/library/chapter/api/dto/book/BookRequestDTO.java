package com.library.chapter.api.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    private String title;
    private String isbn;
    private Long authorId;
    private String publisher;
    private String description;
    private Integer edition;
    private Integer pages;
    private LocalDate publicationDate;
}
