package com.jhops10.personal_finance.transactions.expense;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseDTO(
        @NotBlank String description,
        @NotNull BigDecimal amount,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date,
        @NotNull Long userId
) {
}
