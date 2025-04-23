package com.jhops10.personal_finance.common;

import com.jhops10.personal_finance.transactions.expense.Expense;
import com.jhops10.personal_finance.transactions.expense.ExpenseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.jhops10.personal_finance.common.UserConstants.USER;

public class ExpenseConstants {

    public static final ExpenseDTO EXPENSE_DTO = new ExpenseDTO(
            "Descrição Teste",
            new BigDecimal("10.0"),
            LocalDateTime.of(2025, 4, 22, 12, 0),
            1L);

    public static final Expense EXPENSE = Expense.builder()
            .id(1L)
            .description(EXPENSE_DTO.description())
            .amount(EXPENSE_DTO.amount())
            .date(EXPENSE_DTO.date())
            .user(USER)
            .build();

    public static final List<Expense> EXPENSE_LIST = List.of(EXPENSE);
}
