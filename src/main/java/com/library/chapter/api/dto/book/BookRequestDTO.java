package com.library.chapter.api.dto.book;

import com.library.chapter.domain.model.AuthorModel;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    private String title;
    private String isbn;
    // private String callNumber;

    @NotEmpty
    List<Long> authorIds;

    private String publisher;
    private String description;

    private Integer edition;
    private Integer pages;
    private LocalDate publicationDate;
}
