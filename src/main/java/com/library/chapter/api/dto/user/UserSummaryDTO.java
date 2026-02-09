package com.library.chapter.api.dto.user;

import com.library.chapter.domain.model.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDTO {

    private Long id;
    private String name;
    private UserTypeEnum type;
}
