package com.library.chapter.api.dto.author;


import com.library.chapter.api.dto.book.BookSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDTO {

    private Long id;
    private String name;
    private String email;
    private List<BookSummaryDTO> books;
    private String description;
}
