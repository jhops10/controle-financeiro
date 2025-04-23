package com.jhops10.personal_finance.transactions.income;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<Income> createIncome(@RequestBody IncomeDTO dto) {
        Income income = incomeService.createIncome(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(income);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Income>> getIncomesByUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(incomeService.getIncomesByUser(userId));
    }
}
