package com.library.chapter.application.mapper;

import com.library.chapter.api.dto.author.AuthorSummaryDTO;
import com.library.chapter.api.dto.book.BookRequestDTO;
import com.library.chapter.api.dto.book.BookResponseDTO;
import com.library.chapter.api.dto.book.BookSummaryDTO;
import com.library.chapter.domain.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

// , uses = {AuthorMapper.class}
@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    BookModel toEntity(BookRequestDTO book);
    BookResponseDTO toResponse(BookModel entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    void updateModelFromDto(BookRequestDTO dto, @MappingTarget BookModel model);

    BookSummaryDTO toSummary(BookModel book);
    AuthorSummaryDTO toAuthorSummary(AuthorMapper author);
}
