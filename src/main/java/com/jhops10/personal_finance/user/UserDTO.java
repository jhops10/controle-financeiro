package com.jhops10.personal_finance.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank String name,
        @Email String email,
        @NotBlank String password
) {
}
