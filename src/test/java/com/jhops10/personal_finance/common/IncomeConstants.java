package com.jhops10.personal_finance.common;

import com.jhops10.personal_finance.transactions.expense.Expense;
import com.jhops10.personal_finance.transactions.expense.ExpenseDTO;
import com.jhops10.personal_finance.transactions.income.Income;
import com.jhops10.personal_finance.transactions.income.IncomeDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.jhops10.personal_finance.common.UserConstants.USER;

public class IncomeConstants {

    public static final IncomeDTO INCOME_DTO = new IncomeDTO(
            "Descrição Teste",
            new BigDecimal("10.0"),
            LocalDateTime.of(2025, 4, 22, 12, 0),
            1L);

    public static final Income INCOME = Income.builder()
            .id(1L)
            .description(INCOME_DTO.description())
            .amount(INCOME_DTO.amount())
            .date(INCOME_DTO.date())
            .user(USER)
            .build();

    public static final List<Income> INCOME_LIST = List.of(INCOME);
}
