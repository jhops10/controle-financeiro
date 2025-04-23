package com.jhops10.personal_finance.transactions.expense;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody ExpenseDTO dto) {
        Expense expense = expenseService.createExpense(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getExpensesByUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(expenseService.getExpensesByUser(userId));
    }
}
