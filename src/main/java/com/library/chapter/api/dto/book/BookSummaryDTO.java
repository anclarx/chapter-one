package com.library.chapter.api.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSummaryDTO {

    private Long id;
    private String title;
    private String isbn;
}
