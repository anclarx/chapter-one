package com.library.chapter.application.mapper;

import com.library.chapter.api.dto.copy.BookCopyRequestDTO;
import com.library.chapter.api.dto.copy.BookCopyResponseDTO;
import com.library.chapter.domain.model.BookCopyModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookCopyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookModel", ignore = true)
    BookCopyModel toEntity(BookCopyRequestDTO bookCopy);

    BookCopyResponseDTO toResponse(BookCopyModel entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookModel", ignore = true)
    void updateModelFromDto(BookCopyRequestDTO dto, @MappingTarget BookCopyModel model);
}
