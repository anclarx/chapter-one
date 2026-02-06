package com.library.chapter.application.mapper;

import com.library.chapter.api.dto.author.AuthorRequestDTO;
import com.library.chapter.api.dto.author.AuthorResponseDTO;
import com.library.chapter.api.dto.author.AuthorSummaryDTO;
import com.library.chapter.api.dto.book.BookSummaryDTO;
import com.library.chapter.domain.model.AuthorModel;
import com.library.chapter.domain.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    AuthorModel toEntity(AuthorRequestDTO author);

    AuthorResponseDTO toResponse(AuthorModel entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateModelFromDto(AuthorRequestDTO dto, @MappingTarget AuthorModel model);

    AuthorSummaryDTO toSummary(AuthorModel author);
    BookSummaryDTO toBookSummary(BookModel book);
}
