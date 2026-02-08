package com.library.chapter.api.dto.copy;

import com.library.chapter.domain.model.enums.BookCopyStatusEnum;
import jakarta.validation.constraints.NotNull;

public class BookCopyRequestDTO {

    @NotNull(message = "O exemplar deve ter um livro vinculado.")
    private Long bookModelId;

    @NotNull(message = "O número de catálogo do exemplar é obrigatório.")
    private Integer catalogCopy;

    @NotNull(message = "O status do exemplar é obrigatório.")
    private BookCopyStatusEnum status;
}
