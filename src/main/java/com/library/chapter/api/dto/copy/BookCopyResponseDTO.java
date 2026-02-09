package com.library.chapter.api.dto.copy;

import com.library.chapter.api.dto.book.BookSummaryDTO;
import com.library.chapter.domain.model.enums.BookCopyStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyResponseDTO {

    private Long id;
    private BookSummaryDTO bookModel;
    private BookCopyStatusEnum status;
}
