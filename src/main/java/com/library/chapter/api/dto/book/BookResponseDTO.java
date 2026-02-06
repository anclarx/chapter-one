package com.library.chapter.api.dto.book;

import com.library.chapter.api.dto.author.AuthorSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {

    private Long id;
    private String title;
    private String isbn;
    // private String callNumber;

    private List<AuthorSummaryDTO> authors;

    private String publisher;
    private String description;

    private Integer edition;
    private Integer pages;
    private LocalDate publicationDate;
}
