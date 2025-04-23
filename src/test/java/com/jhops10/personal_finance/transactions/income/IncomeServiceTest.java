package com.jhops10.personal_finance.transactions.income;

import static com.jhops10.personal_finance.common.ExpenseConstants.*;

import com.jhops10.personal_finance.common.IncomeConstants;
import com.jhops10.personal_finance.exceptions.UserNotFoundException;
import com.jhops10.personal_finance.transactions.expense.Expense;
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

import static com.jhops10.personal_finance.common.IncomeConstants.*;
import static com.jhops10.personal_finance.common.UserConstants.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncomeServiceTest {

    @InjectMocks
    private IncomeService incomeService;

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private UserRepository userRepository;


    @Test
    void shouldCreateIncomeWithSuccess() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(USER));
        when(incomeRepository.save(any(Income.class))).thenReturn(INCOME);

        Income sut = incomeService.createIncome(INCOME_DTO);

        assertNotNull(sut);
        assertEquals(1L, sut.getId());
        assertEquals("Descrição Teste", sut.getDescription());
        assertEquals(new BigDecimal("10.0"), sut.getAmount());
        assertEquals(USER, sut.getUser());

        verify(userRepository).findById(1L);
        verify(incomeRepository).save(any(Income.class));
    }

    @Test
    void shouldThrowExceptionWhenUserIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        UserNotFoundException sut = assertThrows(
                UserNotFoundException.class,
                () -> incomeService.createIncome(INCOME_DTO)
        );

        assertEquals("Usuário com o id: " + INCOME_DTO.userId() +  " não encontrado", sut.getMessage());

        verify(userRepository).findById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldReturnAllIncomesByUser() {
        when(incomeRepository.findByUserId(anyLong())).thenReturn(INCOME_LIST);

        List<Income> sut = incomeService.getIncomesByUser(1L);

        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals("Descrição Teste", sut.get(0).getDescription());
        assertEquals(new BigDecimal("10.0"), sut.get(0).getAmount());
        assertEquals(USER, sut.get(0).getUser());

        verify(incomeRepository).findByUserId(1L);
        verifyNoMoreInteractions(incomeRepository);
    }

    @Test
    void shouldReturnEmptyListWhenNoIncomesFoundForUser() {
        when(incomeRepository.findByUserId(anyLong())).thenReturn(Collections.emptyList());

        List<Income> sut = incomeService.getIncomesByUser(1L);

        assertNotNull(sut);
        assertEquals(0, sut.size());
        assertTrue(sut.isEmpty());

        verify(incomeRepository).findByUserId(1L);
        verifyNoMoreInteractions(incomeRepository);
    }


}