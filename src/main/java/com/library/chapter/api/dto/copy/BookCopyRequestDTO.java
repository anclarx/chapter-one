package com.library.chapter.api.dto.copy;

import com.library.chapter.domain.model.enums.BookCopyStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyRequestDTO {

    @NotNull(message = "O exemplar deve ter um livro vinculado.")
    private Long bookModelId;

    @NotNull(message = "O status do exemplar é obrigatório.")
    private BookCopyStatusEnum status;
}
