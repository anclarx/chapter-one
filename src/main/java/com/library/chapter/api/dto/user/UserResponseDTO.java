package com.library.chapter.api.dto.user;

import com.library.chapter.domain.model.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String login;
    private String email;
    private UserTypeEnum type;
}
