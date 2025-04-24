package com.jhops10.personal_finance.transactions.summary;

import java.math.BigDecimal;

public record SummaryDTO(
        BigDecimal totalIncome,
        BigDecimal totalExpense,
        BigDecimal balance
) {
}
