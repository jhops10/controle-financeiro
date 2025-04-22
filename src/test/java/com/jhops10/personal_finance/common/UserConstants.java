package com.jhops10.personal_finance.common;

import com.jhops10.personal_finance.user.User;
import com.jhops10.personal_finance.user.UserDTO;

import java.time.LocalDateTime;

public class UserConstants {

    public static final UserDTO USER_DTO = new UserDTO("Usuario Teste", "email@teste.com", "senha");

    public static final User USER = User.builder()
            .id(1L)
            .name(USER_DTO.name())
            .email(USER_DTO.email())
            .password(USER_DTO.password())
            .createdAt(LocalDateTime.of(2025, 4, 22, 12, 0))
            .build();
}
