package com.jhops10.personal_finance.transactions.summary;

import com.jhops10.personal_finance.transactions.expense.ExpenseRepository;
import com.jhops10.personal_finance.transactions.income.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public SummaryDTO getSummary(Long userId, int month, int year) {
        BigDecimal totalIncome = incomeRepository.getTotalIncomeByMonthAndYear(userId, month, year);
        BigDecimal totalExpense = expenseRepository.getTotalExpenseByMonthAndYear(userId, month, year);
        BigDecimal totalBalance = totalIncome.subtract(totalExpense);

        return new SummaryDTO(totalIncome, totalExpense, totalBalance);
    }
}
