package com.library.chapter.application.mapper;

import com.library.chapter.api.dto.user.UserRequestDTO;
import com.library.chapter.api.dto.user.UserResponseDTO;
import com.library.chapter.api.dto.user.UserSummaryDTO;
import com.library.chapter.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserModel toEntity(UserRequestDTO user);

    UserResponseDTO toResponse(UserModel user);

    @Mapping(target = "id", ignore = true)
    void updateModelFromDto(UserRequestDTO dto, @MappingTarget UserModel model);

    UserSummaryDTO toSummary(UserModel user);
}
