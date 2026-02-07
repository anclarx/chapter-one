package com.library.chapter.api.dto.author;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDTO {

    @NotBlank(message = "O nome do autor é obrigatório.")
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres.")
    private String name;

    @Email(message = "Insira um formato de e-mail válido (ex: usuario@email.com).")
    @Size(max = 255, message = "O e-mail não pode exceder 255 caracteres.")
    private String email;

    private String description;
}
