package com.jhops10.personal_finance.transactions.summary;

import com.jhops10.personal_finance.transactions.expense.ExpenseRepository;
import com.jhops10.personal_finance.transactions.income.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.jhops10.personal_finance.common.SummaryConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SummaryServiceTest {


    @InjectMocks
    private SummaryService summaryService;

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private ExpenseRepository expenseRepository;


    @Test
    void shouldReturnCorrectSummary() {
        when(incomeRepository.getTotalIncomeByMonthAndYear(USER_ID, MONTH, YEAR)).thenReturn(EXPECTED_INCOME);
        when(expenseRepository.getTotalExpenseByMonthAndYear(USER_ID, MONTH, YEAR)).thenReturn(EXPECTED_EXPENSE);

        SummaryDTO sut = summaryService.getSummary(USER_ID, MONTH, YEAR);

        assertEquals(EXPECTED_INCOME, sut.totalIncome());
        assertEquals(EXPECTED_EXPENSE, sut.totalExpense());
        assertEquals(EXPECTED_BALANCE, sut.balance());

        verify(incomeRepository).getTotalIncomeByMonthAndYear(USER_ID, MONTH, YEAR);
        verify(expenseRepository).getTotalExpenseByMonthAndYear(USER_ID, MONTH, YEAR);
        verifyNoMoreInteractions(incomeRepository);
        verifyNoMoreInteractions(expenseRepository);
    }

}