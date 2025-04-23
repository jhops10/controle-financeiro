package com.jhops10.personal_finance.transactions.expense;

import com.jhops10.personal_finance.exceptions.UserNotFoundException;
import com.jhops10.personal_finance.user.User;
import com.jhops10.personal_finance.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public Expense createExpense(ExpenseDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException("Usuário com o id: " + dto.userId() + " não encontrado"));

        Expense expense = Expense.builder()
                .description(dto.description())
                .amount(dto.amount())
                .date(dto.date())
                .user(user)
                .build();


        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserId(userId);
    }
}
