package com.jhops10.personal_finance.transactions.expense;

import static com.jhops10.personal_finance.common.ExpenseConstants.*;
import static com.jhops10.personal_finance.common.UserConstants.USER;

import com.jhops10.personal_finance.exceptions.UserNotFoundException;
import com.jhops10.personal_finance.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @InjectMocks
    private ExpenseService expenseService;

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;


    @Test
    void shouldCreateExpenseWithSuccess() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(USER));
        when(expenseRepository.save(any(Expense.class))).thenReturn(EXPENSE);

        Expense sut = expenseService.createExpense(EXPENSE_DTO);

        assertNotNull(sut);
        assertEquals(1L, sut.getId());
        assertEquals("Descrição Teste", sut.getDescription());
        assertEquals(new BigDecimal("10.0"), sut.getAmount());
        assertEquals(USER, sut.getUser());

        verify(userRepository).findById(1L);
        verify(expenseRepository).save(any(Expense.class));
    }

    @Test
    void shouldThrowExceptionWhenUserIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        UserNotFoundException sut = assertThrows(
                UserNotFoundException.class,
                () -> expenseService.createExpense(EXPENSE_DTO)
        );

        assertEquals("Usuário com o id: " + EXPENSE_DTO.userId() +  " não encontrado", sut.getMessage());

        verify(userRepository).findById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldReturnAllExpensesByUser() {
        when(expenseRepository.findByUserId(anyLong())).thenReturn(EXPENSE_LIST);

        List<Expense> sut = expenseService.getExpensesByUser(1L);

        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals("Descrição Teste", sut.get(0).getDescription());
        assertEquals(new BigDecimal("10.0"), sut.get(0).getAmount());
        assertEquals(USER, sut.get(0).getUser());

        verify(expenseRepository).findByUserId(1L);
        verifyNoMoreInteractions(expenseRepository);
    }

    @Test
    void shouldReturnEmptyListWhenNoExpensesFoundForUser() {
        when(expenseRepository.findByUserId(anyLong())).thenReturn(Collections.emptyList());

        List<Expense> sut = expenseService.getExpensesByUser(1L);

        assertNotNull(sut);
        assertEquals(0, sut.size());
        assertTrue(sut.isEmpty());

        verify(expenseRepository).findByUserId(1L);
        verifyNoMoreInteractions(expenseRepository);
    }

}