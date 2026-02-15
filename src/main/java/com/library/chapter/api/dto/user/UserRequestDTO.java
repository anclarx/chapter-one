package com.library.chapter.api.dto.user;

import com.library.chapter.domain.model.enums.UserTypeEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres.")
    private String name;

    @NotBlank(message = "O login é obrigatório.")
    @Size(min = 8, message = "O login deve ter no mínimo 8 caracteres.")
    private String login;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Size(max = 255, message = "O e-mail não pode exceder 255 caracteres.")
    @Email(message = "O e-mail informado é inválido.")
    private String email;

    @NotNull(message = "O tipo de usuário é obrigatório.")
    private UserTypeEnum type;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    private String password;
}
