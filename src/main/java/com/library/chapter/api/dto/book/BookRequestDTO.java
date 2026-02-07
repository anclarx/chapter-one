package com.library.chapter.api.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    @NotBlank(message = "O título é obrigatório.")
    @Size(min = 3, max = 255, message = "O título deve ter entre 3 e 255 caracteres.")
    private String title;

    @NotBlank(message = "O ISBN é obrigatório.")
    @Size(max = 20, message = "O ISBN não pode exceder 20 caracteres.")
    private String isbn;

    @NotEmpty(message = "O livro deve ter pelo menos um autor vinculado.")
    private List<Long> authorIds;

    @Size(min = 3, max = 255, message = "A editora deve ter entre 3 e 255 caracteres.")
    private String publisher;

    private String description;
    private Integer edition;

    private Integer pages;
    private LocalDate publicationDate;
}
